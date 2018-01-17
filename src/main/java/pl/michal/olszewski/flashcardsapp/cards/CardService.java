package pl.michal.olszewski.flashcardsapp.cards;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;

@Service
public class CardService {

  private final CardRepository cardRepository;
  private final ReadObjectMapper<Card, CardDTO> readObjectMapper;
  private final WriteObjectMapper<Card, CardDTO> writeObjectMapper;


  public CardService(CardRepository cardRepository, @Qualifier("CardObjectMapper") ReadObjectMapper<Card, CardDTO> readObjectMapper,
      WriteObjectMapper<Card, CardDTO> writeObjectMapper) {
    this.cardRepository = cardRepository;
    this.readObjectMapper = readObjectMapper;
    this.writeObjectMapper = writeObjectMapper;
  }

  public Card createCard(CardDTO cardDTO) {
    Card card = writeObjectMapper.convertFromDTO(cardDTO);
    cardRepository.save(card);
    return card;
  }

  public Card updateCard(CardDTO cardDTO) {
    Card card = findCardById(cardDTO.getId());
    card.setAnswer(cardDTO.getAnswer());
    card.setQuestion(cardDTO.getQuestion());
    return card;
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
