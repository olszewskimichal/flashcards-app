package pl.michal.olszewski.flashcardsapp.user.read;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.write.UserDTO;

@Component("UserObjectMapper")
public class UserReadObjectMapper implements ReadObjectMapper<User, UserDTO> {

  @Override
  public UserDTO convertToDTO(User entity) {
    return UserDTO.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName()).build();
  }

}
