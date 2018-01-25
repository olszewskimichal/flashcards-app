package pl.michal.olszewski.flashcardsapp.attempt.read;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.config.Profiles;
import pl.michal.olszewski.flashcardsapp.factory.attempt.AttemptFactory;

@DataJpaTest
@ActiveProfiles(Profiles.TEST)
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
class AttemptFinderTest {

  @Autowired
  private TestEntityManager repository;

  @Autowired
  private AttemptFinder finder;

  @Test
  void shouldFindAttemptById() {
    repository.merge(AttemptFactory.build(1L));
    //when
    Optional<Attempt> byId = finder.findById(1L);
    //then
    assertThat(byId).isPresent();
  }

  @Test
  void shouldNotFindAttemptByIdWhenNotExist() {
    //when
    Optional<Attempt> byId = finder.findById(1L);
    //then
    assertThat(byId).isNotPresent();
  }
}