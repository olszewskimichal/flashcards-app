package pl.michal.olszewski.flashcardsapp.exam.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.michal.olszewski.flashcardsapp.exam.ExamRepository;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.exam.write.dto.create.CreateExamDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExamWriteServiceTest {

  private ExamWriteService service;

  @Mock
  private ExamWriteObjectMapper writeObjectMapper;

  @Mock
  private ExamRepository repository;

  @BeforeEach
  void setUp() {
    service = new ExamWriteService(repository, writeObjectMapper);
  }

  @Test
  void shouldCreateNewTest() {
    CreateExamDTO createExamDTO = CreateExamDTO.builder().topicId(1L).build();
    given(writeObjectMapper.convertFromDTO(createExamDTO)).willReturn(Exam.builder().build());

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