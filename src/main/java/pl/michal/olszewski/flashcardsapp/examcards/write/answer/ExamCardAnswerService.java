package pl.michal.olszewski.flashcardsapp.examcards.write.answer;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.examcards.read.ExamCardFinder;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.write.answer.dto.ExamCardAnswerDTO;

@Service
public class ExamCardAnswerService {

  private final ExamCardFinder finder;

  public ExamCardAnswerService(ExamCardFinder finder) {
    this.finder = finder;
  }

  public Boolean processAnswer(ExamCardAnswerDTO examCardAnswerDTO) {
    Objects.requireNonNull(examCardAnswerDTO.getExamCardId(), "Nie mozna dac odpowiedzi do nieznanej fiszki");
    ExamCard one = finder.findById(examCardAnswerDTO.getExamCardId()).orElseThrow(IllegalStateException::new);
    String answer = one.getCard().getAnswer();
    if (examCardAnswerDTO.getAnswer().equalsIgnoreCase(answer)) {
      one.setIsCorrect(true);
      one.setTestLevel(one.getTestLevel() + 1);
      return true;
    }
    return false;
  }
}
