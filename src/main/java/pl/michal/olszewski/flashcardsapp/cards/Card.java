package pl.michal.olszewski.flashcardsapp.cards;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.topic.Topic;

@Entity
@Data
@EqualsAndHashCode(callSuper = true,exclude = "topics")
@ToString(exclude = "topics")
public class Card extends BaseEntity {

  private String question;
  private String answer;

  @ManyToMany(mappedBy = "cards")
  private List<Topic> topics = new ArrayList<>();
}
