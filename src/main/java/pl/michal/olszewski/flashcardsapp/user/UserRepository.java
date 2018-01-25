package pl.michal.olszewski.flashcardsapp.user;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.base.WriteOnlyRepository;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

@Repository
public interface UserRepository extends WriteOnlyRepository<User, Long>, ReadOnlyRepository<User, Long> {

}
