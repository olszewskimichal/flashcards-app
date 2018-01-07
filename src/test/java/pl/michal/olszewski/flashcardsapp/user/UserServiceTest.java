package pl.michal.olszewski.flashcardsapp.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.mapper.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ObjectMapper<User, UserDTO> objectMapper;

  @BeforeEach
  void setUp() {
    userService = new UserService(userRepository, objectMapper);
  }

  @Test
  void shouldCreateNewTopic() {
    UserDTO userDTO = UserDTO.builder().lastName("last").build();
    given(objectMapper.convertFromDTO(userDTO)).willReturn(User.builder().build());
    User user = userService.createUser(userDTO);

    assertThat(user).isNotNull();
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void shouldUpdateUser() {
    UserDTO userDTO = UserDTO.builder().id(1L).firstName("new Name").build();
    User user = User.builder().id(1L).firstName("name").build();
    given(userRepository.findOne(1L)).willReturn(user);
    given(objectMapper.updateFrom(userDTO, user)).willReturn(User.builder().build());

    User updateUser = userService.updateUser(userDTO);
    assertThat(updateUser).isNotNull();
    verify(userRepository, times(1)).findOne(1L);
  }

  @Test
  void shouldThrowExceptionWhenUpdateNotExistingUser() {
    UserDTO userDTO = UserDTO.builder().firstName("").id(1L).build();
    given(userRepository.findOne(1L)).willReturn(null);

    UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userService.updateUser(userDTO));
    assertThat(userNotFoundException.getMessage()).isEqualTo("Nie znalaziono uzytkownika o id = 1");
  }

  @Test
  void shouldReturnUserDTOById() {
    User user = User.builder().id(2L).build();
    given(userRepository.findOne(2L)).willReturn(user);
    given(objectMapper.convertToDTO(user)).willReturn(UserDTO.builder().id(2L).build());
    UserDTO userDTO = userService.getUserById(2L);

    assertThat(userDTO).isNotNull();
    assertThat(userDTO.getId()).isEqualTo(2L);
    verify(userRepository, times(1)).findOne(2L);
  }

  @Test
  void shouldThrowExceptionWhenGetByNotExistingId() {
    given(userRepository.findOne(1L)).willReturn(null);
    UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userService.getUserById(2L));
    assertThat(userNotFoundException.getMessage()).isEqualTo("Nie znalaziono uzytkownika o id = 2");
  }
}