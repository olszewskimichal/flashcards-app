package pl.michal.olszewski.flashcardsapp.cards.write.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CreateCardDTO;
import pl.michal.olszewski.flashcardsapp.factory.card.CreateCardDTOFactory;


class CardCreateObjectMapperTest {

  private CardCreateObjectMapper mapper = new CardCreateObjectMapper();

  @Test
  void shouldConvertFromCardDTO() {
    //given
    CreateCardDTO cardDTO = CreateCardDTOFactory.build("question", "answer");
    //when
    Card card = mapper.convertFromDTO(cardDTO);
    //then
    assertAll(
        () -> assertThat(card.getQuestion()).isEqualTo("question"),
        () -> assertThat(card.getAnswer()).isEqualTo("answer")
    );
  }
}