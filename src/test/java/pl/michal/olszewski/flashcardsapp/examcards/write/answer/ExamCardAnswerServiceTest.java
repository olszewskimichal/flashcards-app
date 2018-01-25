package pl.michal.olszewski.flashcardsapp.examcards.write.answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.examcards.ExamCardNotFoundException;
import pl.michal.olszewski.flashcardsapp.examcards.read.ExamCardFinder;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCardLevelEnum;
import pl.michal.olszewski.flashcardsapp.examcards.write.answer.dto.ExamCardAnswerDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;
import pl.michal.olszewski.flashcardsapp.factory.examcards.ExamCardAnswerDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.examcards.ExamCardFactory;

@ExtendWith(MockitoExtension.class)
class ExamCardAnswerServiceTest {

  @Mock
  private ExamCardFinder examCardRepository;

  private ExamCardAnswerService examCardAnswerService;

  @BeforeEach
  void setUp() {
    examCardAnswerService = new ExamCardAnswerService(examCardRepository);
  }

  @Test
  void shouldProcessAnswerReturnTrueWhenCorrectAnswer() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTOFactory.build(1L, "correct");
    ExamCard examCard = ExamCardFactory.build(1L, null, CardFactory.build(null, "q", "correct"), ExamCardLevelEnum.BAD.getValue(), Boolean.FALSE);

    given(examCardRepository.findById(1L)).willReturn(Optional.of(examCard));

    Boolean isCorrect = examCardAnswerService.processAnswer(examCardAnswerDTO);
    assertThat(isCorrect).isTrue();
    assertThat(examCard.getIsCorrect()).isTrue();
    assertThat(examCard.getTestLevel()).isEqualTo(ExamCardLevelEnum.MEDIUM.getValue());
  }

  @Test
  void shouldProcessAnswerReturnFalseWhenIncorrectAnswer() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTOFactory.build(1L, "incorrect");
    given(examCardRepository.findById(1L)).willReturn(Optional.of(ExamCard.builder().card(CardFactory.build(null, "q", "correct")).build()));

    Boolean isCorrect = examCardAnswerService.processAnswer(examCardAnswerDTO);
    assertThat(isCorrect).isFalse();
  }

  @Test
  void shouldThrowExceptionWhenProcessAnswerWithIncorrectExamCard() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTOFactory.build(1L, "incorrect");
    given(examCardRepository.findById(1L)).willReturn(Optional.empty());
    assertThrows(ExamCardNotFoundException.class, () -> examCardAnswerService.processAnswer(examCardAnswerDTO));
  }

  @Test
  void shouldThrowExceptionWhenTryProcessAnswerWithoutTestCardId() {
    ExamCardAnswerDTO examCardAnswerDTO = ExamCardAnswerDTOFactory.build(null, "incorrect");

    NullPointerException exception = assertThrows(NullPointerException.class, () -> examCardAnswerService.processAnswer(examCardAnswerDTO));
    assertThat(exception.getMessage()).isEqualTo("Nie mozna dac odpowiedzi do nieznanej fiszki");
  }
}