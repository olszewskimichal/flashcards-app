package pl.michal.olszewski.flashcardsapp.exam;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;
import pl.michal.olszewski.flashcardsapp.exam.readmodel.Exam;
import pl.michal.olszewski.flashcardsapp.exam.writemodel.CreateExamDTO;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;

@Component("ExamObjectMapper")
public class ExamObjectMapper implements ObjectMapper<Exam, CreateExamDTO> {

  private final TopicRepository topicRepository;

  public ExamObjectMapper(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  @Override
  public Exam convertFromDTO(CreateExamDTO transferObject) {
    return Exam.builder().topic(topicRepository.findOne(transferObject.getTopicId())).build();
  }

  @Override
  public CreateExamDTO convertToDTO(Exam entity) {
    return CreateExamDTO.builder().topicId(entity.getTopic().getId()).build();
  }

  @Override
  public Exam updateFrom(CreateExamDTO transferObject, Exam entity) {
    return entity;
  }
}
