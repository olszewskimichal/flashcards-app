package pl.michal.olszewski.flashcardsapp.attempt.read;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;

@Repository
public interface AttemptFinder extends ReadOnlyRepository<Attempt, Long> {

}
