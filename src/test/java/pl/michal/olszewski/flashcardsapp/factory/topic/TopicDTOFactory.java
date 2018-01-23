package pl.michal.olszewski.flashcardsapp.factory.topic;

import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;

public class TopicDTOFactory {

  public static TopicDTO build(Long id, String name) {
    return TopicDTO.builder()
        .id(id)
        .name(name)
        .build();
  }

}
