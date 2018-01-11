package pl.michal.olszewski.flashcardsapp.time;

import java.time.Instant;

/**
 * Declares the method that is used to get the current date and time.
 */
public interface DateTimeService {

  /**
   * Returns the current date and time.
   */
  Instant getCurrentDateTime();
}
