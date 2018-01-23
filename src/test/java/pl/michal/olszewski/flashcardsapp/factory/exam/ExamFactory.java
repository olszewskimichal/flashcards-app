package pl.michal.olszewski.flashcardsapp.factory.exam;

import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

public class ExamFactory {

  public static Exam build(Long id, Long topicId) {
    return Exam.builder()
        .id(id)
        .topic(Topic.builder().id(topicId).build())
        .build();
  }

}
