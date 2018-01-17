package pl.michal.olszewski.flashcardsapp.topic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.read.Topic;
import pl.michal.olszewski.flashcardsapp.topic.read.TopicDTO;

@Service
public class TopicService {

  private final TopicRepository topicRepository;
  private final ReadObjectMapper<Topic, TopicDTO> readObjectMapper;
  private final WriteObjectMapper<Topic, TopicDTO> writeObjectMapper;

  public TopicService(TopicRepository topicRepository, @Qualifier("TopicObjectMapper") ReadObjectMapper<Topic, TopicDTO> readObjectMapper,
      WriteObjectMapper<Topic, TopicDTO> writeObjectMapper) {
    this.topicRepository = topicRepository;
    this.readObjectMapper = readObjectMapper;
    this.writeObjectMapper = writeObjectMapper;
  }

  public Topic createTopic(TopicDTO topicDTO) {
    Topic topic = writeObjectMapper.convertFromDTO(topicDTO);
    topicRepository.save(topic);
    return topic;
  }

  public Topic updateTopic(TopicDTO topicDTO) {
    Topic topic = findTopicById(topicDTO.getId());
    topic.setName(topicDTO.getName());
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
    return readObjectMapper.convertToDTO(topic);
  }

}
