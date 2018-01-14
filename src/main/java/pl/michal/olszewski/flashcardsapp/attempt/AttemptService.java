package pl.michal.olszewski.flashcardsapp.attempt;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.dto.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.dto.NewAttemptDTO;
import pl.michal.olszewski.flashcardsapp.testcards.TestCard;
import pl.michal.olszewski.flashcardsapp.testcards.TestCardLevelEnum;

@Service
public class AttemptService {

  private final AttemptObjectMapper objectMapper;
  private final AttemptRepository attemptRepository;

  public AttemptService(AttemptObjectMapper objectMapper, AttemptRepository attemptRepository) {
    this.objectMapper = objectMapper;
    this.attemptRepository = attemptRepository;
  }

  public Attempt createNewAttempt(NewAttemptDTO newAttemptDTO) {
    Attempt attempt = objectMapper.convertFromDTO(newAttemptDTO);
    attempt.getTest().addAttempt(attempt);
    List<TestCard> testCards = attempt.getTest().getTopic().getCards().stream()
        .map(v -> TestCard.builder().attempt(attempt).card(v).testLevel(TestCardLevelEnum.NEW.getValue()).build()).collect(Collectors.toList());
    attempt.setCardList(testCards);
    attemptRepository.save(attempt);
    return attempt;
  }

  public Attempt closeAttempt(CloseAttemptDTO closeAttemptDTO) {
    Attempt attempt = attemptRepository.findOne(closeAttemptDTO.getAttemptId());
    attempt.setAttemptStatus(AttemptStatusEnum.DONE.getValue());
    return attempt;
  }
}
