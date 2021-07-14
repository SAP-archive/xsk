package com.sap.xsk.migration.api.dto;

import org.junit.Test;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ExecuteMigrationRequestBodyTest {

  @Test
  public void testValidationAnnotationsAreSet() {
    var allMethodsAreAnnotated = Arrays
        .stream(ExecuteMigrationRequestBody.class.getDeclaredMethods())
        .allMatch(m -> m.getAnnotation(NotBlank.class) != null);

    assertTrue("Some method(s) are not annotated with @NotBlank", allMethodsAreAnnotated);
  }
}