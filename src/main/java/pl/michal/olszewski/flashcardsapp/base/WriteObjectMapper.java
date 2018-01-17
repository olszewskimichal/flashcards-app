package pl.michal.olszewski.flashcardsapp.base;

public interface WriteObjectMapper<T extends BaseEntity, P extends DataTransferObject> {

  T convertFromDTO(P transferObject);
}
