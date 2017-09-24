package hu.tilos.radio.backend.episode;

import hu.tilos.radio.backend.CommonPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class EpisodePages {
  @Autowired
  CommonPage commonPage;

  @Autowired
  EpisodeService episodeService;

  @RequestMapping("/episode/{year}/{month}/{day}")
  public String episode(Model model) {
    ZonedDateTime end = ZonedDateTime.now()
        .plusDays(1)
        .truncatedTo(ChronoUnit.DAYS);
    ZonedDateTime start = end.minusDays(1);
    model.addAttribute("episodes", episodeService.listEpisodes(start.toEpochSecond() * 1000l, end.toEpochSecond() * 1000l));
    return "program";
  }
}
