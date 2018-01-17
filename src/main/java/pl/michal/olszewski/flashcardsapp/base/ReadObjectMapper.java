package pl.michal.olszewski.flashcardsapp.base;

public interface ReadObjectMapper<T extends BaseEntity, P extends DataTransferObject> {

  P convertToDTO(T entity);
}
