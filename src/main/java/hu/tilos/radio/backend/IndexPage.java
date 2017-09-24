package hu.tilos.radio.backend;

import hu.tilos.radio.backend.content.news.NewsService;
import hu.tilos.radio.backend.episode.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPage {
  @Autowired
  CommonPage commonPage;

  @Autowired
  private EpisodeService episodeService;

  @Autowired
  private NewsService newsService;

  @RequestMapping("/index")
  public String indexAlias(Model model) {
    return page(model);
  }

  @RequestMapping("/")
  public String page(Model model) {
    model.addAttribute("next", episodeService.next());
    model.addAttribute("last", episodeService.last());
    model.addAttribute("lastWeek", episodeService.lastWeek());
    model.addAttribute("news", newsService.actual());
    commonPage.decorate(model);
    return "index";
  }
}
