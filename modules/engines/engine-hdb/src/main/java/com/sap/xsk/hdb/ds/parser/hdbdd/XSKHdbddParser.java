package com.sap.xsk.hdb.ds.parser.hdbdd;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.transformer.hdbdd.HdbddTransformer;
import com.sap.xsk.parser.hdbdd.core.CdsLexer;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.custom.EntityDefinitionListener;
import com.sap.xsk.parser.hdbdd.custom.ReferenceResolvingListener;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class XSKHdbddParser implements XSKDataStructureParser {
  private static final Logger logger = LoggerFactory.getLogger(XSKHdbddParser.class);
  @Inject
  private IRepository repository;
  @Inject
  private HdbddTransformer hdbddTransformer;
  private SymbolTable symbolTable = new SymbolTable();
  private Map<String, String> contentByFileName = new HashMap<>();
  private Map<String, Set<String>> usedFiles = new HashMap<>();

  @Override
  public XSKDataStructureModel parse(String location, String content) throws XSKDataStructuresException, IOException {
    this.contentByFileName.put(location, content);

    for (String fileLocation : this.getFilesToProcess(location)) {
      String fileContent = this.contentByFileName.get(fileLocation);
      parseHdbdd(fileLocation, fileContent);
    }

    XSKDataStructureCdsModel cdsModel = getCdsModel();
    this.symbolTable.clearSymbolsByFullName();
    this.symbolTable.clearEntityGraph();

    return cdsModel;
  }

  private void parseHdbdd(String location, String content) throws IOException {
    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    CdsLexer hdbtiLexer = new CdsLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbtiLexer);

    CdsParser hdbtiParser = new CdsParser(tokenStream);
    hdbtiParser.setBuildParseTree(true);
    hdbtiParser.removeErrorListeners();

    ParseTree parseTree = hdbtiParser.cdsFile();

    EntityDefinitionListener entityDefinitionListener = new EntityDefinitionListener();
    entityDefinitionListener.setSymbolTable(symbolTable);

    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(entityDefinitionListener, parseTree);

    entityDefinitionListener.getPackagesUsed().forEach(p -> {
      String fileLocation = getFileLocation(p);
      addUsedFile(fileLocation, location);

      IResource loadedFile = repository.getResource(fileLocation);
      String loadedContent = new String(loadedFile.getContent());
      try {
        parse(fileLocation, loadedContent);
      } catch (XSKDataStructuresException | IOException e) {
        e.printStackTrace();
      }
    });

    ReferenceResolvingListener referenceResolvingListener = new ReferenceResolvingListener();
    referenceResolvingListener.setSymbolTable(symbolTable);
    referenceResolvingListener.setSymbolsByParseTreeContext(entityDefinitionListener.getSymbolsByParseTreeContext());
    referenceResolvingListener.setEntityElements(entityDefinitionListener.getEntityElements());
    referenceResolvingListener.setTypeables(entityDefinitionListener.getTypeables());
    referenceResolvingListener.setAssociations(entityDefinitionListener.getAssociations());

    referenceResolvingListener.setSymbolsByParseTreeContext(entityDefinitionListener.getSymbolsByParseTreeContext());
    parseTreeWalker.walk(referenceResolvingListener, parseTree);
  }

  private void addUsedFile(String usedFile, String userFile) {
    Set<String> userFiles = usedFiles.get(usedFile);
    if (userFiles == null) {
      userFiles = new HashSet<>();
    }

    userFiles.add(userFile);
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDBDD;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureCdsModel.class;
  }

  private List<String> getFilesToProcess(String fileLocation) {
    List<String> rootFiles = new ArrayList<>();
    getRootFiles(fileLocation, rootFiles);

    return rootFiles;
  }

  private void getRootFiles(String usedFileName, List<String> rootFiles) {
    Set<String> userFiles = usedFiles.get(usedFileName);
    if (userFiles == null) {
      rootFiles.add(usedFileName);
      return;
    }

    usedFiles.get(usedFileName).forEach(f -> {
      getRootFiles(f, rootFiles);
    });
  }

  private XSKDataStructureCdsModel getCdsModel() {
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();

    List<XSKDataStructureHDBTableModel> tableModels = new ArrayList<>();
    parsedEntities.forEach(e -> {
      tableModels.add(this.hdbddTransformer.transformEntitySymbolToTableModel(e));
    });

    XSKDataStructureCdsModel cdsModel = new XSKDataStructureCdsModel();
    cdsModel.setTableModels(tableModels);

    return cdsModel;
  }

  private String getFileLocation(String fullPackagePath) {
    String[] splitPackagePath = fullPackagePath.split("::");
    String directory = XSKHDBUtils.getRepositoryNamespace(splitPackagePath[0]);
    String topLevelCdsObject = splitPackagePath[1].split("\\.")[0];
    String fileLocation = directory + "." + topLevelCdsObject;
    fileLocation = fileLocation.replace('.', XSKConstants.WINDOWS_SEPARATOR);

    return fileLocation;
  }
}
