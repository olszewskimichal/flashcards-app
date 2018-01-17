package pl.michal.olszewski.flashcardsapp.exam.write;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.exam.write.dto.create.CreateExamDTO;
import pl.michal.olszewski.flashcardsapp.topic.TopicRepository;

@Component("ExamWriteObjectMapper")
public class ExamWriteObjectMapper implements WriteObjectMapper<Exam, CreateExamDTO> {

  private final TopicRepository topicRepository;

  public ExamWriteObjectMapper(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  @Override
  public Exam convertFromDTO(CreateExamDTO transferObject) {
    return Exam.builder().topic(topicRepository.findOne(transferObject.getTopicId())).build();
  }

}
