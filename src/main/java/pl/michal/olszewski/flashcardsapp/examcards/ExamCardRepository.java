package pl.michal.olszewski.flashcardsapp.examcards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.examcards.readmodel.ExamCard;

@Repository
public interface ExamCardRepository extends JpaRepository<ExamCard, Long> {

}
