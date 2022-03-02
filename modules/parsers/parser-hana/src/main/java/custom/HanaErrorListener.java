package custom;

import com.sap.xsk.parser.models.BaseParserErrorsModel;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import java.util.ArrayList;

public class HanaErrorListener extends BaseErrorListener {

  private final ArrayList<BaseParserErrorsModel> errors = new ArrayList<>();

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg,
      RecognitionException e) {

    this.errors.add(new BaseParserErrorsModel(line, charPositionInLine, offendingSymbol == null? "" : offendingSymbol.toString(), msg));
  }

  public ArrayList<BaseParserErrorsModel> getErrors() {
    return errors;
  }
}
