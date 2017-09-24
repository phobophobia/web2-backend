package hu.tilos.radio.backend.content.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    NewsRepository repository;


    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/api/v1/text/news/{alias}")
    public News get(@PathVariable String alias) {
        return newsService.render(repository.findByAliasOrId(alias, alias));
    }


    @RequestMapping(value = "/api/v1/text/news")
    public List<News> list() {
        return repository.findAll(new Sort(Sort.Direction.DESC, "created")).stream()
            .map(newsService::render).collect(Collectors.toList());
    }

    @RequestMapping(value = "/api/v1/text/news/current")
    public List<News> actual() {
        return newsService.actual();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/api/v1/text/news/{alias}", method = RequestMethod.PUT)
    public void update(@RequestBody News news) {
        repository.save(news);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/api/v1/text/news", method = RequestMethod.POST)
    public News create(@RequestBody News news) {
        news.setCreated(new Date());
        return newsService.render(repository.insert(news));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/api/v1/text/news/{alias}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String alias) {
        repository.deleteNewsByAlias(alias);
    }



}
