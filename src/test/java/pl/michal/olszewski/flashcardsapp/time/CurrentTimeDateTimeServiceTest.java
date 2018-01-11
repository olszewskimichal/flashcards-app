package pl.michal.olszewski.flashcardsapp.time;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CurrentTimeDateTimeServiceTest {

  private DateTimeService timeService;

  @BeforeEach
  void setUp() {
    timeService = new CurrentTimeDateTimeService();
  }

  @Test
  void shouldGetCurrentDateTime() throws InterruptedException {
    //given
    Instant dateTime = timeService.getCurrentDateTime();
    //when
    Thread.sleep(5);
    Instant currentDateTime = timeService.getCurrentDateTime();
    //then
    assertThat(currentDateTime).isAfter(dateTime);
  }
}