package pl.michal.olszewski.flashcardsapp.user;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class User extends BaseEntity {

  private String firstName;
  private String lastName;

  @Builder
  public User(Long id, String firstName, String lastName) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
