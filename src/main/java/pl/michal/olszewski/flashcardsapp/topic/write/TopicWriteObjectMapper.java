package pl.michal.olszewski.flashcardsapp.topic.write;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.read.Topic;
import pl.michal.olszewski.flashcardsapp.topic.read.TopicDTO;

@Component
public class TopicWriteObjectMapper implements WriteObjectMapper<Topic, TopicDTO> {

  @Override
  public Topic convertFromDTO(TopicDTO transferObject) {
    return Topic.builder().name(transferObject.getName()).build();
  }
}
