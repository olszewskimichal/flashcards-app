package pl.michal.olszewski.flashcardsapp.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}