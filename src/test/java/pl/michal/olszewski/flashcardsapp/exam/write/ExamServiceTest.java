package pl.michal.olszewski.flashcardsapp.exam.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.exam.read.Exam;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;
import pl.michal.olszewski.flashcardsapp.topic.read.Topic;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {

  private ExamService service;

  @Mock
  private TopicRepository topicRepository;

  @Mock
  private ExamRepository repository;


  @BeforeEach
  void setUp() {
    service = new ExamService(repository, topicRepository);
  }

  @Test
  void shouldCreateNewTest() {
    CreateExamDTO createExamDTO = CreateExamDTO.builder().topicId(1L).build();
    given(topicRepository.findOne(1L)).willReturn(Topic.builder().build());

    Exam test = service.createNewExam(createExamDTO);

    assertThat(test).isNotNull();
    Mockito.verify(repository, Mockito.times(1)).save(test);
  }

  @Test
  void shouldThrowExceptionWhenTopicIdIsNull() {
    CreateExamDTO createExamDTO = CreateExamDTO.builder().build();

    NullPointerException exception = assertThrows(NullPointerException.class, () -> service.createNewExam(createExamDTO));
    assertThat(exception).hasMessage("Nie mozna stworzyc testu bez tematu");
  }
}