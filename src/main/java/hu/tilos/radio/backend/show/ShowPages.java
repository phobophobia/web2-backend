package hu.tilos.radio.backend.show;

import hu.tilos.radio.backend.CommonPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShowPages {

  @Autowired
  CommonPage commonPage;

  @Inject
  private ShowService showService;

  @RequestMapping("/shows")
  public String showList(Model model) {
    List<ShowSimple> shows = showService.list("active");
    model.addAttribute("musicshows", shows.stream().filter(show -> show.getType() == ShowType.MUSIC).collect(Collectors.toList()));
    model.addAttribute("speechshows", shows.stream().filter(show -> show.getType() == ShowType.SPEECH).collect(Collectors.toList()));

    commonPage.decorate(model);
    return "shows";
  }


  @RequestMapping("/show/{alias}/intro")
  public String showInfo(@PathVariable String alias, Model model) {
    return show(alias, model)+"-info";

  }

  @RequestMapping("/show/{alias}/mixes")
  public String showMixes(@PathVariable String alias, Model model) {
    return show(alias, model)+"-mixes";
  }


  @RequestMapping("/show/{alias}")
  public String show(@PathVariable String alias, Model model) {
    model.addAttribute("show", showService.get(alias));
    commonPage.decorate(model);
    return "show";
  }
}
