package pl.michal.olszewski.flashcardsapp.cards.write;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.CardNotFoundException;
import pl.michal.olszewski.flashcardsapp.cards.CardRepository;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CardCreateDTO;
import pl.michal.olszewski.flashcardsapp.cards.write.update.CardUpdateDTO;

@Service
public class CardWriteService {

  private final CardRepository cardRepository;
  private final WriteObjectMapper<Card, CardCreateDTO> writeObjectMapper;


  public CardWriteService(CardRepository cardRepository, @Qualifier("CardCreateObjectMapper") WriteObjectMapper<Card, CardCreateDTO> writeObjectMapper) {
    this.cardRepository = cardRepository;
    this.writeObjectMapper = writeObjectMapper;
  }

  public Card createCard(CardCreateDTO cardDTO) {
    Card card = writeObjectMapper.convertFromDTO(cardDTO);
    cardRepository.save(card);
    return card;
  }

  public Card updateCard(CardUpdateDTO cardDTO) {
    Card card = findCardById(cardDTO.getId());
    card.setAnswer(cardDTO.getAnswer());
    card.setQuestion(cardDTO.getQuestion());
    return card;
  }

  private Card findCardById(long cardId) {
    Card card = cardRepository.findOne(cardId);
    if (card == null) {
      throw new CardNotFoundException(cardId);
    }
    return card;
  }

}
