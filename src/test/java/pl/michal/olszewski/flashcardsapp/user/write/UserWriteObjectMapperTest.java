package pl.michal.olszewski.flashcardsapp.user.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;
import pl.michal.olszewski.flashcardsapp.user.write.dto.create.CreateUserDTO;


class UserWriteObjectMapperTest {

  private UserWriteObjectMapper mapper = new UserWriteObjectMapper();

  @Test
  void shouldConvertFromUserDTO() {
    //given
    CreateUserDTO createUserDTO = CreateUserDTO.builder().firstName("first").lastName("last").build();
    //when
    User user = mapper.convertFromDTO(createUserDTO);
    //then
    assertAll(
        () -> assertThat(user.getFirstName()).isEqualTo("first"),
        () -> assertThat(user.getLastName()).isEqualTo("last")
    );
  }
}