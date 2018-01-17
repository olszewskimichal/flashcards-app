package pl.michal.olszewski.flashcardsapp.user.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.user.write.UserDTO;


class UserReadObjectMapperTest {

  private UserReadObjectMapper mapper = new UserReadObjectMapper();

  @Test
  void shouldConvertUserToUserDTO() {
    //given
    User user = User.builder().firstName("first1").lastName("last1").build();
    //when
    UserDTO userDTO = mapper.convertToDTO(user);
    //then
    assertAll(
        () -> assertThat(userDTO.getFirstName()).isEqualTo("first1"),
        () -> assertThat(userDTO.getLastName()).isEqualTo("last1")
    );
  }

}