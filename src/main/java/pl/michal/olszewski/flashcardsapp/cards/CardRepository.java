package pl.michal.olszewski.flashcardsapp.cards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}