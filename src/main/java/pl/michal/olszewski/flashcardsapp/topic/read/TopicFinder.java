package pl.michal.olszewski.flashcardsapp.topic.read;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Repository
public interface TopicFinder extends ReadOnlyRepository<Topic, Long> {

}