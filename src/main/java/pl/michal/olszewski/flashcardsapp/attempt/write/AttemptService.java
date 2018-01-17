package pl.michal.olszewski.flashcardsapp.attempt.write;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.attempt.read.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.examcards.read.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.read.ExamCardLevelEnum;

@Service
public class AttemptService {

  private final AttemptWriteObjectMapper objectMapper;
  private final AttemptRepository attemptRepository;

  public AttemptService(AttemptWriteObjectMapper objectMapper, AttemptRepository attemptRepository) {
    this.objectMapper = objectMapper;
    this.attemptRepository = attemptRepository;
  }

  public Attempt createNewAttempt(CreateAttemptDTO createAttemptDTO) {
    Attempt attempt = objectMapper.convertFromDTO(createAttemptDTO);
    attempt.getExam().addAttempt(attempt);
    List<ExamCard> examCards = attempt.getExam().getTopic().getCards().stream()
        .map(v -> ExamCard.builder().attempt(attempt).card(v).testLevel(ExamCardLevelEnum.NEW.getValue()).build()).collect(Collectors.toList());
    attempt.setExamCards(examCards);
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
