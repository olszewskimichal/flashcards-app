package pl.michal.olszewski.flashcardsapp.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

  private CardService cardService;

  @Mock
  private CardRepository cardRepository;

  @Mock
  private ReadObjectMapper<Card, CardDTO> readObjectMapper;

  @Mock
  private WriteObjectMapper<Card, CardDTO> writeObjectMapper;

  @BeforeEach
  void setUp() {
    cardService = new CardService(cardRepository, readObjectMapper, writeObjectMapper);
  }

  @Test
  void shouldCreateNewCard() {
    CardDTO cardDTO = CardDTO.builder().answer("answer3").question("question3").build();
    given(writeObjectMapper.convertFromDTO(cardDTO)).willReturn(Card.builder().answer("answer3").question("question3").build());
    Card card = cardService.createCard(cardDTO);

    assertThat(card).isNotNull();
    verify(cardRepository, times(1)).save(card);
  }

  @Test
  void shouldUpdateCard() {
    CardDTO cardDTO = CardDTO.builder().answer("answer4").question("question4").id(1L).build();
    Card card = Card.builder().id(1L).question("old").answer("oldAnswer").build();
    given(cardRepository.findOne(1L)).willReturn(card);

    Card updateCard = cardService.updateCard(cardDTO);
    assertThat(updateCard).isNotNull();
    verify(cardRepository, times(1)).findOne(1L);
  }

  @Test
  void shouldUpdateCardFromCardDTO() {
    //given
    Card card = Card.builder().answer("answer2").question("question12").id(1L).build();
    CardDTO cardDTO = CardDTO.builder().id(1L).question("newQuestion").answer("newAnswer").build();
    given(cardRepository.findOne(1L)).willReturn(card);
    //when
    Card updatedCard = cardService.updateCard(cardDTO);
    //then
    assertAll(
        () -> assertThat(updatedCard.getQuestion()).isEqualTo("newQuestion"),
        () -> assertThat(updatedCard.getId()).isEqualTo(1L),
        () -> assertThat(updatedCard.getAnswer()).isEqualTo("newAnswer")
    );
  }

  @Test
  void shouldThrowExceptionWhenUpdateNotExistingCard() {
    CardDTO cardDTO = CardDTO.builder().answer("answer5").question("question5").id(1L).build();
    given(cardRepository.findOne(1L)).willReturn(null);

    CardNotFoundException cardNotFoundException = assertThrows(CardNotFoundException.class, () -> cardService.updateCard(cardDTO));
    assertThat(cardNotFoundException.getMessage()).isEqualTo("Nie znalaziono fiszki o id = 1");
  }

  @Test
  void shouldReturnCardDTOById() {
    Card card = Card.builder().question("question6").answer("answer6").build();
    given(cardRepository.findOne(2L)).willReturn(card);
    given(readObjectMapper.convertToDTO(card)).willReturn(CardDTO.builder().id(2L).question("question6").answer("answer6").build());
    CardDTO cardDTO = cardService.getCardById(2L);

    assertThat(cardDTO).isNotNull();
    assertThat(cardDTO.getId()).isEqualTo(2L);
    verify(cardRepository, times(1)).findOne(2L);
  }

  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(cardRepository.findOne(1L)).willReturn(null);
    CardNotFoundException cardNotFoundException = assertThrows(CardNotFoundException.class, () -> cardService.getCardById(2L));
    assertThat(cardNotFoundException.getMessage()).isEqualTo("Nie znalaziono fiszki o id = 2");
  }

  @Test
  void shouldReturnEmptyListWhenTryFindCardsByNotExistingIds() {
    given(cardRepository.findAll(Arrays.asList(1L, 2L))).willReturn(new ArrayList<>());
    List<Card> cardsByIds = cardService.findCardsByIds(Arrays.asList(1L, 2L));
    assertThat(cardsByIds).hasSize(0).isEmpty();
  }

  @Test
  void shouldCardsListWhenFindCardsByIds() {
    given(cardRepository.findAll(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(Card.builder().id(1L).build(), Card.builder().id(2L).build()));
    List<Card> cardsByIds = cardService.findCardsByIds(Arrays.asList(1L, 2L));
    assertThat(cardsByIds).hasSize(2);
  }
}