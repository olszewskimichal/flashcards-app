package pl.michal.olszewski.flashcardsapp.examcards.readmodel;

import lombok.Getter;

@Getter
public enum ExamCardLevelEnum {

  NEW(1, "Nowe"),
  BAD(2, "Słabo znane"),
  MEDIUM(3, "Srednio znane"),
  GOOD(4, "Dobrze znane"),
  PERFECT(5, "Opanowane");

  private final Long value;

  private final String description;

  private ExamCardLevelEnum(long i, String description) {
    this.description = description;
    this.value = i;
  }

}
