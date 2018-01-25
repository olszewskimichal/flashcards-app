package pl.michal.olszewski.flashcardsapp.cards;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.base.WriteOnlyRepository;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

@Repository
public interface CardRepository extends WriteOnlyRepository<Card, Long>, ReadOnlyRepository<Card, Long> {

}