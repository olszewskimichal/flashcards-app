package pl.michal.olszewski.flashcardsapp.topic;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.cards.CardObjectMapper;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.TopicDTO;

@Component("TopicObjectMapper")
public class TopicObjectMapper implements ObjectMapper<Topic, TopicDTO> {

  private final CardObjectMapper cardObjectMapper = new CardObjectMapper();

  @Override
  public Topic convertFromDTO(TopicDTO transferObject) {
    return Topic.builder().name(transferObject.getName()).build();
  }

  @Override
  public TopicDTO convertToDTO(Topic entity) {
    List<CardDTO> cards = entity.getCards().stream().map(cardObjectMapper::convertToDTO).collect(Collectors.toList());
    return TopicDTO.builder().id(entity.getId()).name(entity.getName()).cards(cards).build();
  }

  @Override
  public Topic updateFrom(TopicDTO transferObject, Topic entity) {
    entity.setName(transferObject.getName());
    return entity;
  }
}
