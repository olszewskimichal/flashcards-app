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
import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CardCreateDTO;
import pl.michal.olszewski.flashcardsapp.cards.write.update.CardUpdateDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardWriteServiceTest {

  private CardWriteService cardWriteService;

  @Mock
  private CardRepository cardRepository;

  @Mock
  private WriteObjectMapper<Card, CardCreateDTO> writeObjectMapper;

  @BeforeEach
  void setUp() {
    cardWriteService = new CardWriteService(cardRepository, writeObjectMapper);
  }

  @Test
  void shouldCreateNewCard() {
    CardCreateDTO cardDTO = CardCreateDTO.builder().answer("answer3").question("question3").build();
    given(writeObjectMapper.convertFromDTO(cardDTO)).willReturn(Card.builder().answer("answer3").question("question3").build());
    Card card = cardWriteService.createCard(cardDTO);

    assertThat(card).isNotNull();
    verify(cardRepository, times(1)).save(card);
  }

  @Test
  void shouldUpdateCard() {
    CardUpdateDTO cardDTO = CardUpdateDTO.builder().answer("answer4").question("question4").id(1L).build();
    Card card = Card.builder().id(1L).question("old").answer("oldAnswer").build();
    given(cardRepository.findOne(1L)).willReturn(card);

    Card updateCard = cardWriteService.updateCard(cardDTO);
    assertThat(updateCard).isNotNull();
    verify(cardRepository, times(1)).findOne(1L);
  }

  @Test
  void shouldUpdateCardFromCardDTO() {
    //given
    Card card = Card.builder().answer("answer2").question("question12").id(1L).build();
    CardUpdateDTO cardDTO = CardUpdateDTO.builder().id(1L).question("newQuestion").answer("newAnswer").build();
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
    CardUpdateDTO cardDTO = CardUpdateDTO.builder().answer("answer5").question("question5").id(1L).build();
    given(cardRepository.findOne(1L)).willReturn(null);

    CardNotFoundException cardNotFoundException = assertThrows(CardNotFoundException.class, () -> cardWriteService.updateCard(cardDTO));
    assertThat(cardNotFoundException.getMessage()).isEqualTo("Nie znalaziono fiszki o id = 1");
  }

}