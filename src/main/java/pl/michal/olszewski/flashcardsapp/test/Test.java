package pl.michal.olszewski.flashcardsapp.test;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Test extends BaseEntity {

}
