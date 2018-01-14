package pl.michal.olszewski.flashcardsapp.testcards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCardRepository extends JpaRepository<TestCard, Long> {

}
