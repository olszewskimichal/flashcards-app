package pl.michal.olszewski.flashcardsapp.exam.write;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.exam.write.dto.create.CreateExamDTO;

@Service
public class ExamWriteService {

  private final ExamRepository examRepository;
  private final ExamWriteObjectMapper examWriteObjectMapper;

  public ExamWriteService(ExamRepository examRepository, @Qualifier("ExamWriteObjectMapper") ExamWriteObjectMapper examWriteObjectMapper) {
    this.examRepository = examRepository;
    this.examWriteObjectMapper = examWriteObjectMapper;
  }

  public Exam createNewExam(CreateExamDTO createExamDTO) {
    Objects.requireNonNull(createExamDTO.getTopicId(), "Nie mozna stworzyc testu bez tematu");
    Exam exam = examWriteObjectMapper.convertFromDTO(createExamDTO);
    examRepository.save(exam);
    return exam;
  }
}
