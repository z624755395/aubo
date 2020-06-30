package org.eclipse.basyx.testsuite.regression.vab.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.basyx.vab.model.VABModelMap;
import org.junit.Test;

/**
 * Tests the VABModelMap
 * 
 * @author schnicke
 *
 */
public class VABModelMapTest {

	/**
	 * Tests the behaviour of the getPath method
	 */
	@Test
	public void testGetPath() {
		// Build VABModelMap
		VABModelMap<Object> map = new VABModelMap<>();
		map.putPath("a/b/c", 12);
		map.putPath("a/b/d", 13);
		
		// Build expected output
		Map<String, Object> b = new HashMap<>();
		b.put("c", 12);
		b.put("d", 13);

		Map<String, Object> a = new HashMap<>();
		a.put("b", b);
		
		Map<String, Object> root = new HashMap<>();
		root.put("a", a);

		// Assert correct behaviour of getPath
		assertEquals(b, map.getPath("a/b"));
		assertEquals(a, map.getPath("a"));
		assertEquals(root, map.getPath(""));
	}

}
