package pl.michal.olszewski.flashcardsapp.user.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.factory.user.UserFactory;
import pl.michal.olszewski.flashcardsapp.user.read.dto.UserDTO;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;


class UserReadObjectMapperTest {

  private UserReadObjectMapper mapper = new UserReadObjectMapper();

  @Test
  void shouldConvertUserToUserDTO() {
    //given
    User user = UserFactory.build(2L, "first1", "last1");
    //when
    UserDTO userDTO = mapper.convertToDTO(user);
    //then
    assertAll(
        () -> assertThat(userDTO.getFirstName()).isEqualTo("first1"),
        () -> assertThat(userDTO.getLastName()).isEqualTo("last1"),
        () -> assertThat(userDTO.getId()).isEqualTo(2L)
    );
  }

}