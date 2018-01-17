package pl.michal.olszewski.flashcardsapp.exam.read;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.exam.read.dto.ExamDTO;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;

@Component("ExamReadObjectMapper")
public class ExamReadObjectMapper implements ReadObjectMapper<Exam, ExamDTO> {

  @Override
  public ExamDTO convertToDTO(Exam entity) {
    return ExamDTO.builder().id(entity.getId()).topicId(entity.getTopic().getId()).build();
  }

}
