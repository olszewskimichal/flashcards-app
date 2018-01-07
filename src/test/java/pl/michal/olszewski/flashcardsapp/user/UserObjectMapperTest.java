package pl.michal.olszewski.flashcardsapp.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;


class UserObjectMapperTest {

  private UserObjectMapper mapper = new UserObjectMapper();

  @Test
  void shouldConvertFromUserDTO() {
    //given
    UserDTO userDTO = UserDTO.builder().firstName("first").lastName("last").build();
    //when
    User user = mapper.convertFromDTO(userDTO);
    //then
    assertAll(
        () -> assertThat(user.getFirstName()).isEqualTo("first"),
        () -> assertThat(user.getLastName()).isEqualTo("last")
    );
  }

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

  @Test
  void shouldUpdateUserFromUserDTO() {
    //given
    User user = User.builder().firstName("first2").lastName("lastName2").id(1L).build();
    UserDTO userDTO = UserDTO.builder().firstName("newName").lastName("newLastName").id(1L).build();
    //when
    mapper.updateFrom(userDTO, user);
    //then
    assertAll(
        () -> assertThat(user.getFirstName()).isEqualTo("newName"),
        () -> assertThat(user.getId()).isEqualTo(1L),
        () -> assertThat(user.getLastName()).isEqualTo("newLastName")
    );
  }
}