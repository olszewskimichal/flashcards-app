package pl.michal.olszewski.flashcardsapp.cards.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.CardNotFoundException;
import pl.michal.olszewski.flashcardsapp.cards.CardRepository;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CreateCardDTO;
import pl.michal.olszewski.flashcardsapp.cards.write.update.UpdateCardDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;
import pl.michal.olszewski.flashcardsapp.factory.card.CreateCardDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.card.UpdateCardDTOFactory;

@ExtendWith(MockitoExtension.class)
class CardWriteServiceTest {

  private CardWriteService cardWriteService;

  @Mock
  private CardRepository cardRepository;

  @Mock
  private WriteObjectMapper<Card, CreateCardDTO> writeObjectMapper;

  @BeforeEach
  void setUp() {
    cardWriteService = new CardWriteService(cardRepository, writeObjectMapper);
  }

  @Test
  void shouldCreateNewCard() {
    CreateCardDTO cardDTO = CreateCardDTOFactory.build("question3", "answer3");
    given(writeObjectMapper.convertFromDTO(cardDTO)).willReturn(Card.builder().answer("answer3").question("question3").build());
    Card card = cardWriteService.createCard(cardDTO);

    assertThat(card).isNotNull();
    verify(cardRepository, times(1)).save(card);
  }

  @Test
  void shouldUpdateCardFromCardDTO() {
    //given
    Card card = CardFactory.build(1L, "question12", "answer2");
    UpdateCardDTO cardDTO = UpdateCardDTOFactory.build(1L, "newQuestion", "newAnswer");
    given(cardRepository.findOne(1L)).willReturn(card);
    //when
    Card updatedCard = cardWriteService.updateCard(cardDTO);
    //then
    assertAll(
        () -> assertThat(updatedCard.getQuestion()).isEqualTo("newQuestion"),
        () -> assertThat(updatedCard.getId()).isEqualTo(1L),
        () -> assertThat(updatedCard.getAnswer()).isEqualTo("newAnswer")
    );
  }

  @Test
  void shouldThrowExceptionWhenUpdateNotExistingCard() {
    UpdateCardDTO cardDTO = UpdateCardDTOFactory.build(1L, "question5", "answer5");

    given(cardRepository.findOne(1L)).willReturn(null);

    CardNotFoundException cardNotFoundException = assertThrows(CardNotFoundException.class, () -> cardWriteService.updateCard(cardDTO));
    assertThat(cardNotFoundException.getMessage()).isEqualTo("Nie znalaziono fiszki o id = 1");
  }

}