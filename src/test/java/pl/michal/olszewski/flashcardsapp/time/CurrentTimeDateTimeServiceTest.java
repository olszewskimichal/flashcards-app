package pl.michal.olszewski.flashcardsapp.time;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;


class CurrentTimeDateTimeServiceTest {

  private DateTimeService timeService;

  @BeforeEach
  void setUp() {
    timeService = new CurrentTimeDateTimeService();
  }

  @RepeatedTest(10)
  void shouldGetCurrentDateTime() {
    //given
    Instant dateTime = timeService.getCurrentDateTime();
    //when
    Instant currentDateTime = timeService.getCurrentDateTime();
    //then
    assertThat(currentDateTime).isAfterOrEqualTo(dateTime);
  }
}