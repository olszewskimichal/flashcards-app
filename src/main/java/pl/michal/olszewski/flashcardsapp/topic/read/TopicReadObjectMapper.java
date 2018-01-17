package pl.michal.olszewski.flashcardsapp.topic.read;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.CardReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.cards.read.dto.CardDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Component("TopicReadObjectMapper")
public class TopicReadObjectMapper implements ReadObjectMapper<Topic, TopicDTO> {

  private final CardReadObjectMapper cardObjectMapper = new CardReadObjectMapper();

  @Override
  public TopicDTO convertToDTO(Topic entity) {
    List<CardDTO> cards = entity.getCards().stream().map(cardObjectMapper::convertToDTO).collect(Collectors.toList());
    return TopicDTO.builder().id(entity.getId()).name(entity.getName()).cards(cards).build();
  }

}
