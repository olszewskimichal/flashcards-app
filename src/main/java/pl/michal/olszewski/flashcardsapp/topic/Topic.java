package pl.michal.olszewski.flashcardsapp.topic;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.cards.Card;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Topic extends BaseEntity {

  private String name;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "post_card",
      joinColumns = @JoinColumn(name = "topic_id"),
      inverseJoinColumns = @JoinColumn(name = "card_id")
  )
  private List<Card> cards;

  @Builder
  public Topic(Long id, List<Card> cards, String name) {
    super(id);
    this.cards = cards;
    this.name = name;
  }

  public void addCard(Card card) {
    getCards().add(card);
    card.getTopics().add(this);
  }

  public void removeCard(Card card) {
    getCards().remove(card);
    card.getTopics().remove(this);
  }

  public List<Card> getCards() {
    if (cards == null) {
      cards = new ArrayList<>();
    }
    return cards;
  }
}
