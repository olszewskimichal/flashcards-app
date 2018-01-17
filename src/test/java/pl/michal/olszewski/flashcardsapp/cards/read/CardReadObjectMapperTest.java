package pl.michal.olszewski.flashcardsapp.cards.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;


class CardReadObjectMapperTest {

  private CardReadObjectMapper mapper = new CardReadObjectMapper();

  @Test
  void shouldConvertCardToCardDTO() {
    //given
    Card card = Card.builder().answer("answer1").question("question1").id(1L).build();
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