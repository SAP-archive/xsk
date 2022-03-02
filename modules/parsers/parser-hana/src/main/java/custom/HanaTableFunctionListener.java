package custom;

import com.sap.xsk.parser.hana.core.HanaBaseListener;
import com.sap.xsk.parser.hana.core.HanaParser.Function_nameContext;
import com.sap.xsk.parser.hana.core.HanaParser.Schema_nameContext;
import models.TableFunctionDefinitionModel;

public class HanaTableFunctionListener extends HanaBaseListener {

  private final TableFunctionDefinitionModel model = new TableFunctionDefinitionModel();

  @Override
  public void exitSchema_name(Schema_nameContext ctx) {
    super.exitSchema_name(ctx);
    model.setSchema(ctx.getText());
  }

  @Override
  public void exitFunction_name(Function_nameContext ctx) {
    super.exitFunction_name(ctx);
    model.setName(ctx.getText());
  }

  public TableFunctionDefinitionModel getModel() {
    return model;
  }
}
