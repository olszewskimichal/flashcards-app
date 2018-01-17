package pl.michal.olszewski.flashcardsapp.exam.write;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.exam.read.Exam;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;

@Service
public class ExamService {

  private final ExamRepository examRepository;
  private final TopicRepository topicRepository;

  public ExamService(ExamRepository examRepository, TopicRepository topicRepository) {
    this.examRepository = examRepository;
    this.topicRepository = topicRepository;
  }

  public Exam createNewExam(CreateExamDTO createExamDTO) {
    Objects.requireNonNull(createExamDTO.getTopicId(), "Nie mozna stworzyc testu bez tematu");
    Exam exam = Exam.builder()
        .topic(topicRepository.findOne(createExamDTO.getTopicId()))
        .build();
    examRepository.save(exam);
    return exam;
  }
}
