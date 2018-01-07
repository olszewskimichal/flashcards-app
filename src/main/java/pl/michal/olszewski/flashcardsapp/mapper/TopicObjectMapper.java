package pl.michal.olszewski.flashcardsapp.mapper;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicDTO;

@Component("TopicObjectMapper")
public class TopicObjectMapper implements ObjectMapper<Topic, TopicDTO> {

  private final CardObjectMapper cardObjectMapper = new CardObjectMapper();

  @Override
  public Topic convertFromDTO(TopicDTO transferObject) {
    return Topic.builder().name(transferObject.getName()).build();
  }

  @Override
  public TopicDTO convertToDTO(Topic entity) {
    return TopicDTO.builder().id(entity.getId()).name(entity.getName()).build();
  }

  @Override
  public Topic updateFrom(TopicDTO transferObject, Topic entity) {
    entity.setName(transferObject.getName());
    return entity;
  }
}
