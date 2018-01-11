package pl.michal.olszewski.flashcardsapp.time;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.config.Profiles;

@Slf4j
@Profile(Profiles.DEVELOPMENT)
@Component
public class CurrentTimeDateTimeService implements DateTimeService {

  @Override
  public Instant getCurrentDateTime() {
    Instant currentDateAndTime = Instant.now();

    log.debug("Returning current date and time: {}", currentDateAndTime);
    return currentDateAndTime;
  }
}
