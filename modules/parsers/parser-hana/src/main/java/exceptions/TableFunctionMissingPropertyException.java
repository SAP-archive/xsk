package exceptions;

public class TableFunctionMissingPropertyException extends RuntimeException {

  public TableFunctionMissingPropertyException(String message) {
    super(message);
  }
}