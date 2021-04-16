package io.dirigible.smart.datasource.object.factory;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NameNotFoundException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.sql.DataSource;
import org.apache.naming.ResourceRef;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Katya Stoycheva
 *
 */
public class SmartDatasourceObjectFactoryTest {
	private Map<String, String> testEnv; 
	private SmartDatasourceObjectFactory factory = new SmartDatasourceObjectFactory();
	private static final String DATASOURCE_NAME = "DefaultDB"; 
	
	@Before
	public void cleanEnv() {
		testEnv = new HashMap<>();
	}
	
	@Test
	public void testPrefixDelimiterDefaultValueIfNoneSet() {
		String result = factory.getPrefixDelimiter(testEnv);
		Assert.assertEquals("Wrong default value", SmartDatasourceObjectFactory.ENV_PREFIX_DELIMITER_DEFAULT, result);
	}
	
	@Test
	public void testPrefixDelimiterDefaultValueIfEmptyStringSet() {
		String customValue = "";
		testEnv.put(SmartDatasourceObjectFactory.ENV_PREFIX_DELIMITER_KEY, customValue);
		
		String result = factory.getPrefixDelimiter(testEnv);
		Assert.assertEquals("Wrong default value", SmartDatasourceObjectFactory.ENV_PREFIX_DELIMITER_DEFAULT, result);
	}
	
	@Test
	public void testPrefixDelimiterCustomValue() {
		final String customValue = "#";
		testEnv.put(SmartDatasourceObjectFactory.ENV_PREFIX_DELIMITER_KEY, customValue);
		
		String result = factory.getPrefixDelimiter(testEnv);
		
		Assert.assertEquals("Wrong custom value", customValue, result);
	}
	
	@Test
	public void testPrefixDelimiterInitialisedOnce() {
		factory.getPrefixDelimiter(testEnv);
		
		testEnv.put(SmartDatasourceObjectFactory.ENV_PREFIX_DELIMITER_KEY, "other_prefix");
		String result = factory.getPrefixDelimiter();
		
		Assert.assertEquals("Prefix value should not be reset", SmartDatasourceObjectFactory.ENV_PREFIX_DELIMITER_DEFAULT, result);
	}
	
	@Test(expected=NameNotFoundException.class)
	public void testShouldProcessResourceObjNotRef() throws NameNotFoundException {
		factory.shouldProcessResourceRef("");
		Assert.fail("NameNotFoundException should be thrown");
		
	}
	
	@Test(expected=NameNotFoundException.class)
	public void testShouldProcessResourceNullObj() throws NameNotFoundException {
		factory.shouldProcessResourceRef(null);
		Assert.fail("NameNotFoundException should be thrown");
	}
	
	@Test
	public void testShouldProcessResourceObjNotDatasource() throws NameNotFoundException {
		Reference ref = new Reference(SmartDatasourceObjectFactory.class.getName());
		Assert.assertFalse("Only Datasource reference should be processed", factory.shouldProcessResourceRef(ref));
	}
	
	@Test
	public void testShouldProcessResourceObjDatasource() throws NameNotFoundException {
		Reference ref = new Reference(DataSource.class.getName());
		Assert.assertTrue("Datasource reference should be processed", factory.shouldProcessResourceRef(ref));
	}
	
	@Test
	public void testEnhanceResourceRefMatchingEnvVars() {
		String key = "url";
		String compositeKey = DATASOURCE_NAME + factory.getPrefixDelimiter() + key;
		String value = "jdbc:mysql://test:3306/mysql";
		testEnv.put(compositeKey, value);
		
		ResourceRef ref = new ResourceRef(DataSource.class.getName(), null, null, null, false);
		factory.enhanceResourceRef(ref, DATASOURCE_NAME, testEnv);
		
		Assert.assertNotNull(ref.get(key));

		Enumeration<RefAddr> refs = ref.getAll();
		while (refs.hasMoreElements()) {
			System.out.println(refs.nextElement());
			System.out.println(DataSource.class.getName());
		}

		String refValue = ref.get(key).toString();
		Assert.assertNotNull(refValue);
		Assert.assertTrue("Wrong ref value", refValue.contains(value));
	}
	
	@Test
	public void testEnhanceResourceRefNoMatchingEnvVars() {
		String key = "url";
		String compositeKey = DATASOURCE_NAME + "not_configured_prefix" + key;
		String value = "jdbc:mysql://test:3306/mysql";
		
		testEnv.put(compositeKey, value);
		
		ResourceRef ref = new ResourceRef(DataSource.class.getName(), null, null, null, false);
		factory.enhanceResourceRef(ref, DATASOURCE_NAME, testEnv);
		
		RefAddr refValue = ref.get(key);
		Assert.assertNull("No value should be set for key " + key, refValue);
	}
	
}
