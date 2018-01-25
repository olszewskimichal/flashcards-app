package pl.michal.olszewski.flashcardsapp.cards.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.CardNotFoundException;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;


@ExtendWith(MockitoExtension.class)
class CardReadServiceTest {

  private CardReadService cardReadService;

  @Mock
  private CardFinder finder;

  @Mock
  private ReadObjectMapper<Card, CardDTO> readObjectMapper;

  @BeforeEach
  void setUp() {
    cardReadService = new CardReadService(finder, readObjectMapper);
  }

  @Test
  void shouldReturnCardDTOById() {
    Card card = CardFactory.build(2L, "question6", "answer6");
    given(finder.findById(2L)).willReturn(Optional.of(card));
    given(readObjectMapper.convertToDTO(card)).willReturn(CardDTO.builder().id(2L).question("question6").answer("answer6").build());
    CardDTO cardDTO = cardReadService.getCardById(2L);

    assertThat(cardDTO).isNotNull();
    assertThat(cardDTO.getId()).isEqualTo(2L);
    verify(finder, times(1)).findById(2L);
  }

  @Test
  void shouldReturnEmptyListWhenTryFindCardsByNotExistingIds() {
    given(finder.findAll(Arrays.asList(1L, 2L))).willReturn(new ArrayList<>());
    List<Card> cardsByIds = cardReadService.findCardsByIds(Arrays.asList(1L, 2L));
    assertThat(cardsByIds).hasSize(0).isEmpty();
  }

  @Test
  void shouldCardsListWhenFindCardsByIds() {
    given(finder.findAll(Arrays.asList(1L, 2L))).willReturn(Arrays.asList(CardFactory.build(1L, null, null), CardFactory.build(2L, null, null)));
    List<Card> cardsByIds = cardReadService.findCardsByIds(Arrays.asList(1L, 2L));
    assertThat(cardsByIds).hasSize(2);
  }


  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(finder.findById(2L)).willReturn(Optional.empty());
    CardNotFoundException cardNotFoundException = assertThrows(CardNotFoundException.class, () -> cardReadService.getCardById(2L));
    assertThat(cardNotFoundException.getMessage()).isEqualTo("Nie znalaziono fiszki o id = 2");
  }

}
