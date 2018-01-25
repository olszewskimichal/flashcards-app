package pl.michal.olszewski.flashcardsapp.examcards;

public class ExamCardNotFoundException extends RuntimeException {

  public ExamCardNotFoundException(Long id) {
    super(String.format("Nie znalaziono examCard o id = %s", id));
  }


}
