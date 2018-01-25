package pl.michal.olszewski.flashcardsapp.attempt;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.base.WriteOnlyRepository;

@Repository
public interface AttemptRepository extends WriteOnlyRepository<Attempt, Long>, ReadOnlyRepository<Attempt, Long> {

}