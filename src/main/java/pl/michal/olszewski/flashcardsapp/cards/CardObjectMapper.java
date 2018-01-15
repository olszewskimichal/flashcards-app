package pl.michal.olszewski.flashcardsapp.cards;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;

@Component("CardObjectMapper")
public class CardObjectMapper implements ObjectMapper<Card, CardDTO> {

  @Override
  public Card convertFromDTO(CardDTO transferObject) {
    return Card.builder().id(transferObject.getId()).answer(transferObject.getAnswer()).question(transferObject.getQuestion()).build();
  }

  @Override
  public CardDTO convertToDTO(Card entity) {
    return CardDTO.builder().question(entity.getQuestion()).answer(entity.getAnswer()).id(entity.getId()).build();
  }

  @Override
  public Card updateFrom(CardDTO transferObject, Card entity) {
    entity.setAnswer(transferObject.getAnswer());
    entity.setQuestion(transferObject.getQuestion());
    return entity;
  }
}
