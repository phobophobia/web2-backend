package hu.tilos.radio.backend.controller;

import hu.radio.tilos.model.Contribution;
import hu.radio.tilos.model.Role;
import hu.radio.tilos.model.Show;
import hu.radio.tilos.model.User;
import hu.radio.tilos.model.type.ShowStatus;
import hu.tilos.radio.backend.Security;
import hu.tilos.radio.backend.Session;
import hu.tilos.radio.backend.converters.SchedulingTextUtil;

import hu.tilos.radio.backend.data.input.ShowToSave;
import hu.tilos.radio.backend.data.response.CreateResponse;
import hu.tilos.radio.backend.data.response.UpdateResponse;
import hu.tilos.radio.backend.data.types.*;
import hu.tilos.radio.backend.episode.EpisodeUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.*;

@Path("/api/v1/show")
public class ShowController {

    private static Logger LOG = LoggerFactory.getLogger(ShowController.class);

    private final SchedulingTextUtil schedulingTextUtil = new SchedulingTextUtil();

    @Inject
    EpisodeUtil episodeUtil;
    @Inject
    Session session;
    @Inject
    private EntityManager entityManager;
    @Inject
    private ModelMapper modelMapper;

    @Produces("application/json")
    @Path("/")
    @Security(role = Role.GUEST)
    @GET
    public List<ShowSimple> list(@QueryParam("status") String status) {
        ShowStatus showStatus = processStatus(status);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Show> query = criteriaBuilder.createQuery(Show.class);
        Root<Show> showRoot = query.from(Show.class);
        CriteriaQuery<Show> select = query.select(showRoot);
        if (showStatus != null) {
            select.where(criteriaBuilder.equal(showRoot.get("status"), showStatus));
        }
        List<Show> selectedShows = entityManager.createQuery(query).getResultList();

        List<ShowSimple> mappedShows = new ArrayList<>();
        for (Show show : selectedShows) {
            mappedShows.add(modelMapper.map(show, ShowSimple.class));
        }
        return mappedShows;

    }

    private ShowStatus processStatus(String status) {
        if (status == null) {
            return ShowStatus.ACTIVE;
        } else if (status.equals("all")) {
            return null;
        } else {
            return ShowStatus.valueOf(status.toUpperCase());
        }
    }

    /**
     * Detailed information about one radioshow.
     * <p/>
     * Integer based if also could be used as an alias.
     *
     * @param alias Alias of the radioshow (eg. 3-utas)
     * @return
     */
    @Produces("application/json")
    @Path("/{alias}")
    @Security(role = Role.GUEST)
    @GET
    @Transactional
    public ShowDetailed get(@PathParam("alias") String alias) {
        Show show = null;
        if (!alias.matches("\\d+")) {
            show = entityManager.createQuery("SELECT s FROM Show s " +
                    "LEFT JOIN FETCH s.mixes " +
                    "LEFT JOIN FETCH s.contributors " +
                    "LEFT JOIN FETCH s.schedulings " +
                    "WHERE s.alias=:alias", Show.class).setParameter("alias", alias).getSingleResult();
        } else {
            show = entityManager.createQuery("SELECT s FROM Show s " +
                    "LEFT JOIN FETCH s.mixes " +
                    "LEFT JOIN FETCH s.contributors " +
                    "LEFT JOIN FETCH s.schedulings " +
                    "WHERE s.id=:id", Show.class).setParameter("id", Integer.parseInt(alias)).getSingleResult();
        }


        ShowDetailed detailed = modelMapper.map(show, ShowDetailed.class);


        Collections.sort(detailed.getMixes(), new Comparator<MixSimple>() {

            @Override
            public int compare(MixSimple mixSimple, MixSimple mixSimple2) {
                return mixSimple.getTitle().compareTo(mixSimple2.getTitle());
            }
        });

        Collections.sort(detailed.getContributors(), new Comparator<ShowContribution>() {

            @Override
            public int compare(ShowContribution contribution, ShowContribution contribution2) {
                return contribution.getAuthor().getName().compareTo(contribution2.getAuthor().getName());
            }
        });

        Date now = new Date();
        for (SchedulingSimple ss : detailed.getSchedulings()) {
            if (ss.getValidFrom().compareTo(now) < 0 && ss.getValidTo().compareTo(now) > 0)
                ss.setText(schedulingTextUtil.create(ss));
        }


        Long o = (Long) entityManager.createQuery("SELECT count(m) FROM Mix m where m.show = :show").setParameter("show", show).getSingleResult();
        detailed.getStats().mixCount = o.intValue();

        return detailed;

    }

    @GET
    @Path("/{show}/episodes")
    @Security(role = Role.GUEST)
    @Produces("application/json")
    @Transactional
    public List<EpisodeData> listEpisodes(@PathParam("show") String showAlias, @QueryParam("start") long from, @QueryParam("end") long to) {
        Date fromDate = new Date();
        fromDate.setTime(from);
        Date toDate = new Date();
        toDate.setTime(to);
        int showId;
        showId = Integer.parseInt(showAlias);
        //todo on parse error
        //Show show = (Show) entityManager.createQuery("SELECT s FROM Show s WHERE s.alias = :alias").setParameter("alias",showAlias).getSingleResult();
        return episodeUtil.getEpisodeData(showId, fromDate, toDate);

    }


    @Produces("application/json")
    @Path("/{alias}")
    @Security(role = Role.AUTHOR)
    @PUT
    @Transactional
    public UpdateResponse update(@PathParam("alias") String alias, ShowToSave showToSave) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Show> query = criteriaBuilder.createQuery(Show.class);
        Root<Show> fromShow = query.from(Show.class);
        CriteriaQuery<Show> select = query.select(fromShow);
        if (alias.matches("\\d+")) {
            select.where(criteriaBuilder.equal(fromShow.get("id"), Integer.parseInt(alias)));
        } else {
            select.where(criteriaBuilder.equal(fromShow.get("alias"), alias));
        }

        Show show = entityManager.createQuery(query).getSingleResult();
        checkPermission(show, session.getCurrentUser());
        modelMapper.map(showToSave, show);
        entityManager.persist(show);
        return new UpdateResponse(true);

    }

    protected void checkPermission(Show show, User currentUser) {
        if (currentUser.getRole() == Role.ADMIN) {
            return;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contribution> query = criteriaBuilder.createQuery(Contribution.class);
        Root<Contribution> fromContribution = query.from(Contribution.class);
        query.where(criteriaBuilder.equal(fromContribution.get("author").get("user").get("id"), currentUser.getId()));
        List<Contribution> contributions = entityManager.createQuery(query).getResultList();

        for (hu.radio.tilos.model.Contribution contribution : contributions) {
            if (contribution.getShow().getId() == show.getId()) {
                return;
            }
        }
        throw new IllegalArgumentException("No permission to modify");
    }

    @Produces("application/json")
    @Path("/{alias}")
    @Security(role = Role.ADMIN)
    @POST
    @Transactional
    public CreateResponse create(@PathParam("alias") String alias, ShowToSave showToSave) {
        Show show = modelMapper.map(showToSave, Show.class);
        entityManager.persist(show);
        entityManager.flush();
        return new CreateResponse(show.getId());
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}