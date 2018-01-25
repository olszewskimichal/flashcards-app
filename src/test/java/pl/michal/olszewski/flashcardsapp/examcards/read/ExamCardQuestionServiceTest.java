package pl.michal.olszewski.flashcardsapp.examcards.read;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptNotFoundException;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptFinder;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.examcards.read.dto.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCardLevelEnum;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.attempt.AttemptFactory;
import pl.michal.olszewski.flashcardsapp.factory.examcards.ExamCardFactory;

@ExtendWith(MockitoExtension.class)
class ExamCardQuestionServiceTest {

  private ExamCardQuestionService examCardQuestionService;

  @Mock
  private AttemptFinder attemptRepository;

  @Mock
  private ExamCardQuestionObjectMapper examCardQuestionObjectMapper;

  @BeforeEach
  void setUp() {
    examCardQuestionService = new ExamCardQuestionService(attemptRepository, examCardQuestionObjectMapper);
  }

  @Test
  void shouldGetNextTestCardQuestion() {
    Attempt attempt = Attempt.builder().build();
    ExamCard examCard = ExamCardFactory.build(2L, null, null, ExamCardLevelEnum.GOOD.getValue());

    attempt.setExamCards(Collections.singletonList(examCard));
    given(attemptRepository.findById(1L)).willReturn(Optional.of(attempt));
    Optional<ExamCard> nextTestCardQuestion = examCardQuestionService.getNextTestCardQuestion(1L);

    assertThat(nextTestCardQuestion).isPresent();
  }

  @Test
  void shouldThrowExceptionWhenTryGetNextTestCardQuestionForNotExistingAttempt() {
    given(attemptRepository.findById(1L)).willReturn(Optional.empty());
    assertThrows(AttemptNotFoundException.class, () -> examCardQuestionService.getNextTestCardQuestion(1L));
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsArePerfect() {
    Attempt attempt = AttemptFactory.build(1L);
    ExamCard examCard = ExamCardFactory.build(2L, null, null, ExamCardLevelEnum.PERFECT.getValue());
    attempt.setExamCards(Collections.singletonList(examCard));
    given(attemptRepository.findById(1L)).willReturn(Optional.of(attempt));
    Optional<ExamCard> nextTestCardQuestion = examCardQuestionService.getNextTestCardQuestion(1L);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsIsEmpty() {
    Attempt attempt = AttemptFactory.build(1L);
    attempt.setExamCards(Collections.emptyList());
    given(attemptRepository.findById(1L)).willReturn(Optional.of(attempt));
    Optional<ExamCard> nextTestCardQuestion = examCardQuestionService.getNextTestCardQuestion(1L);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldMapTestCardToDTO() {
    ExamCard examCard = ExamCard.builder().build();
    given(examCardQuestionObjectMapper.convertToDTO(examCard)).willReturn(ExamCardQuestionDTO.builder().build());

    Optional<ExamCardQuestionDTO> cardQuestionDTO = examCardQuestionService.mapTestCardToDTO(Optional.of(examCard));
    assertThat(cardQuestionDTO).isPresent();
  }

  @Test
  void shouldReturnOptionalEmpty() {
    Optional<ExamCardQuestionDTO> cardQuestionDTO = examCardQuestionService.mapTestCardToDTO(Optional.ofNullable(null));
    assertThat(cardQuestionDTO).isNotPresent();
  }


}