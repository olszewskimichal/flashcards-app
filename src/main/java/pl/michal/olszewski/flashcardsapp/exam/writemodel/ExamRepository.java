package pl.michal.olszewski.flashcardsapp.exam.writemodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.exam.readmodel.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}