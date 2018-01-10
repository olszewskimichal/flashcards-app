package pl.michal.olszewski.flashcardsapp.base;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@MappedSuperclass
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public abstract class BaseEntity {

  @Id
  @GeneratedValue
  protected Long id;

}
