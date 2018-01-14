package pl.michal.olszewski.flashcardsapp.testcards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.cards.Card;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.testcards.answer.AnswerCardService;
import pl.michal.olszewski.flashcardsapp.testcards.answer.TestCardAnswerDTO;

@ExtendWith(MockitoExtension.class)
class AnswerCardServiceTest {

  @Mock
  private TestCardRepository testCardRepository;

  private AnswerCardService answerCardService;

  @BeforeEach
  void setUp() {
    answerCardService = new AnswerCardService(testCardRepository);
  }

  @Test
  void shouldProcessAnswerReturnTrueWhenCorrectAnswer() {
    TestCardAnswerDTO testCardAnswerDTO = TestCardAnswerDTO.builder().testCardId(1L).answer("correct").build();
    TestCard testCard = TestCard.builder().card(Card.builder().answer("Correct").build()).testLevel(TestCardLevelEnum.BAD.getValue()).isCorrect(Boolean.FALSE).build();
    given(testCardRepository.findOne(1L)).willReturn(testCard);

    Boolean isCorrect = answerCardService.processAnswer(testCardAnswerDTO);
    assertThat(isCorrect).isTrue();
    assertThat(testCard.getIsCorrect()).isTrue();
    assertThat(testCard.getTestLevel()).isEqualTo(TestCardLevelEnum.MEDIUM.getValue());
  }

  @Test
  void shouldProcessAnswerReturnFalseWhenIncorrectAnswer() {
    TestCardAnswerDTO testCardAnswerDTO = TestCardAnswerDTO.builder().testCardId(1L).answer("incorrect").build();
    given(testCardRepository.findOne(1L)).willReturn(TestCard.builder().card(Card.builder().answer("Correct").build()).build());

    Boolean isCorrect = answerCardService.processAnswer(testCardAnswerDTO);
    assertThat(isCorrect).isFalse();
  }

  @Test
  void shouldThrowExceptionWhenTryProcessAnswerWithoutTestCardId() {
    TestCardAnswerDTO testCardAnswerDTO = TestCardAnswerDTO.builder().answer("incorrect").build();

    NullPointerException exception = assertThrows(NullPointerException.class, () -> answerCardService.processAnswer(testCardAnswerDTO));
    assertThat(exception.getMessage()).isEqualTo("Nie mozna dac odpowiedzi do nieznanej fiszki");
  }
}