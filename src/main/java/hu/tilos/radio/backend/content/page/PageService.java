package hu.tilos.radio.backend.content.page;

import hu.tilos.radio.backend.text.TextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

  @Autowired
  TextConverter textConverter;

  public Page render(Page p) {
    p.setFormatted(textConverter.format("markdown", p.getContent()));
    return p;
  }

}
