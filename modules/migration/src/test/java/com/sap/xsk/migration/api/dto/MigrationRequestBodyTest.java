package com.sap.xsk.migration.api.dto;

import org.junit.Test;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MigrationRequestBodyTest {

  @Test
  public void testValidationAnnotationsAreSet() {
    var allMethodsAreAnnotated = Arrays
        .stream(MigrationRequestBody.class.getDeclaredMethods())
        .allMatch(m -> m.getAnnotation(NotNull.class) != null);

    assertTrue("Some method(s) are not annotated with @NotNull", allMethodsAreAnnotated);
  }
}