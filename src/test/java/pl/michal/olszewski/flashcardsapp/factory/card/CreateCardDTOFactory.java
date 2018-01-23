package pl.michal.olszewski.flashcardsapp.factory.card;

import pl.michal.olszewski.flashcardsapp.cards.write.create.dto.CreateCardDTO;

public class CreateCardDTOFactory {

  public static CreateCardDTO build(String question, String answer) {
    return CreateCardDTO.builder()
        .answer(answer)
        .question(question)
        .build();
  }

}
