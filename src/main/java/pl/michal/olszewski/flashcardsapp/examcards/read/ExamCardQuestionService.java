package pl.michal.olszewski.flashcardsapp.examcards.read;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptNotFoundException;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptFinder;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.examcards.read.dto.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCardLevelEnum;

@Service
public class ExamCardQuestionService {

  private final AttemptFinder attemptRepository;
  private final ExamCardQuestionObjectMapper examCardQuestionObjectMapper;

  public ExamCardQuestionService(AttemptFinder attemptRepository, @Qualifier("ExamCardQuestionObjectMapper") ExamCardQuestionObjectMapper examCardQuestionObjectMapper) {
    this.attemptRepository = attemptRepository;
    this.examCardQuestionObjectMapper = examCardQuestionObjectMapper;
  }

  public Optional<ExamCard> getNextTestCardQuestion(Long attemptId) {
    Attempt attempt = attemptRepository.findById(attemptId).orElseThrow(() -> new AttemptNotFoundException(attemptId));
    List<ExamCard> cardList = attempt.getExamCards();
    Collections.shuffle(cardList);

    return cardList.parallelStream().filter(v -> !v.getTestLevel().equals(ExamCardLevelEnum.PERFECT.getValue()))
        .findAny();
  }

  public Optional<ExamCardQuestionDTO> mapTestCardToDTO(Optional<ExamCard> testCard) {
    return testCard.map(examCardQuestionObjectMapper::convertToDTO);
  }

}
