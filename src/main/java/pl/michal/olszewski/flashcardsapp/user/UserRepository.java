package pl.michal.olszewski.flashcardsapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.user.read.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
