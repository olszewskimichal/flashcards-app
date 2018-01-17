package pl.michal.olszewski.flashcardsapp.user.read;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.ReadObjectMapper;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.user.UserNotFoundException;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;
import pl.michal.olszewski.flashcardsapp.user.read.dto.UserDTO;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserReadServiceTest {

  private UserReadService userReadService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ReadObjectMapper<User, UserDTO> readObjectMapper;

  @BeforeEach
  void setUp() {
    userReadService = new UserReadService(userRepository, readObjectMapper);
  }

  @Test
  void shouldReturnUserDTOById() {
    User user = User.builder().id(2L).build();
    given(userRepository.findOne(2L)).willReturn(user);
    given(readObjectMapper.convertToDTO(user)).willReturn(UserDTO.builder().id(2L).build());
    UserDTO userDTO = userReadService.getUserById(2L);

    assertThat(userDTO).isNotNull();
    assertThat(userDTO.getId()).isEqualTo(2L);
    verify(userRepository, times(1)).findOne(2L);
  }

  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(userRepository.findOne(1L)).willReturn(null);
    UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userReadService.getUserById(2L));
    assertThat(userNotFoundException.getMessage()).isEqualTo("Nie znalaziono uzytkownika o id = 2");
  }
}