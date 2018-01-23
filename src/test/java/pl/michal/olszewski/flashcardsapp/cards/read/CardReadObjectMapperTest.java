package pl.michal.olszewski.flashcardsapp.cards.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;


class CardReadObjectMapperTest {

  private CardReadObjectMapper mapper = new CardReadObjectMapper();

  @Test
  void shouldConvertCardToCardDTO() {
    //given
    Card card = CardFactory.build(1L, "question1", "answer1");
    //when
    CardDTO cardDTO = mapper.convertToDTO(card);
    //then
    assertAll(
        () -> assertThat(cardDTO.getQuestion()).isEqualTo("question1"),
        () -> assertThat(cardDTO.getAnswer()).isEqualTo("answer1"),
        () -> assertThat(cardDTO.getId()).isEqualTo(1L)
    );
  }

}