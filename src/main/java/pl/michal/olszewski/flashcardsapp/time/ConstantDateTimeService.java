package pl.michal.olszewski.flashcardsapp.time;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.config.Profiles;

@Slf4j
@Profile(Profiles.TEST)
@Component
public class ConstantDateTimeService implements DateTimeService {

  @Override
  public Instant getCurrentDateTime() {
    Instant instant = Instant.parse("2000-01-01T10:00:55.000Z");
    log.debug("Returning current date and time: {}", instant);
    return instant;
  }
}
