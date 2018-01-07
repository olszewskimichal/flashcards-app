package pl.michal.olszewski.flashcardsapp.cards;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.mapper.ObjectMapper;

@Service
public class CardService {

  private final CardRepository cardRepository;
  private final ObjectMapper<Card, CardDTO> objectMapper;

  public CardService(CardRepository cardRepository, @Qualifier("CardObjectMapper") ObjectMapper<Card, CardDTO> objectMapper) {
    this.cardRepository = cardRepository;
    this.objectMapper = objectMapper;
  }

  public Card createCard(CardDTO cardDTO) {
    Card card = objectMapper.convertFromDTO(cardDTO);
    cardRepository.save(card);
    return card;
  }

  public Card updateCard(CardDTO cardDTO) {
    Card card = findCardById(cardDTO.getId());
    objectMapper.updateFrom(cardDTO, card);
    return card;
  }

  public CardDTO getCardById(long cardId) {
    Card cardById = findCardById(cardId);
    return objectMapper.convertToDTO(cardById);
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
