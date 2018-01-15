package pl.michal.olszewski.flashcardsapp.equals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.cards.read.Card;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;
import pl.michal.olszewski.flashcardsapp.topic.Topic;
import pl.michal.olszewski.flashcardsapp.user.User;


class EqualsTest {

  @Test
  void cardEqualsContractTest() {
    Topic topicOne = Topic.builder().name("name1").id(1L).build();
    Topic topicTwo = Topic.builder().name("name2").id(2L).build();

    EqualsVerifier.forClass(Card.class).withPrefabValues(Topic.class, topicOne, topicTwo).withIgnoredFields("topics").withRedefinedSuperclass().verify();
  }

  @Test
  void baseEntityEqualsContractTest() {
    EqualsVerifier.forClass(BaseEntity.class).verify();
  }

  @Test
  void userEqualsContractTest() {
    EqualsVerifier.forClass(User.class).withRedefinedSuperclass().verify();
  }

  @Test
  void testEqualsContractTest() {
    Attempt attemptOne = Attempt.builder().id(1L).build();
    Attempt attemptTwo = Attempt.builder().id(2L).build();
    Topic topicOne = Topic.builder().name("name1").id(1L).build();
    Topic topicTwo = Topic.builder().name("name2").id(2L).build();

    EqualsVerifier.forClass(pl.michal.olszewski.flashcardsapp.test.Test.class)
        .withPrefabValues(Attempt.class, attemptOne, attemptTwo)
        .withPrefabValues(Topic.class, topicOne, topicTwo)
        .withRedefinedSuperclass().verify();
  }

  @Test
  void attemptEqualsContractTest() {
    pl.michal.olszewski.flashcardsapp.test.Test testOne = pl.michal.olszewski.flashcardsapp.test.Test.builder().id(1L).build();
    pl.michal.olszewski.flashcardsapp.test.Test testTwo = pl.michal.olszewski.flashcardsapp.test.Test.builder().id(2L).build();

    TestCard testCardOne = TestCard.builder().id(1L).build();
    TestCard testCardTwo = TestCard.builder().id(2L).build();

    EqualsVerifier.forClass(Attempt.class)
        .withPrefabValues(pl.michal.olszewski.flashcardsapp.test.Test.class, testOne, testTwo)
        .withPrefabValues(TestCard.class, testCardOne, testCardTwo)
        .withRedefinedSuperclass().verify();
  }

  @Test
  void topicEqualsContractTest() {
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
    EqualsVerifier.forClass(Topic.class).withPrefabValues(Card.class, cardOne, cardTwo).withRedefinedSuperclass().verify();
  }

  @Test
  void testCardEqualsContractTest() {
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
    Attempt attemptOne = Attempt.builder().id(1L).build();
    Attempt attemptTwo = Attempt.builder().id(2L).build();

    EqualsVerifier.forClass(TestCard.class)
        .withPrefabValues(Attempt.class, attemptOne, attemptTwo)
        .withPrefabValues(Card.class, cardOne, cardTwo)
        .withRedefinedSuperclass().verify();
  }
}
