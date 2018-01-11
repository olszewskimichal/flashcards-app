package pl.michal.olszewski.flashcardsapp.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

  private TestService testService;

  @Mock
  private TopicRepository topicRepository;

  @Mock
  private TestRepository testRepository;


  @BeforeEach
  void setUp() {
    testService = new TestService(testRepository, topicRepository);
  }

  @Test
  void shouldCreateNewTest() {
    TestDTO testDTO = TestDTO.builder().topicId(1L).build();
    given(topicRepository.findOne(1L)).willReturn(Topic.builder().build());

    pl.michal.olszewski.flashcardsapp.test.Test test = testService.createNewTest(testDTO);

    assertThat(test).isNotNull();
    Mockito.verify(testRepository, Mockito.times(1)).save(test);
  }

  @Test
  void shouldThrowExceptionWhenTopicIdIsNull() {
    TestDTO testDTO = TestDTO.builder().build();

    NullPointerException exception = assertThrows(NullPointerException.class, () -> testService.createNewTest(testDTO));
    assertThat(exception).hasMessage("Nie mozna stworzyc testu bez tematu");
  }
}