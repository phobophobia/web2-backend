package hu.tilos.radio.backend.mix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class MixPage {
  @Autowired
  MixRepository mixRepository;

  public String list(Model model) {
    model.addAttribute("mixes", mixRepository.findAll());
    return "mixes";
  }
}
