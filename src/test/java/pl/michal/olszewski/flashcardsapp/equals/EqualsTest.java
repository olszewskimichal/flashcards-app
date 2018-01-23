package pl.michal.olszewski.flashcardsapp.equals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.cards.read.entity.Card;
import pl.michal.olszewski.flashcardsapp.exam.read.entity.Exam;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.factory.attempt.AttemptFactory;
import pl.michal.olszewski.flashcardsapp.factory.card.CardFactory;
import pl.michal.olszewski.flashcardsapp.topic.read.entity.Topic;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;


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
    Attempt attemptOne = AttemptFactory.buildAttemptWithUserAndExam(1L, null, null);
    Attempt attemptTwo = AttemptFactory.buildAttemptWithUserAndExam(2L, null, null);
    Topic topicOne = Topic.builder().name("name1").id(1L).build();
    Topic topicTwo = Topic.builder().name("name2").id(2L).build();

    EqualsVerifier.forClass(Exam.class)
        .withPrefabValues(Attempt.class, attemptOne, attemptTwo)
        .withPrefabValues(Topic.class, topicOne, topicTwo)
        .withRedefinedSuperclass().verify();
  }

  @Test
  void attemptEqualsContractTest() {
    Exam testOne = Exam.builder().id(1L).build();
    Exam testTwo = Exam.builder().id(2L).build();

    ExamCard examCardOne = ExamCard.builder().id(1L).build();
    ExamCard examCardTwo = ExamCard.builder().id(2L).build();

    EqualsVerifier.forClass(Attempt.class)
        .withPrefabValues(Exam.class, testOne, testTwo)
        .withPrefabValues(ExamCard.class, examCardOne, examCardTwo)
        .withRedefinedSuperclass().verify();
  }

  @Test
  void topicEqualsContractTest() {
    Card cardOne = CardFactory.build(1L, null, null);
    Card cardTwo = CardFactory.build(2L, null, null);
    EqualsVerifier.forClass(Topic.class).withPrefabValues(Card.class, cardOne, cardTwo).withRedefinedSuperclass().verify();
  }

  @Test
  void testCardEqualsContractTest() {
    Card cardOne = CardFactory.build(1L, null, null);
    Card cardTwo = CardFactory.build(2L, null, null);
    Attempt attemptOne = AttemptFactory.buildAttemptWithUserAndExam(1L, null, null);
    Attempt attemptTwo = AttemptFactory.buildAttemptWithUserAndExam(2L, null, null);

    EqualsVerifier.forClass(ExamCard.class)
        .withPrefabValues(Attempt.class, attemptOne, attemptTwo)
        .withPrefabValues(Card.class, cardOne, cardTwo)
        .withRedefinedSuperclass().verify();
  }
}
