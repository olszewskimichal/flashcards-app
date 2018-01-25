package pl.michal.olszewski.flashcardsapp.user.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.UserNotFoundException;
import pl.michal.olszewski.flashcardsapp.user.read.dto.UserDTO;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

@Service
public class UserReadService {

  private final UserFinder finder;
  private final ReadObjectMapper<User, UserDTO> readObjectMapper;

  public UserReadService(UserFinder finder, @Qualifier("UserReadObjectMapper") ReadObjectMapper<User, UserDTO> readObjectMapper) {
    this.finder = finder;
    this.readObjectMapper = readObjectMapper;
  }

  public UserDTO getUserById(long userId) {
    User user = findUserById(userId);
    return readObjectMapper.convertToDTO(user);
  }

  private User findUserById(long userId) {
    return finder.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
  }
}
