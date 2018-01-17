package pl.michal.olszewski.flashcardsapp.attempt.read.entity;

import java.util.Arrays;

public enum AttemptStatusEnum {
  NEW(1),
  DURING(2),
  DONE(3);

  private final Long value;

  private AttemptStatusEnum(long i) {
    this.value = i;
  }

  public static AttemptStatusEnum fromValue(Long value) {
    return Arrays.stream(AttemptStatusEnum.values()).filter(e -> e.value.equals(value)).findFirst().orElse(null);
  }

  public Long getValue() {
    return value;
  }

}
