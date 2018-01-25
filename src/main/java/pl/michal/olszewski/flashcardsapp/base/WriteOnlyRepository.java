package pl.michal.olszewski.flashcardsapp.base;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface WriteOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {

  <S extends T> S save(S entity);
}
