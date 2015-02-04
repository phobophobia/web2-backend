package hu.tilos.radio.backend.controller;

import com.github.fakemongo.junit.FongoRule;
import hu.tilos.radio.backend.*;
import hu.tilos.radio.backend.data.output.ListenerStat;
import hu.tilos.radio.backend.data.output.StatData;
import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static hu.tilos.radio.backend.MongoTestUtil.loadTo;

@RunWith(CdiRunner.class)
@AdditionalClasses({MongoProducer.class, DozerFactory.class, ConfigurationProducer.class})
@ActivatedAlternatives({FongoCreator.class, TestConfigProvider.class})
public class StatControllerTest {


    @Inject
    StatController controller;

    @Inject
    FongoRule fongoRule;

    @Rule
    public FongoRule fongoRule() {
        return fongoRule;
    }


    @Test
    public void testGetSummary() {
        //given
        loadTo(fongoRule, "show", "show-3utas.json");
        loadTo(fongoRule, "show", "show-vendeglo.json");
        loadTo(fongoRule, "episode", "episode-episode1.json");
        loadTo(fongoRule, "episode", "episode-episode2.json");
        //when
        StatData data = controller.getSummary();

        //then
        Assert.assertEquals(2, data.showCount);
        Assert.assertEquals(2, data.episodeCount);
    }

    @Test
    public void testGetListenerStat() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hhmm");
        //given
        loadTo(fongoRule, "show", "show-3utas.json");
        loadTo(fongoRule, "stat_icecast", "stat_icecast1.json");
        loadTo(fongoRule, "stat_icecast", "stat_icecast2.json");
        Date from = sdf.parse("20140419 0800");
        Date to = sdf.parse("20140419 1000");

        //when

        List<ListenerStat> listenerSTat = controller.getListenerSTat(from.getTime(), to.getTime());

        //then

    }
}