package pl.michal.olszewski.flashcardsapp.exam.read;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;

@Repository
public interface ExamFinder extends ReadOnlyRepository<Exam, Long> {

}