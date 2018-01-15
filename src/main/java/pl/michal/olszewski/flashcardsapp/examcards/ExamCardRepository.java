package pl.michal.olszewski.flashcardsapp.examcards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamCardRepository extends JpaRepository<ExamCard, Long> {

}
