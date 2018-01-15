package pl.michal.olszewski.flashcardsapp.examcards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.examcards.answer.AnswerCardService;
import pl.michal.olszewski.flashcardsapp.examcards.answer.ExamCardAnswerDTO;

@ExtendWith(MockitoExtension.class)
class AnswerCardServiceTest {

  @Mock
  private ExamCardRepository examCardRepository;

  private AnswerCardService answerCardService;

  @BeforeEach
  void setUp() {
    answerCardService = new AnswerCardService(examCardRepository);
  }

  @Test
  void shouldProcessAnswerReturnTrueWhenCorrectAnswer() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTO.builder().examCardId(1L).answer("correct").build();
    ExamCard examCard = ExamCard.builder().card(Card.builder().answer("Correct").build()).testLevel(ExamCardLevelEnum.BAD.getValue()).isCorrect(Boolean.FALSE).build();
    given(examCardRepository.findOne(1L)).willReturn(examCard);

    Boolean isCorrect = answerCardService.processAnswer(examCardAnswerDTO);
    assertThat(isCorrect).isTrue();
    assertThat(examCard.getIsCorrect()).isTrue();
    assertThat(examCard.getTestLevel()).isEqualTo(ExamCardLevelEnum.MEDIUM.getValue());
  }

  @Test
  void shouldProcessAnswerReturnFalseWhenIncorrectAnswer() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTO.builder().examCardId(1L).answer("incorrect").build();
    given(examCardRepository.findOne(1L)).willReturn(ExamCard.builder().card(Card.builder().answer("Correct").build()).build());

    Boolean isCorrect = answerCardService.processAnswer(examCardAnswerDTO);
    assertThat(isCorrect).isFalse();
  }

  @Test
  void shouldThrowExceptionWhenTryProcessAnswerWithoutTestCardId() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTO.builder().answer("incorrect").build();

    NullPointerException exception = assertThrows(NullPointerException.class, () -> answerCardService.processAnswer(examCardAnswerDTO));
    assertThat(exception.getMessage()).isEqualTo("Nie mozna dac odpowiedzi do nieznanej fiszki");
  }
}