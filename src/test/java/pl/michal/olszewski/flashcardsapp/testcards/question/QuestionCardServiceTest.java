package pl.michal.olszewski.flashcardsapp.testcards.question;

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
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;
import pl.michal.olszewski.flashcardsapp.testcards.TestCardLevelEnum;

@ExtendWith(MockitoExtension.class)
class QuestionCardServiceTest {

  private QuestionCardService questionCardService;

  @Mock
  private AttemptRepository attemptRepository;

  @Mock
  private TestCardQuestionObjectMapper testCardQuestionObjectMapper;

  @BeforeEach
  void setUp() {
    questionCardService = new QuestionCardService(attemptRepository, testCardQuestionObjectMapper);
  }

  @Test
  void shouldGetNextTestCardQuestion() {
    GetTestCardQuestionDTO dto = GetTestCardQuestionDTO.builder().attemptId(1L).build();
    Attempt attempt = Attempt.builder().build();
    attempt.setCardList(Collections.singletonList(TestCard.builder().id(2L).testLevel(TestCardLevelEnum.GOOD.getValue()).build()));
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<TestCard> nextTestCardQuestion = questionCardService.getNextTestCardQuestion(dto);

    assertThat(nextTestCardQuestion).isPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsArePerfect() {
    GetTestCardQuestionDTO dto = GetTestCardQuestionDTO.builder().attemptId(1L).build();
    Attempt attempt = Attempt.builder().build();
    attempt.setCardList(Collections.singletonList(TestCard.builder().id(2L).testLevel(TestCardLevelEnum.PERFECT.getValue()).build()));
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<TestCard> nextTestCardQuestion = questionCardService.getNextTestCardQuestion(dto);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldNotGetNextTestCardQuestionWhenAllTestCardsIsEmpty() {
    GetTestCardQuestionDTO dto = GetTestCardQuestionDTO.builder().attemptId(1L).build();
    Attempt attempt = Attempt.builder().build();
    attempt.setCardList(Collections.emptyList());
    given(attemptRepository.findOne(1L)).willReturn(attempt);
    Optional<TestCard> nextTestCardQuestion = questionCardService.getNextTestCardQuestion(dto);

    assertThat(nextTestCardQuestion).isNotPresent();
  }

  @Test
  void shouldMapTestCardToDTO() {
    TestCard testCard = TestCard.builder().build();
    given(testCardQuestionObjectMapper.convertToDTO(testCard)).willReturn(TestCardQuestionDTO.builder().build());

    Optional<TestCardQuestionDTO> cardQuestionDTO = questionCardService.mapTestCardToDTO(Optional.of(testCard));
    assertThat(cardQuestionDTO).isPresent();
  }

  @Test
  void shouldReturnOptionalEmpty() {
    Optional<TestCardQuestionDTO> cardQuestionDTO = questionCardService.mapTestCardToDTO(Optional.ofNullable(null));
    assertThat(cardQuestionDTO).isNotPresent();
  }


}