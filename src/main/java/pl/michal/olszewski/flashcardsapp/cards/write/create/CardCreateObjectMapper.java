package pl.michal.olszewski.flashcardsapp.cards.write.create;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CardCreateDTO;

@Component("CardCreateObjectMapper")
public class CardCreateObjectMapper implements WriteObjectMapper<Card, CardCreateDTO> {

  @Override
  public Card convertFromDTO(CardCreateDTO transferObject) {
    return Card.builder().answer(transferObject.getAnswer()).question(transferObject.getQuestion()).build();
  }

}
