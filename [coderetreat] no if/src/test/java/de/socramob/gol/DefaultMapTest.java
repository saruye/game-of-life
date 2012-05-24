package de.socramob.gol;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultMapTest {

	Map<String, Integer> defaultMap;

	@Before
	public void setUp() throws Exception {
		defaultMap = new DefaultMap<String, Integer>(-1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNormalReturn() {
		defaultMap.put("Drei", 3);
		assertEquals(new Integer(3), defaultMap.get("Drei"));
	}

	@Test
	public void testDefaultReturn() {
		defaultMap.put("Drei", 3);
		assertEquals(new Integer(-1), defaultMap.get("Sieben"));
		assertEquals(new Integer(-1), defaultMap.get(null));
	}

}
