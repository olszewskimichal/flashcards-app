package pl.michal.olszewski.flashcardsapp.test;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;

@Component("TestObjectMapper")
public class TestObjectMapper implements ObjectMapper<Test, TestDTO> {

  private final TopicRepository topicRepository;

  public TestObjectMapper(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  @Override
  public Test convertFromDTO(TestDTO transferObject) {
    return Test.builder().topic(topicRepository.findOne(transferObject.getTopicId())).build();
  }

  @Override
  public TestDTO convertToDTO(Test entity) {
    return TestDTO.builder().topicId(entity.getTopic().getId()).build();
  }

  @Override
  public Test updateFrom(TestDTO transferObject, Test entity) {
    return entity;
  }
}
