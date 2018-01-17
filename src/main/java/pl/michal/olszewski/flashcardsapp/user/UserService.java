package pl.michal.olszewski.flashcardsapp.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.read.User;
import pl.michal.olszewski.flashcardsapp.user.write.UserDTO;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ReadObjectMapper<User, UserDTO> readObjectMapper;
  private final WriteObjectMapper<User, UserDTO> writeObjectMapper;

  public UserService(UserRepository userRepository, @Qualifier("UserObjectMapper") ReadObjectMapper<User, UserDTO> readObjectMapper,
      WriteObjectMapper<User, UserDTO> writeObjectMapper) {
    this.userRepository = userRepository;
    this.readObjectMapper = readObjectMapper;
    this.writeObjectMapper = writeObjectMapper;
  }

  public User createUser(UserDTO userDTO) {
    User user = writeObjectMapper.convertFromDTO(userDTO);
    userRepository.save(user);
    return user;
  }

  public User updateUser(UserDTO userDTO) {
    User user = findUserById(userDTO.getId());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    return user;
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
