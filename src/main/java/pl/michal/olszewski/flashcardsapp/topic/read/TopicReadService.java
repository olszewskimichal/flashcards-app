package pl.michal.olszewski.flashcardsapp.topic.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.TopicNotFoundException;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Service
public class TopicReadService {

  private final TopicFinder finder;
  private final ReadObjectMapper<Topic, TopicDTO> readObjectMapper;

  public TopicReadService(TopicFinder finder, @Qualifier("TopicReadObjectMapper") ReadObjectMapper<Topic, TopicDTO> readObjectMapper) {
    this.finder = finder;
    this.readObjectMapper = readObjectMapper;
  }

  public Topic findTopicById(Long id) {
    return finder.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
  }

  public TopicDTO getTopicById(long topicId) {
    Topic topic = findTopicById(topicId);
    return readObjectMapper.convertToDTO(topic);
  }

}
