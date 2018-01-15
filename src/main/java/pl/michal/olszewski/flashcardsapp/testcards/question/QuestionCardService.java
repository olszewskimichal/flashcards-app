package pl.michal.olszewski.flashcardsapp.testcards.question;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;
import pl.michal.olszewski.flashcardsapp.testcards.TestCardLevelEnum;

@Service
public class QuestionCardService {

  private final AttemptRepository attemptRepository;
  private final TestCardQuestionObjectMapper testCardQuestionObjectMapper;

  public QuestionCardService(AttemptRepository attemptRepository, TestCardQuestionObjectMapper testCardQuestionObjectMapper) {
    this.attemptRepository = attemptRepository;
    this.testCardQuestionObjectMapper = testCardQuestionObjectMapper;
  }

  public Optional<TestCard> getNextTestCardQuestion(GetTestCardQuestionDTO questionDTO) {
    Attempt attempt = attemptRepository.findOne(questionDTO.getAttemptId());
    List<TestCard> cardList = attempt.getCardList();
    Collections.shuffle(cardList);

    return cardList.parallelStream().filter(v -> !v.getTestLevel().equals(TestCardLevelEnum.PERFECT.getValue()))
        .findAny();
  }

  public Optional<TestCardQuestionDTO> mapTestCardToDTO(Optional<TestCard> testCard) {
    return testCard.map(testCardQuestionObjectMapper::convertToDTO);
  }

}
