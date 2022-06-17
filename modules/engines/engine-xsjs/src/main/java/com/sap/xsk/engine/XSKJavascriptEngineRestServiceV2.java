package com.sap.xsk.engine;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.http.HttpRequestFacade;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.js.graalium.GraalJSCodeRunner;
import org.eclipse.dirigible.engine.js.graalium.core.polyfills.JSGlobalObject;
import org.eclipse.dirigible.engine.js.graalium.core.polyfills.JSPolyfill;
import org.eclipse.dirigible.engine.js.graalium.core.polyfills.RequirePolyfill;
import org.eclipse.dirigible.engine.js.graalium.platform.internal.modules.DirigibleCoreModuleResolver;
import org.eclipse.dirigible.engine.js.graalium.platform.internal.polyfills.DirigibleContextGlobalObject;
import org.eclipse.dirigible.engine.js.graalium.platform.internal.polyfills.DirigibleEngineTypeGlobalObject;
import org.eclipse.dirigible.engine.js.service.CodeRunnerPool;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.graalvm.polyglot.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.eclipse.dirigible.engine.js.graalium.CodeRunner;
import org.eclipse.dirigible.engine.js.graalium.platform.internal.modules.DirigibleSourceProvider;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

@Path("/xsk")
public class XSKJavascriptEngineRestServiceV2 extends AbstractRestService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(org.eclipse.dirigible.engine.js.service.JavascriptEngineRestServiceV2.class.getCanonicalName());
  private static final String HTTP_PATH_MATCHER = "/{projectName}/{projectFilePath:.*\\.js|.*\\.mjs|.*\\.xsjs}";
  private static final String HTTP_PATH_WITH_PARAM_MATCHER = "/{projectName}/{projectFilePath:.*\\.js|.*\\.mjs|.*\\.xsjs}/{projectFilePathParam}";
  private final DirigibleSourceProvider dirigibleSourceProvider = new DirigibleSourceProvider();

  @GET
  @Path(HTTP_PATH_MATCHER)
  public Response get(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeJavaScript(projectName, projectFilePath);
  }

  @GET
  @Path(HTTP_PATH_WITH_PARAM_MATCHER)
  public Response get(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath,
      @PathParam("projectFilePathParam") String projectFilePathParam
  ) {
    return executeJavaScript(projectName, projectFilePath, projectFilePathParam);
  }

  @POST
  @Path(HTTP_PATH_MATCHER)
  public Response post(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeJavaScript(projectName, projectFilePath);
  }

  @POST
  @Path(HTTP_PATH_WITH_PARAM_MATCHER)
  public Response post(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath,
      @PathParam("projectFilePathParam") String projectFilePathParam
  ) {
    return executeJavaScript(projectName, projectFilePath, projectFilePathParam);
  }

  @PUT
  @Path(HTTP_PATH_MATCHER)
  public Response put(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeJavaScript(projectName, projectFilePath);
  }

  @PUT
  @Path(HTTP_PATH_WITH_PARAM_MATCHER)
  public Response put(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath,
      @PathParam("projectFilePathParam") String projectFilePathParam
  ) {
    return executeJavaScript(projectName, projectFilePath, projectFilePathParam);
  }

  @PATCH
  @Path(HTTP_PATH_MATCHER)
  public Response patch(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeJavaScript(projectName, projectFilePath);
  }

  @PATCH
  @Path(HTTP_PATH_WITH_PARAM_MATCHER)
  public Response patch(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath,
      @PathParam("projectFilePathParam") String projectFilePathParam
  ) {
    return executeJavaScript(projectName, projectFilePath, projectFilePathParam);
  }

  @DELETE
  @Path(HTTP_PATH_MATCHER)
  public Response delete(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeJavaScript(projectName, projectFilePath);
  }

  @DELETE
  @Path(HTTP_PATH_WITH_PARAM_MATCHER)
  public Response delete(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath,
      @PathParam("projectFilePathParam") String projectFilePathParam
  ) {
    return executeJavaScript(projectName, projectFilePath, projectFilePathParam);
  }

  @HEAD
  @Path(HTTP_PATH_MATCHER)
  public Response head(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeJavaScript(projectName, projectFilePath);
  }

  @HEAD
  @Path(HTTP_PATH_WITH_PARAM_MATCHER)
  public Response head(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath,
      @PathParam("projectFilePathParam") String projectFilePathParam
  ) {
    return executeJavaScript(projectName, projectFilePath, projectFilePathParam);
  }

  private Response executeJavaScript(String projectName, String projectFilePath) {
    return executeJavaScript(projectName, projectFilePath, "");
  }

  private Response executeJavaScript(String projectName, String projectFilePath, String projectFilePathParam) {
    try {
      if (HttpRequestFacade.isValid()) {
        HttpRequestFacade.setAttribute(HttpRequestFacade.ATTRIBUTE_REST_RESOURCE_PATH, projectFilePathParam);
      }

      String maybeJSCode = dirigibleSourceProvider.getSource(projectName, projectFilePath);
      if (maybeJSCode == null) {
        return Response.status(404).build();
      }

      Source jsSource = Source.newBuilder("js", maybeJSCode, projectFilePath).build();

      if ("test".equals(projectName)) {
        CodeRunnerPool.CodeRunnerPoolable codeRunnerPoolable = CodeRunnerPool.get();
        try {
          CodeRunner codeRunner = codeRunnerPoolable.getCodeRunner();
          codeRunner.run(jsSource);
        } finally {
          if (codeRunnerPoolable != null) {
            codeRunnerPoolable.release();
          }
        }
      } else {
        CodeRunner codeRunner = createJavaScriptCodeRunner(projectName);
        codeRunner.run(jsSource);
      }

      return Response.ok().build();
    } catch (Throwable e) {
      String message = e.getMessage();
      LOGGER.error(message, e);
      createErrorResponseInternalServerError(message);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
  }

  private static CodeRunner createJavaScriptCodeRunner(String projectName) {
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    java.nio.file.Path projectPath = java.nio.file.Path.of(projectName);
    java.nio.file.Path repositoryRootPath = java.nio.file.Path.of(repository.getRepositoryPath());
    java.nio.file.Path projectDirectoryPath = repositoryRootPath.resolve("registry/public").resolve(projectPath);

    java.nio.file.Path cachePath = projectDirectoryPath.resolve("caches");
    java.nio.file.Path coreModulesESMProxiesCachePath = cachePath.resolve("core-modules-proxies-cache");
    return GraalJSCodeRunner
        .newBuilder(projectDirectoryPath, cachePath)
        .addJSPolyfill(new RequirePolyfill())
        .addJSPolyfill(new XskApiPolyfill())
        .addJSPolyfill(new ErrorFileNamePolyfill())
        .addGlobalObject(new DirigibleContextGlobalObject(new HashMap<>()))
        .addGlobalObject(new DirigibleEngineTypeGlobalObject())
        .addGlobalObject(new SourceProviderGlobalObject())
        .addModuleResolver(new DirigibleCoreModuleResolver(coreModulesESMProxiesCachePath))
        .waitForDebugger(false)
        .build();

//    return DirigibleCodeRunnerFactory.createDirigibleJSCodeRunner(projectDirectoryPath);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  public Class<? extends IRestService> getType() {
    return org.eclipse.dirigible.engine.js.service.JavascriptEngineRestServiceV2.class;
  }

  private static class XskApiPolyfill implements JSPolyfill {

    private static final String POLYFILL_LOCATION = "/js/polyfills/XskApiPolyfill.js";

    @Override
    public String getSource() {
      try {
        InputStream polyfill = XskApiPolyfill.class.getResourceAsStream(POLYFILL_LOCATION);
        if (polyfill == null) {
          throw new IOException("Polyfill '" + POLYFILL_LOCATION + "' not found in resources!");
        } else {
          return IOUtils.toString(polyfill, Charset.defaultCharset());
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public String getFileName() {
      return POLYFILL_LOCATION;
    }
  }

  private static class ErrorFileNamePolyfill implements JSPolyfill {

    private static final String POLYFILL_LOCATION = "/js/polyfills/ErrorFileNamePolyfill.js";

    @Override
    public String getSource() {
      try {
        InputStream polyfill = ErrorFileNamePolyfill.class.getResourceAsStream(POLYFILL_LOCATION);
        if (polyfill == null) {
          throw new IOException("Polyfill '" + POLYFILL_LOCATION + "' not found in resources!");
        } else {
          return IOUtils.toString(polyfill, Charset.defaultCharset());
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public String getFileName() {
      return POLYFILL_LOCATION;
    }
  }

  private static class SourceProviderGlobalObject implements JSGlobalObject {

    private static final XSKRepositoryModuleSourceProvider sourceProvider = new XSKRepositoryModuleSourceProvider(
        new XSKJavascriptEngineExecutor(), IRepositoryStructure.PATH_REGISTRY_PUBLIC);

    @Override
    public String getName() {
      return "SourceProvider";
    }

    @Override
    public Object getValue() {
      return sourceProvider;
    }
  }
}
