package pl.michal.olszewski.flashcardsapp.cards.read;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.CardNotFoundException;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

@Service
public class CardReadService {

  private final CardFinder finder;
  private final ReadObjectMapper<Card, CardDTO> readObjectMapper;


  public CardReadService(CardFinder finder, @Qualifier("CardReadObjectMapper") ReadObjectMapper<Card, CardDTO> readObjectMapper) {
    this.finder = finder;
    this.readObjectMapper = readObjectMapper;
  }

  public CardDTO getCardById(long cardId) {
    Card cardById = findCardById(cardId);
    return readObjectMapper.convertToDTO(cardById);
  }

  private Card findCardById(long cardId) {
    return finder.findById(cardId).orElseThrow(() -> new CardNotFoundException(cardId));
  }

  public List<Card> findCardsByIds(List<Long> cardsIds) {
    return finder.findAll(cardsIds);
  }

}
