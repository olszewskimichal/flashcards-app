package pl.michal.olszewski.flashcardsapp.user.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.user.UserDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.user.UserFactory;
import pl.michal.olszewski.flashcardsapp.user.UserNotFoundException;
import pl.michal.olszewski.flashcardsapp.user.read.dto.UserDTO;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

@ExtendWith(MockitoExtension.class)
class UserReadServiceTest {

  private UserReadService userReadService;

  @Mock
  private UserFinder userFinder;

  @Mock
  private ReadObjectMapper<User, UserDTO> readObjectMapper;

  @BeforeEach
  void setUp() {
    userReadService = new UserReadService(userFinder, readObjectMapper);
  }

  @Test
  void shouldReturnUserDTOById() {
    User user = UserFactory.build(2L);
    given(userFinder.findById(2L)).willReturn(Optional.of(user));
    given(readObjectMapper.convertToDTO(user)).willReturn(UserDTOFactory.build(2L));
    UserDTO userDTO = userReadService.getUserById(2L);

    assertThat(userDTO).isNotNull();
    assertThat(userDTO.getId()).isEqualTo(2L);
    verify(userFinder, times(1)).findById(2L);
  }

  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(userFinder.findById(1L)).willReturn(Optional.empty());
    UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userReadService.getUserById(2L));
    assertThat(userNotFoundException.getMessage()).isEqualTo("Nie znalaziono uzytkownika o id = 2");
  }
}