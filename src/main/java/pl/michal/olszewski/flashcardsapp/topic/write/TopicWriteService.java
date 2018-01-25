package pl.michal.olszewski.flashcardsapp.topic.write;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.TopicNotFoundException;
import pl.michal.olszewski.flashcardsapp.topic.read.dto.TopicDTO;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Service
public class TopicWriteService {

  private final TopicRepository topicRepository;
  private final WriteObjectMapper<Topic, TopicDTO> writeObjectMapper;

  public TopicWriteService(TopicRepository topicRepository, @Qualifier("TopicWriteObjectMapper") WriteObjectMapper<Topic, TopicDTO> writeObjectMapper) {
    this.topicRepository = topicRepository;
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
    return topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException(id));
  }

}
