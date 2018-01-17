package pl.michal.olszewski.flashcardsapp.cards.write.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CardCreateDTO;


class CardCreateObjectMapperTest {

  private CardCreateObjectMapper mapper = new CardCreateObjectMapper();

  @Test
  void shouldConvertFromCardDTO() {
    //given
    CardCreateDTO cardDTO = CardCreateDTO.builder().question("question").answer("answer").build();
    //when
    Card card = mapper.convertFromDTO(cardDTO);
    //then
    assertAll(
        () -> assertThat(card.getQuestion()).isEqualTo("question"),
        () -> assertThat(card.getAnswer()).isEqualTo("answer")
    );
  }
}