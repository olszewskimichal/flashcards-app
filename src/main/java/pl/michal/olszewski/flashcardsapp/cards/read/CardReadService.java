package pl.michal.olszewski.flashcardsapp.cards.read;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.CardNotFoundException;
import pl.michal.olszewski.flashcardsapp.cards.CardRepository;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

@Service
public class CardReadService {

  private final CardRepository cardRepository;
  private final ReadObjectMapper<Card, CardDTO> readObjectMapper;


  public CardReadService(CardRepository cardRepository, @Qualifier("CardReadObjectMapper") ReadObjectMapper<Card, CardDTO> readObjectMapper) {
    this.cardRepository = cardRepository;
    this.readObjectMapper = readObjectMapper;
  }

  public CardDTO getCardById(long cardId) {
    Card cardById = findCardById(cardId);
    return readObjectMapper.convertToDTO(cardById);
  }

  private Card findCardById(long cardId) {
    Card card = cardRepository.findOne(cardId);
    if (card == null) {
      throw new CardNotFoundException(cardId);
    }
    return card;
  }

  public List<Card> findCardsByIds(List<Long> cardsIds) {
    return cardRepository.findAll(cardsIds);
  }

}
