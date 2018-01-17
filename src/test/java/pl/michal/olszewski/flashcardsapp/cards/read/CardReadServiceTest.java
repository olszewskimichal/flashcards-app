package pl.michal.olszewski.flashcardsapp.cards.read;

import static org.assertj.core.api.Assertions.assertThat;
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
import pl.michal.olszewski.flashcardsapp.cards.CardNotFoundException;
import pl.michal.olszewski.flashcardsapp.cards.CardRepository;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CardReadServiceTest {

  private CardReadService cardReadService;

  @Mock
  private CardRepository cardRepository;

  @Mock
  private ReadObjectMapper<Card, CardDTO> readObjectMapper;

  @BeforeEach
  void setUp() {
    cardReadService = new CardReadService(cardRepository, readObjectMapper);
  }

  @Test
  void shouldReturnCardDTOById() {
    Card card = Card.builder().question("question6").answer("answer6").build();
    given(cardRepository.findOne(2L)).willReturn(card);
    given(readObjectMapper.convertToDTO(card)).willReturn(CardDTO.builder().id(2L).question("question6").answer("answer6").build());
    CardDTO cardDTO = cardReadService.getCardById(2L);

    assertThat(cardDTO).isNotNull();
    assertThat(cardDTO.getId()).isEqualTo(2L);
    verify(cardRepository, times(1)).findOne(2L);
  }

  @Test
  void shouldReturnEmptyListWhenTryFindCardsByNotExistingIds() {
    given(cardRepository.findAll(Arrays.asList(1L, 2L))).willReturn(new ArrayList<>());
    List<Card> cardsByIds = cardReadService.findCardsByIds(Arrays.asList(1L, 2L));
    assertThat(cardsByIds).hasSize(0).isEmpty();
  }

  @Test
  void shouldCardsListWhenFindCardsByIds() {
    given(cardRepository.findAll(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(Card.builder().id(1L).build(), Card.builder().id(2L).build()));
    List<Card> cardsByIds = cardReadService.findCardsByIds(Arrays.asList(1L, 2L));
    assertThat(cardsByIds).hasSize(2);
  }


  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(cardRepository.findOne(1L)).willReturn(null);
    CardNotFoundException cardNotFoundException = assertThrows(CardNotFoundException.class, () -> cardReadService.getCardById(2L));
    assertThat(cardNotFoundException.getMessage()).isEqualTo("Nie znalaziono fiszki o id = 2");
  }

}
