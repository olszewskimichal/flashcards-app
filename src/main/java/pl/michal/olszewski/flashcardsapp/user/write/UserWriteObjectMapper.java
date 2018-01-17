package pl.michal.olszewski.flashcardsapp.user.write;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;
import pl.michal.olszewski.flashcardsapp.user.write.dto.create.CreateUserDTO;

@Component("UserWriteObjectMapper")
public class UserWriteObjectMapper implements WriteObjectMapper<User, CreateUserDTO> {

  @Override
  public User convertFromDTO(CreateUserDTO transferObject) {
    return User.builder().firstName(transferObject.getFirstName()).lastName(transferObject.getLastName()).build();
  }

}
