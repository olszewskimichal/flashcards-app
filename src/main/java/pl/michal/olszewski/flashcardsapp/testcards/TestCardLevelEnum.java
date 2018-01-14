package pl.michal.olszewski.flashcardsapp.testcards;

import lombok.Getter;

@Getter
public enum TestCardLevelEnum {

  NEW(1, "Nowe"),
  BAD(2, "Słabo znane"),
  MEDIUM(3, "Srednio znane"),
  GOOD(4, "Dobrze znane"),
  PERFECT(5, "Opanowane");

  private final Long value;

  private final String description;

  private TestCardLevelEnum(long i, String description) {
    this.description = description;
    this.value = i;
  }

}
