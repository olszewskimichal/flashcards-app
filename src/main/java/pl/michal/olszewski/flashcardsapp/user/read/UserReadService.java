package pl.michal.olszewski.flashcardsapp.user.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.UserNotFoundException;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;
import pl.michal.olszewski.flashcardsapp.user.read.dto.UserDTO;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

@Service
public class UserReadService {

  private final UserRepository userRepository;
  private final ReadObjectMapper<User, UserDTO> readObjectMapper;

  public UserReadService(UserRepository userRepository, @Qualifier("UserReadObjectMapper") ReadObjectMapper<User, UserDTO> readObjectMapper) {
    this.userRepository = userRepository;
    this.readObjectMapper = readObjectMapper;
  }

  public UserDTO getUserById(long userId) {
    User user = findUserById(userId);
    return readObjectMapper.convertToDTO(user);
  }

  private User findUserById(long userId) {
    User user = userRepository.findOne(userId);
    if (user == null) {
      throw new UserNotFoundException(userId);
    }
    return user;
  }
}
