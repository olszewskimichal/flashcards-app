package pl.michal.olszewski.flashcardsapp.equals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.cards.Card;
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
  void userEqualsContractTest() {
    EqualsVerifier.forClass(User.class).withRedefinedSuperclass().verify();
  }

  @Test
  void testEqualsContractTest() {
    EqualsVerifier.forClass(pl.michal.olszewski.flashcardsapp.test.Test.class).withRedefinedSuperclass().verify();
  }

  @Test
  void topicEqualsContractTest() {
    Card cardOne = Card.builder().id(1L).build();
    Card cardTwo = Card.builder().id(2L).build();
    EqualsVerifier.forClass(Topic.class).withPrefabValues(Card.class, cardOne, cardTwo).withRedefinedSuperclass().verify();
  }
}
