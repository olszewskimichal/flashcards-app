package pl.michal.olszewski.flashcardsapp.factory.exam;

import pl.michal.olszewski.flashcardsapp.exam.write.dto.create.CreateExamDTO;

public class CreateExamDTOFactory {

  public static CreateExamDTO build(Long topicId) {
    return CreateExamDTO.builder()
        .topicId(topicId)
        .build();
  }

}
