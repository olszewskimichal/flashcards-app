package pl.michal.olszewski.flashcardsapp.mapper;

import pl.michal.olszewski.flashcardsapp.test.Test;
import pl.michal.olszewski.flashcardsapp.test.TestDTO;

public class TestObjectMapper implements ObjectMapper<Test, TestDTO> {

  @Override
  public Test convertFromDTO(TestDTO transferObject) {
    return Test.builder().build();
  }

  @Override
  public TestDTO convertToDTO(Test entity) {
    return TestDTO.builder().build();
  }

  @Override
  public Test updateFrom(TestDTO transferObject, Test entity) {
    return entity;
  }
}
