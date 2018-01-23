package pl.michal.olszewski.flashcardsapp.examcards.read;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.examcards.read.dto.ExamCardQuestionDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.factory.attempt.AttemptFactory;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;
import pl.michal.olszewski.flashcardsapp.factory.examcards.ExamCardFactory;

class ExamCardQuestionObjectMapperTest {

  private ExamCardQuestionObjectMapper examCardQuestionObjectMapper = new ExamCardQuestionObjectMapper();

  @Test
  void shouldConvertToDTO() {
    ExamCard examCard = ExamCardFactory.build(2L, AttemptFactory.build(1L), CardFactory.build(1L, "question1", "answer"), 2L);
    ExamCardQuestionDTO cardQuestionDTO = examCardQuestionObjectMapper.convertToDTO(examCard);

    assertThat(cardQuestionDTO.getAttemptId()).isNotNull().isEqualTo(1L);
    assertThat(cardQuestionDTO.getQuestion()).isNotEmpty().isEqualTo("question1");
    assertThat(cardQuestionDTO.getTestCardId()).isNotNull().isEqualTo(2L);
  }
}