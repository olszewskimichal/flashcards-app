package pl.michal.olszewski.flashcardsapp.attempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {

}