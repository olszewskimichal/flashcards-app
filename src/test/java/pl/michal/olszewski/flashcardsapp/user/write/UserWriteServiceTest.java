package pl.michal.olszewski.flashcardsapp.user.write;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import pl.michal.olszewski.flashcardsapp.base.WriteObjectMapper;
import pl.michal.olszewski.flashcardsapp.extensions.MockitoExtension;
import pl.michal.olszewski.flashcardsapp.factory.user.CreateUserDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.user.UpdateUserDTOFactory;
import pl.michal.olszewski.flashcardsapp.factory.user.UserFactory;
import pl.michal.olszewski.flashcardsapp.user.UserNotFoundException;
import pl.michal.olszewski.flashcardsapp.user.UserRepository;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;
import pl.michal.olszewski.flashcardsapp.user.write.dto.create.CreateUserDTO;
import pl.michal.olszewski.flashcardsapp.user.write.dto.update.UpdateUserDTO;

@ExtendWith(MockitoExtension.class)
public class UserWriteServiceTest {

  private UserWriteService userWriteService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private WriteObjectMapper<User, CreateUserDTO> writeObjectMapper;

  @BeforeEach
  void setUp() {
    userWriteService = new UserWriteService(userRepository, writeObjectMapper);
  }

  @Test
  void shouldCreateNewTopic() {
    CreateUserDTO createUserDTO = CreateUserDTOFactory.build("first", "last");
    given(writeObjectMapper.convertFromDTO(createUserDTO)).willReturn(User.builder().build());
    User user = userWriteService.createUser(createUserDTO);

    assertThat(user).isNotNull();
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void shouldUpdateUser() {
    UpdateUserDTO updateUserDTO = UpdateUserDTOFactory.build(1L, "new Name", null);
    User user = UserFactory.build(1L, "name", null);
    given(userRepository.findOne(1L)).willReturn(user);

    User updateUser = userWriteService.updateUser(updateUserDTO);
    assertThat(updateUser).isNotNull();
    verify(userRepository, times(1)).findOne(1L);
  }

  @Test
  void shouldUpdateUserFromUserDTO() {
    //given
    User user = UserFactory.build(1L, "first2", "lastName2");
    UpdateUserDTO updateUserDTO = UpdateUserDTOFactory.build(1L, "newName", "newLastName");
    given(userRepository.findOne(1L)).willReturn(user);
    //when
    User updatedUser = userWriteService.updateUser(updateUserDTO);
    //then
    assertAll(
        () -> assertThat(updatedUser.getFirstName()).isEqualTo("newName"),
        () -> assertThat(updatedUser.getId()).isEqualTo(1L),
        () -> assertThat(updatedUser.getLastName()).isEqualTo("newLastName")
    );
  }

  @Test
  void shouldThrowExceptionWhenUpdateNotExistingUser() {
    UpdateUserDTO updateUserDTO = UpdateUserDTOFactory.build(1L);
    given(userRepository.findOne(1L)).willReturn(null);

    UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userWriteService.updateUser(updateUserDTO));
    assertThat(userNotFoundException.getMessage()).isEqualTo("Nie znalaziono uzytkownika o id = 1");
  }
}