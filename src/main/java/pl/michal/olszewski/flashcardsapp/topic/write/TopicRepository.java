package pl.michal.olszewski.flashcardsapp.topic.write;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.base.WriteOnlyRepository;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;

@Repository
public interface TopicRepository extends WriteOnlyRepository<Topic, Long>, ReadOnlyRepository<Topic, Long> {

}