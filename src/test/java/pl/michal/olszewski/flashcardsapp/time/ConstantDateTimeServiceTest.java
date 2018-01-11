package pl.michal.olszewski.flashcardsapp.time;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConstantDateTimeServiceTest {

  private DateTimeService timeService;

  @BeforeEach
  void setUp() {
    timeService = new ConstantDateTimeService();
  }

  @Test
  void shouldReturnConstantTimeEveryTime() {
    //given
    Instant dateTime = timeService.getCurrentDateTime();
    //when
    Instant currentDateTime = timeService.getCurrentDateTime();
    //then
    assertThat(dateTime).isEqualTo(currentDateTime);
  }
}