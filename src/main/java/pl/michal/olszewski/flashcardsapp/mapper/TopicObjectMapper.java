package pl.michal.olszewski.flashcardsapp.mapper;

import java.util.stream.Collectors;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicDTO;

public class TopicObjectMapper implements ObjectMapper<Topic, TopicDTO> {

  private final CardObjectMapper cardObjectMapper = new CardObjectMapper();

  @Override
  public Topic convertFromDTO(TopicDTO transferObject) {
    return Topic.builder().cards(transferObject.getCards().stream().map(cardObjectMapper::convertFromDTO).collect(Collectors.toList())).build();
  }

  @Override
  public TopicDTO convertToDTO(Topic entity) {
    return TopicDTO.builder().cards(entity.getCards().stream().map(cardObjectMapper::convertToDTO).collect(Collectors.toList())).build();
  }

  @Override
  public Topic updateFrom(TopicDTO transferObject, Topic entity) {
    transferObject.getCards().stream().filter(v -> v.getId() != null).forEach(v ->
        entity.getCards().stream().filter(e -> e.getId().equals(v.getId())).findAny().ifPresent(card -> cardObjectMapper.updateFrom(v, card))
    );
    transferObject.getCards().stream().filter(v -> v.getId() == null).forEach(v -> entity.addCard(cardObjectMapper.convertFromDTO(v)));
    return entity;
  }
}
