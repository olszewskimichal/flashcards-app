package pl.michal.olszewski.flashcardsapp.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;


class CardObjectMapperTest {

  private CardObjectMapper mapper = new CardObjectMapper();

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

  @Test
  void shouldUpdateCardFromCardDTO() {
    //given
    Card card = Card.builder().answer("answer2").question("question12").id(1L).build();
    CardDTO cardDTO = CardDTO.builder().question("newQuestion").answer("newAnswer").build();
    //when
    mapper.updateFrom(cardDTO, card);
    //then
    assertAll(
        () -> assertThat(card.getQuestion()).isEqualTo("newQuestion"),
        () -> assertThat(card.getId()).isEqualTo(1L),
        () -> assertThat(card.getAnswer()).isEqualTo("newAnswer")
    );
  }
}