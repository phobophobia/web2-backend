package hu.tilos.radio.backend;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CommonPage {
  public void decorate(Model model) {
    model.addAttribute("today", "2017/01/12");

  }
}
