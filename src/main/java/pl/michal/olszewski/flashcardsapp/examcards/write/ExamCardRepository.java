package pl.michal.olszewski.flashcardsapp.examcards.write;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.base.WriteOnlyRepository;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;

@Repository
public interface ExamCardRepository extends WriteOnlyRepository<ExamCard, Long>, ReadOnlyRepository<ExamCard, Long> {

}
