package pl.michal.olszewski.flashcardsapp.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ObjectMapper;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ObjectMapper<User, UserDTO> objectMapper;

  public UserService(UserRepository userRepository, @Qualifier("UserObjectMapper") ObjectMapper<User, UserDTO> objectMapper) {
    this.userRepository = userRepository;
    this.objectMapper = objectMapper;
  }

  public User createUser(UserDTO userDTO) {
    User user = objectMapper.convertFromDTO(userDTO);
    userRepository.save(user);
    return user;
  }

  public User updateUser(UserDTO userDTO) {
    User user = findUserById(userDTO.getId());
    objectMapper.updateFrom(userDTO, user);
    return user;
  }

  public UserDTO getUserById(long userId) {
    User user = findUserById(userId);
    return objectMapper.convertToDTO(user);
  }

  private User findUserById(long userId) {
    User user = userRepository.findOne(userId);
    if (user == null) {
      throw new UserNotFoundException(userId);
    }
    return user;
  }
}
