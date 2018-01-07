package pl.michal.olszewski.flashcardsapp.mapper;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.user.User;
import pl.michal.olszewski.flashcardsapp.user.UserDTO;

@Component("UserObjectMapper")
public class UserObjectMapper implements ObjectMapper<User, UserDTO> {

  @Override
  public User convertFromDTO(UserDTO transferObject) {
    return User.builder().firstName(transferObject.getFirstName()).lastName(transferObject.getLastName()).build();
  }

  @Override
  public UserDTO convertToDTO(User entity) {
    return UserDTO.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName()).build();
  }

  @Override
  public User updateFrom(UserDTO transferObject, User entity) {
    entity.setFirstName(transferObject.getFirstName());
    entity.setLastName(transferObject.getLastName());
    return entity;
  }
}