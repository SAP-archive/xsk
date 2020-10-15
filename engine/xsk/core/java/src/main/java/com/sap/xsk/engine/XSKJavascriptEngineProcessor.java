package com.sap.xsk.engine;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.engine.js.api.IJavascriptEngineProcessor;

/**
 * The HANA XS Classic Javascript Engine Processor.
 */
public class XSKJavascriptEngineProcessor implements IJavascriptEngineProcessor {

	@Inject
	private XSKJavascriptEngineExecutor rhinoEngineExecutor;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.engine.js.api.IJavascriptEngineProcessor#executeService(java.lang.String)
	 */
	@Override
	public void executeService(String module) throws ScriptingException {
		Map<Object, Object> executionContext = new HashMap<Object, Object>();
		rhinoEngineExecutor.executeServiceModule(module, executionContext);
	}

}
