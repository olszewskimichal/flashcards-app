package pl.michal.olszewski.flashcardsapp.cards.read;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;

@Component("CardObjectMapper")
public class CardReadObjectMapper implements ReadObjectMapper<Card, CardDTO> {

  @Override
  public CardDTO convertToDTO(Card entity) {
    return CardDTO.builder().question(entity.getQuestion()).answer(entity.getAnswer()).id(entity.getId()).build();
  }
}
