package pl.michal.olszewski.flashcardsapp.examcards.read;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;

@Repository
public interface ExamCardFinder extends ReadOnlyRepository<ExamCard, Long> {

}
