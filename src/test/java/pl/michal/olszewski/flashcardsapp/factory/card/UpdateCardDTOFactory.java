package pl.michal.olszewski.flashcardsapp.factory.card;

import pl.michal.olszewski.flashcardsapp.cards.write.update.UpdateCardDTO;

public class UpdateCardDTOFactory {

  public static UpdateCardDTO build(Long id, String question, String answer) {
    return UpdateCardDTO.builder()
        .id(id)
        .answer(answer)
        .question(question)
        .build();
  }

}
