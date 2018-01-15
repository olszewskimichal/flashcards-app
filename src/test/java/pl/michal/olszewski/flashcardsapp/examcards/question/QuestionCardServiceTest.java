package pl.michal.olszewski.flashcardsapp.examcards.question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.question.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.question.ExamCardQuestionObjectMapper;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.question.GetTestCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.question.QuestionCardService;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.ExamCardLevelEnum;

@ExtendWith(MockitoExtension.class)
class QuestionCardServiceTest {

  private QuestionCardService questionCardService;

  @Mock
  private AttemptRepository attemptRepository;

  @Mock
  private ExamCardQuestionObjectMapper examCardQuestionObjectMapper;

  @BeforeEach
  void setUp() {
    questionCardService = new QuestionCardService(attemptRepository, examCardQuestionObjectMapper);
  }

  @Test
  void shouldGetNextTestCardQuestion() {
    GetTestCardQuestionDTO dto = GetTestCardQuestionDTO.builder().attemptId(1L).build();
    Attempt attempt = Attempt.builder().build();
    attempt.setCardList(Collections.singletonList(ExamCard.builder().id(2L).testLevel(ExamCardLevelEnum.GOOD.getValue()).build()));
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<ExamCard> nextTestCardQuestion = questionCardService.getNextTestCardQuestion(dto);

    assertThat(nextTestCardQuestion).isPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsArePerfect() {
    GetTestCardQuestionDTO dto = GetTestCardQuestionDTO.builder().attemptId(1L).build();
    Attempt attempt = Attempt.builder().build();
    attempt.setCardList(Collections.singletonList(ExamCard.builder().id(2L).testLevel(ExamCardLevelEnum.PERFECT.getValue()).build()));
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<ExamCard> nextTestCardQuestion = questionCardService.getNextTestCardQuestion(dto);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsIsEmpty() {
    GetTestCardQuestionDTO dto = GetTestCardQuestionDTO.builder().attemptId(1L).build();
    Attempt attempt = Attempt.builder().build();
    attempt.setCardList(Collections.emptyList());
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<ExamCard> nextTestCardQuestion = questionCardService.getNextTestCardQuestion(dto);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldMapTestCardToDTO() {
    ExamCard examCard = ExamCard.builder().build();
    given(examCardQuestionObjectMapper.convertToDTO(examCard)).willReturn(ExamCardQuestionDTO.builder().build());

    Optional<ExamCardQuestionDTO> cardQuestionDTO = questionCardService.mapTestCardToDTO(Optional.of(examCard));
    assertThat(cardQuestionDTO).isPresent();
  }

  @Test
  void shouldReturnOptionalEmpty() {
    Optional<ExamCardQuestionDTO> cardQuestionDTO = questionCardService.mapTestCardToDTO(Optional.ofNullable(null));
    assertThat(cardQuestionDTO).isNotPresent();
  }


}