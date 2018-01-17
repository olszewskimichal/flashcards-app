package pl.michal.olszewski.flashcardsapp.user.write;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.read.User;

@Component
public class UserWriteObjectMapper implements WriteObjectMapper<User, UserDTO> {

  @Override
  public User convertFromDTO(UserDTO transferObject) {
    return User.builder().firstName(transferObject.getFirstName()).lastName(transferObject.getLastName()).build();
  }

}
