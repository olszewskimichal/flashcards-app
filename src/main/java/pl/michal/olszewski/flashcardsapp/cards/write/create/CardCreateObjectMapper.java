package pl.michal.olszewski.flashcardsapp.cards.write.create;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;

@Component
public class CardCreateObjectMapper implements WriteObjectMapper<Card, CardDTO> {

  @Override
  public Card convertFromDTO(CardDTO transferObject) {
    return Card.builder().id(transferObject.getId()).answer(transferObject.getAnswer()).question(transferObject.getQuestion()).build();
  }

}
