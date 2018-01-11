package pl.michal.olszewski.flashcardsapp.test;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;

@Service
public class TestService {

  private final TestRepository testRepository;
  private final TopicRepository topicRepository;

  public TestService(TestRepository testRepository, TopicRepository topicRepository) {
    this.testRepository = testRepository;
    this.topicRepository = topicRepository;
  }

  public Test createNewTest(TestDTO testDTO) {
    Objects.requireNonNull(testDTO.getTopicId(), "Nie mozna stworzyc testu bez tematu");
    Test test = Test.builder()
        .topic(topicRepository.findOne(testDTO.getTopicId()))
        .build();
    testRepository.save(test);
    return test;
  }
}
