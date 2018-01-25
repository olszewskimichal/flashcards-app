package pl.michal.olszewski.flashcardsapp.exam.write;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.base.WriteOnlyRepository;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;

@Repository
public interface ExamRepository extends WriteOnlyRepository<Exam, Long>, ReadOnlyRepository<Exam, Long> {

}