package hu.tilos.radio.backend.content.news;

import hu.tilos.radio.backend.text.TextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class NewsService {

  @Autowired
  NewsRepository repository;

  @Autowired
  TextConverter textConverter;

  public List<News> actual() {
    return repository.findAll(new PageRequest(0, 10, Sort.Direction.DESC, "created")).getContent().stream()
        .map(this::render)
        .map(this::renderLead)
        .map(a -> {
          a.setContent(null);
          a.setFormatted(null);
          return a;
        })
        .collect(Collectors.toList());
  }
  public News render(News p) {
    p.setFormatted(textConverter.format("markdown", p.getContent()));
    return p;
  }

  public News renderLead(News p) {
    StringBuilder lead = new StringBuilder();
    Scanner s = new Scanner(p.getContent()).useDelimiter("\\n");
    while (s.hasNext() && lead.length() < 200) {
      lead.append(s.next());
    }
    p.setLeadFormatted(textConverter.format("markdown", lead.toString()));
    if (lead.length() < p.getContent().length()) {
      p.setLongText(true);
    }
    return p;
  }
}
