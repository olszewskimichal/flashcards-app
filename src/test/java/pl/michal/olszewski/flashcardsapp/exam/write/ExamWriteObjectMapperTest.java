package pl.michal.olszewski.flashcardsapp.exam.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.exam.write.dto.create.CreateExamDTO;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.exam.CreateExamDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.topic.TopicFactory;
import pl.michal.olszewski.flashcardsapp.topic.read.TopicFinder;


@ExtendWith(MockitoExtension.class)
class ExamWriteObjectMapperTest {

  @Mock
  private TopicFinder topicRepository;

  private ExamWriteObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ExamWriteObjectMapper(topicRepository);
  }

  @Test
  void shouldConvertFromTestDTO() {
    CreateExamDTO createExamDTO = CreateExamDTOFactory.build(1L);
    given(topicRepository.findById(1L)).willReturn(Optional.of(TopicFactory.build(1L, null)));

    Exam exam = mapper.convertFromDTO(createExamDTO);

    assertThat(exam).isNotNull();
    assertThat(exam.getTopic()).isNotNull();
  }
}