package pl.michal.olszewski.flashcardsapp.mapper;

import pl.michal.olszewski.flashcardsapp.base.BaseEntity;
import pl.michal.olszewski.flashcardsapp.base.DataTransferObject;

public interface ObjectMapper<T extends BaseEntity,P extends DataTransferObject> {
   T convertFromDTO(P transferObject);
   P convertToDTO(T entity);
   T updateFrom(P transferObject,T entity);
}
