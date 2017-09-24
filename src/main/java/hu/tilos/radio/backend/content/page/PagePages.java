package hu.tilos.radio.backend.content.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagePages {

  @Autowired
  PageRepository repository;

  @Autowired
  PageService pageService;

  @RequestMapping("/page/{name}")
  public String page(@PathVariable String name, Model model) {
    model.addAttribute("page", pageService.render(repository.findByAlias(name)));
    return "page";
  }
}
