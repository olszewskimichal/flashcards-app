package pl.michal.olszewski.flashcardsapp.topic.write;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Component("TopicWriteObjectMapper")
public class TopicWriteObjectMapper implements WriteObjectMapper<Topic, TopicDTO> {

  @Override
  public Topic convertFromDTO(TopicDTO transferObject) {
    return Topic.builder().name(transferObject.getName()).build();
  }
}
