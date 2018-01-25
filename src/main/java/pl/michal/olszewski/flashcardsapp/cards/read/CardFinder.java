package pl.michal.olszewski.flashcardsapp.cards.read;

import java.util.List;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

@Repository
public interface CardFinder extends ReadOnlyRepository<Card, Long> {

  List<Card> findAll(Iterable<Long> ids);
}
