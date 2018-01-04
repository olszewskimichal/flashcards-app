package pl.michal.olszewski.flashcardsapp.user;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class User extends BaseEntity {
  private String firstName;
  private String lastName;
}
