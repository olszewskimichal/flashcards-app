package pl.michal.olszewski.flashcardsapp.examcards.write.answer;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.examcards.ExamCardRepository;
import pl.michal.olszewski.flashcardsapp.examcards.read.ExamCard;

@Service
public class AnswerCardService {

  private final ExamCardRepository examCardRepository;

  public AnswerCardService(ExamCardRepository examCardRepository) {
    this.examCardRepository = examCardRepository;
  }

  public Boolean processAnswer(ExamCardAnswerDTO examCardAnswerDTO) {
    Objects.requireNonNull(examCardAnswerDTO.getExamCardId(), "Nie mozna dac odpowiedzi do nieznanej fiszki");
    ExamCard one = examCardRepository.findOne(examCardAnswerDTO.getExamCardId());
    String answer = one.getCard().getAnswer();
    if (examCardAnswerDTO.getAnswer().equalsIgnoreCase(answer)) {
      one.setIsCorrect(true);
      one.setTestLevel(one.getTestLevel() + 1);
      return true;
    }
    return false;
  }
}
