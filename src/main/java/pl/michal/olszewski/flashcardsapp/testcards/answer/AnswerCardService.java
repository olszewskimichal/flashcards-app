package pl.michal.olszewski.flashcardsapp.testcards.answer;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;
import pl.michal.olszewski.flashcardsapp.testcards.TestCardRepository;

@Service
public class AnswerCardService {

  private final TestCardRepository testCardRepository;

  public AnswerCardService(TestCardRepository testCardRepository) {
    this.testCardRepository = testCardRepository;
  }

  public Boolean processAnswer(TestCardAnswerDTO testCardAnswerDTO) {
    Objects.requireNonNull(testCardAnswerDTO.getTestCardId(), "Nie mozna dac odpowiedzi do nieznanej fiszki");
    TestCard one = testCardRepository.findOne(testCardAnswerDTO.getTestCardId());
    String answer = one.getCard().getAnswer();
    if (testCardAnswerDTO.getAnswer().equalsIgnoreCase(answer)) {
      one.setIsCorrect(true);
      one.setTestLevel(one.getTestLevel() + 1);
      return true;
    }
    return false;
  }
}
