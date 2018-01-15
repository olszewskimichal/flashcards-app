package pl.michal.olszewski.flashcardsapp.topic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.TopicDTO;

@Service
public class TopicService {

  private final TopicRepository topicRepository;
  private final ObjectMapper<Topic, TopicDTO> objectMapper;

  public TopicService(TopicRepository topicRepository, @Qualifier("TopicObjectMapper") ObjectMapper<Topic, TopicDTO> objectMapper) {
    this.topicRepository = topicRepository;
    this.objectMapper = objectMapper;
  }

  public Topic createTopic(TopicDTO topicDTO) {
    Topic topic = objectMapper.convertFromDTO(topicDTO);
    topicRepository.save(topic);
    return topic;
  }

  public Topic updateTopic(TopicDTO topicDTO) {
    Topic topic = findTopicById(topicDTO.getId());
    objectMapper.updateFrom(topicDTO, topic);
    return topic;
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
    return objectMapper.convertToDTO(topic);
  }

}
