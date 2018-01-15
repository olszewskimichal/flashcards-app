package pl.michal.olszewski.flashcardsapp.cards.read;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.topic.readmodel.Topic;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "topics")
public class Card extends BaseEntity {

  private String question;
  private String answer;

  @ManyToMany(mappedBy = "cards")
  private List<Topic> topics = new ArrayList<>();

  @Builder
  public Card(Long id, String question, String answer, List<Topic> topics) {
    super(id);
    this.question = question;
    this.answer = answer;
    this.topics = topics;
  }

  public List<Topic> getTopics() {
    if (topics == null) {
      topics = new ArrayList<>();
    }
    return topics;
  }
}
