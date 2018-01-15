package pl.michal.olszewski.flashcardsapp.attempt.write;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptObjectMapper;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.NewAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.examcards.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.ExamCardLevelEnum;

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
    attempt.getExam().addAttempt(attempt);
    List<ExamCard> examCards = attempt.getExam().getTopic().getCards().stream()
        .map(v -> ExamCard.builder().attempt(attempt).card(v).testLevel(ExamCardLevelEnum.NEW.getValue()).build()).collect(Collectors.toList());
    attempt.setCardList(examCards);
    attemptRepository.save(attempt);
    return attempt;
  }

  public Attempt closeAttempt(CloseAttemptDTO closeAttemptDTO) {
    Attempt attempt = attemptRepository.findOne(closeAttemptDTO.getAttemptId());
    attempt.setAttemptStatus(AttemptStatusEnum.DONE.getValue());
    return attempt;
  }

  public Attempt updateAttemptStatus(UpdateStatusAttemptDTO updateStatusAttemptDTO) {
    Attempt attempt = attemptRepository.findOne(updateStatusAttemptDTO.getAttemptId());
    attempt.setAttemptStatus(updateStatusAttemptDTO.getAttemptStatus());
    return attempt;
  }
}
