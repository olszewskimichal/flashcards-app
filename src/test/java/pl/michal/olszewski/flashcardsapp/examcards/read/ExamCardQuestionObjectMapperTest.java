package pl.michal.olszewski.flashcardsapp.examcards.read;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.examcards.read.dto.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;

class ExamCardQuestionObjectMapperTest {

  private ExamCardQuestionObjectMapper examCardQuestionObjectMapper = new ExamCardQuestionObjectMapper();

  @Test
  void shouldConvertToDTO() {
    ExamCard examCard = ExamCard.builder()
        .id(2L)
        .attempt(Attempt.builder().id(1L).build())
        .card(Card.builder().question("question1").build())
        .build();
    ExamCardQuestionDTO cardQuestionDTO = examCardQuestionObjectMapper.convertToDTO(examCard);

    assertThat(cardQuestionDTO.getAttemptId()).isNotNull().isEqualTo(1L);
    assertThat(cardQuestionDTO.getQuestion()).isNotEmpty().isEqualTo("question1");
    assertThat(cardQuestionDTO.getTestCardId()).isNotNull().isEqualTo(2L);
  }
}