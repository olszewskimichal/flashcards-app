package pl.michal.olszewski.flashcardsapp.examcards.readmodel.question;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.ExamCardLevelEnum;

@Service
public class QuestionCardService {

  private final AttemptRepository attemptRepository;
  private final ExamCardQuestionObjectMapper examCardQuestionObjectMapper;

  public QuestionCardService(AttemptRepository attemptRepository, ExamCardQuestionObjectMapper examCardQuestionObjectMapper) {
    this.attemptRepository = attemptRepository;
    this.examCardQuestionObjectMapper = examCardQuestionObjectMapper;
  }

  public Optional<ExamCard> getNextTestCardQuestion(GetTestCardQuestionDTO questionDTO) {
    Attempt attempt = attemptRepository.findOne(questionDTO.getAttemptId());
    List<ExamCard> cardList = attempt.getCardList();
    Collections.shuffle(cardList);

    return cardList.parallelStream().filter(v -> !v.getTestLevel().equals(ExamCardLevelEnum.PERFECT.getValue()))
        .findAny();
  }

  public Optional<ExamCardQuestionDTO> mapTestCardToDTO(Optional<ExamCard> testCard) {
    return testCard.map(examCardQuestionObjectMapper::convertToDTO);
  }

}
