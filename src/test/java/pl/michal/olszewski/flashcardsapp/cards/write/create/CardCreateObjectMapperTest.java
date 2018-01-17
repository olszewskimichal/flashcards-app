package pl.michal.olszewski.flashcardsapp.cards.write.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;


class CardCreateObjectMapperTest {

  private CardCreateObjectMapper mapper = new CardCreateObjectMapper();

  @Test
  void shouldConvertFromCardDTO() {
    //given
    CardDTO cardDTO = CardDTO.builder().question("question").answer("answer").id(1L).build();
    //when
    Card card = mapper.convertFromDTO(cardDTO);
    //then
    assertAll(
        () -> assertThat(card.getQuestion()).isEqualTo("question"),
        () -> assertThat(card.getAnswer()).isEqualTo("answer"),
        () -> assertThat(card.getId()).isEqualTo(1L)
    );
  }
}