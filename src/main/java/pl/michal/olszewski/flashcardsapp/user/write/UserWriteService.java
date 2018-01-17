package pl.michal.olszewski.flashcardsapp.user.write;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.UserNotFoundException;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;
import pl.michal.olszewski.flashcardsapp.user.write.dto.create.CreateUserDTO;
import pl.michal.olszewski.flashcardsapp.user.write.dto.update.UpdateUserDTO;

@Service
public class UserWriteService {

  private final UserRepository userRepository;
  private final WriteObjectMapper<User, CreateUserDTO> writeObjectMapper;

  public UserWriteService(UserRepository userRepository, @Qualifier("UserWriteObjectMapper") WriteObjectMapper<User, CreateUserDTO> writeObjectMapper) {
    this.userRepository = userRepository;
    this.writeObjectMapper = writeObjectMapper;
  }

  public User createUser(CreateUserDTO createUserDTO) {
    User user = writeObjectMapper.convertFromDTO(createUserDTO);
    userRepository.save(user);
    return user;
  }

  public User updateUser(UpdateUserDTO updateUserDTO) {
    User user = findUserById(updateUserDTO.getId());
    user.setFirstName(updateUserDTO.getFirstName());
    user.setLastName(updateUserDTO.getLastName());
    return user;
  }

  private User findUserById(long userId) {
    User user = userRepository.findOne(userId);
    if (user == null) {
      throw new UserNotFoundException(userId);
    }
    return user;
  }
}
