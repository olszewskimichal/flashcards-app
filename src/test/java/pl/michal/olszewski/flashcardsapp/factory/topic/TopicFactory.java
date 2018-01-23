package pl.michal.olszewski.flashcardsapp.factory.topic;

import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

public class TopicFactory {

  public static Topic build(Long id, String name) {
    return Topic.builder()
        .id(id)
        .name(name)
        .build();
  }

}
