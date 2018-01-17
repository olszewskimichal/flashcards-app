package pl.michal.olszewski.flashcardsapp.topic.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.TopicNotFoundException;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Service
public class TopicReadService {

  private final TopicRepository topicRepository;
  private final ReadObjectMapper<Topic, TopicDTO> readObjectMapper;

  public TopicReadService(TopicRepository topicRepository, @Qualifier("TopicReadObjectMapper") ReadObjectMapper<Topic, TopicDTO> readObjectMapper) {
    this.topicRepository = topicRepository;
    this.readObjectMapper = readObjectMapper;
  }

  public Topic findTopicById(Long id) {
    Topic topic = topicRepository.findOne(id);
    if (topic == null) {
      throw new TopicNotFoundException(id);
    }
    return topic;
  }

  public TopicDTO getTopicById(long topicId) {
    Topic topic = findTopicById(topicId);
    return readObjectMapper.convertToDTO(topic);
  }

}
