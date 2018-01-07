package pl.michal.olszewski.flashcardsapp.test;

import org.springframework.stereotype.Component;
import pl.michal.olszewski.flashcardsapp.mapper.ObjectMapper;

@Component("TestObjectMapper")
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