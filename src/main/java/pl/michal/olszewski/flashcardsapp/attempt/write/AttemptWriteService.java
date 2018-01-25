package pl.michal.olszewski.flashcardsapp.attempt.write;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptNotFoundException;
import pl.michal.olszewski.flashcardsapp.attempt.AttemptRepository;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.Attempt;
import pl.michal.olszewski.flashcardsapp.attempt.read.entity.AttemptStatusEnum;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.create.CreateAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.CloseAttemptDTO;
import pl.michal.olszewski.flashcardsapp.attempt.write.dto.update.UpdateStatusAttemptDTO;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCard;
import pl.michal.olszewski.flashcardsapp.examcards.read.entity.ExamCardLevelEnum;

@Service
public class AttemptWriteService {

  private final WriteObjectMapper<Attempt, CreateAttemptDTO> objectMapper;
  private final AttemptRepository attemptRepository;

  public AttemptWriteService(@Qualifier("AttemptWriteObjectMapper") WriteObjectMapper<Attempt, CreateAttemptDTO> objectMapper, AttemptRepository attemptRepository) {
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
    Attempt attempt = attemptRepository.findById(closeAttemptDTO.getAttemptId()).orElseThrow(() -> new AttemptNotFoundException(closeAttemptDTO.getAttemptId()));
    attempt.setAttemptStatus(AttemptStatusEnum.DONE.getValue());
    return attempt;
  }

  public Attempt updateAttemptStatus(UpdateStatusAttemptDTO updateStatusAttemptDTO) {
    Attempt attempt = attemptRepository.findById(updateStatusAttemptDTO.getAttemptId()).orElseThrow(() -> new AttemptNotFoundException(updateStatusAttemptDTO.getAttemptId()));
    attempt.setAttemptStatus(updateStatusAttemptDTO.getAttemptStatus());
    return attempt;
  }
}
