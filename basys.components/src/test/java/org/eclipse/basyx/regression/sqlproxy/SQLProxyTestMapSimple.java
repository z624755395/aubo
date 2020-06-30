package org.eclipse.basyx.regression.sqlproxy;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.eclipse.basyx.tools.sqlproxy.SQLMap;
import org.eclipse.basyx.tools.sqlproxy.SQLRootElement;



/**
 * Test SQL map implementation with basic operations
 * 
 * @author kuhn
 *
 */
public class SQLProxyTestMapSimple {

	
	/**
	 * Store SQL root element reference
	 * - An SQL root element is the main gateway to a SQL database
	 */
	protected SQLRootElement sqlRootElement = null;

	
	
	/**
	 * Test basic operations
	 */
	@Test
	public void test() throws Exception {
		// Create SQL root element
		// - Every root element needs a unique table ID
		sqlRootElement = new SQLRootElement(SQLConfig.SQLUSER, SQLConfig.SQLPW,  "//localhost/basyx-map?", "org.postgresql.Driver", "jdbc:postgresql:", "root_el_01");

		
		// Drop tables to make sure we start with a fresh database
		sqlRootElement.dropTable(1);
		sqlRootElement.dropRootTable();

		
		// Create new table in database for root element
		sqlRootElement.createRootTable();

		// Create new SQL map
		Map<String, Object> sqlMap = sqlRootElement.createMap(1);

		// Clear map to make sure that no old data from previous test runs is stored in it
		sqlMap.clear();
		
		// Test initial insert operations and resulting map size
		assertTrue(sqlMap.size() == 0);
		sqlMap.put("abc", 14);
		assertTrue(sqlMap.size() == 1);
		sqlMap.put("abd", 15);
		assertTrue(sqlMap.size() == 2);
		
		// Test if map is empty
		assertTrue(!sqlMap.isEmpty());
		
		// Test retrieving of values
		assertTrue((int) sqlMap.get("abc") == 14);
		assertTrue((int) sqlMap.get("abd") == 15);

		// Test map size again
		assertTrue(sqlMap.size() == 2);

		// Change map element, and test size and value again
		sqlMap.put("abc", 21);
		assertTrue(sqlMap.size() == 2);
		assertTrue((int) sqlMap.get("abc") == 21);
		assertTrue(sqlMap.size() == 2);

		// Add another map element, read elements and size back
		sqlMap.put("abe", 16);
		assertTrue(sqlMap.size() == 3);
		assertTrue((int) sqlMap.get("abc") == 21);
		assertTrue((int) sqlMap.get("abd") == 15);
		assertTrue((int) sqlMap.get("abe") == 16);
		assertTrue(sqlMap.size() == 3);

		// Check if map contains keys
		assertTrue(sqlMap.containsKey("abc"));		
		assertTrue(!sqlMap.containsKey("xyzz"));		

		// Check if map contains values
		assertTrue(sqlMap.containsValue(21));		
		assertTrue(sqlMap.containsValue(16));		
		assertTrue(!sqlMap.containsValue('x'));		

		// Remove map element, check values and size	
		Object value = sqlMap.remove("abe");
		assertTrue((int) value == 16);
		assertTrue((int) sqlMap.get("abc") == 21);
		assertTrue((int) sqlMap.get("abd") == 15);
		assertTrue(sqlMap.size() == 2);
		assertTrue(sqlMap.get("abe") == null);
		
		
		// Connect to existing map
		Map<String, Object> sqlMap2 = new SQLMap(sqlRootElement, 1);
		// - Check map elements
		assertTrue((int) sqlMap2.get("abc") == 21);
		assertTrue((int) sqlMap2.get("abd") == 15);
		assertTrue(sqlMap2.size() == 2);

		
		// Clear map, check size
		sqlMap.clear();
		assertTrue(sqlMap.size() == 0);
		
		// Test if map is empty
		assertTrue(sqlMap.isEmpty());
	}
}
