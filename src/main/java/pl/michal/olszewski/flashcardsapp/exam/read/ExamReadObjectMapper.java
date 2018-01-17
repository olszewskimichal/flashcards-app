package pl.michal.olszewski.flashcardsapp.exam.read;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.exam.write.CreateExamDTO;

@Component("ExamObjectMapper")
public class ExamReadObjectMapper implements ReadObjectMapper<Exam, CreateExamDTO> {

  @Override
  public CreateExamDTO convertToDTO(Exam entity) {
    return CreateExamDTO.builder().topicId(entity.getTopic().getId()).build();
  }

}
