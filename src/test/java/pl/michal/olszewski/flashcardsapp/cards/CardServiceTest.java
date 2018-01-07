package pl.michal.olszewski.flashcardsapp.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.mapper.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

  private CardService cardService;

  @Mock
  private CardRepository cardRepository;

  @Mock
  private ObjectMapper<Card, CardDTO> objectMapper;

  @BeforeEach
  void setUp() {
    cardService = new CardService(cardRepository, objectMapper);
  }

  @Test
  void shouldCreateNewCard() {
    CardDTO cardDTO = CardDTO.builder().answer("answer3").question("question3").build();
    given(objectMapper.convertFromDTO(cardDTO)).willReturn(Card.builder().answer("answer3").question("question3").build());
    Card card = cardService.createCard(cardDTO);

    assertThat(card).isNotNull();
    verify(cardRepository, times(1)).save(card);
  }

  @Test
  void shouldUpdateCard() {
    CardDTO cardDTO = CardDTO.builder().answer("answer4").question("question4").id(1L).build();
    Card card = Card.builder().id(1L).question("old").answer("oldAnswer").build();
    given(cardRepository.findOne(1L)).willReturn(card);
    given(objectMapper.updateFrom(cardDTO, card)).willReturn(Card.builder().answer("answer4").question("question4").id(1L).build());

    Card updateCard = cardService.updateCard(cardDTO);
    assertThat(updateCard).isNotNull();
    verify(cardRepository, times(1)).findOne(1L);
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
    given(objectMapper.convertToDTO(card)).willReturn(CardDTO.builder().id(2L).question("question6").answer("answer6").build());
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
}