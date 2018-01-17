package pl.michal.olszewski.flashcardsapp.examcards.read;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.examcards.read.dto.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCardLevelEnum;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExamCardQuestionServiceTest {

  private ExamCardQuestionService examCardQuestionService;

  @Mock
  private AttemptRepository attemptRepository;

  @Mock
  private ExamCardQuestionObjectMapper examCardQuestionObjectMapper;

  @BeforeEach
  void setUp() {
    examCardQuestionService = new ExamCardQuestionService(attemptRepository, examCardQuestionObjectMapper);
  }

  @Test
  void shouldGetNextTestCardQuestion() {
    Attempt attempt = Attempt.builder().build();
    attempt.setExamCards(Collections.singletonList(ExamCard.builder().id(2L).testLevel(ExamCardLevelEnum.GOOD.getValue()).build()));
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<ExamCard> nextTestCardQuestion = examCardQuestionService.getNextTestCardQuestion(1L);

    assertThat(nextTestCardQuestion).isPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsArePerfect() {
    Attempt attempt = Attempt.builder().build();
    attempt.setExamCards(Collections.singletonList(ExamCard.builder().id(2L).testLevel(ExamCardLevelEnum.PERFECT.getValue()).build()));
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<ExamCard> nextTestCardQuestion = examCardQuestionService.getNextTestCardQuestion(1L);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsIsEmpty() {
    Attempt attempt = Attempt.builder().build();
    attempt.setExamCards(Collections.emptyList());
    given(attemptRepository.findOne(1L)).willReturn(attempt);
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