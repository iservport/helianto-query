package org.helianto.query.filter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.helianto.query.QueryBuilder;
import org.helianto.query.TestingDomainObject;
import org.helianto.query.filter.MapFilter;
import org.helianto.query.jpa.JPAQueryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterTests {

	@Test
	public void empty() {
		filter.filter(map, query);
		assertEquals("select alias from TestingDomainObject "
				, query.build());
	}
	
	@Test
	public void supported() {
		map.put("otherId", 1);
		map.put("fieldOneId", 2);
		map.put("fieldTwo", 'X');
		filter.filter(map, query);
		assertEquals("select alias from TestingDomainObject where alias.fieldOne.id = 2 AND alias.fieldTwo = 'X'"
				, query.build());
	}
	
	//
	
	private QueryBuilder query;
	private MapFilter filter;
	private Map<String, Object> map;
	
	@Before
	public void setUp() {
		query = new JPAQueryBuilder(TestingDomainObject.class);
		filter = new ObjectFilterStub();
		map = new HashMap<>();
	}

}
