package pl.michal.olszewski.flashcardsapp.user.read;

import org.springframework.stereotype.Repository;
import pl.michal.olszewski.flashcardsapp.base.ReadOnlyRepository;
import pl.michal.olszewski.flashcardsapp.user.read.entity.User;

@Repository
public interface UserFinder extends ReadOnlyRepository<User, Long> {

}
