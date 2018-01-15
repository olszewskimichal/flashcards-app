package pl.michal.olszewski.flashcardsapp.testcards.question;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.cards.Card;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;

class TestCardObjectMapperTestQuestion {

  private TestCardQuestionObjectMapper testCardQuestionObjectMapper = new TestCardQuestionObjectMapper();

  @Test
  void shouldConvertToDTO() {
    TestCard testCard = TestCard.builder()
        .id(2L)
        .attempt(Attempt.builder().id(1L).build())
        .card(Card.builder().question("question1").build())
        .build();
    TestCardQuestionDTO cardQuestionDTO = testCardQuestionObjectMapper.convertToDTO(testCard);

    assertThat(cardQuestionDTO.getAttemptId()).isNotNull().isEqualTo(1L);
    assertThat(cardQuestionDTO.getQuestion()).isNotEmpty().isEqualTo("question1");
    assertThat(cardQuestionDTO.getTestCardId()).isNotNull().isEqualTo(2L);
  }
}