package org.helianto.query.jpa;

import static org.junit.Assert.assertEquals;

import org.helianto.query.QueryBuilder;
import org.helianto.query.TestingDomainObject;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class QueryBuilderTests {

	@Test
	public void buildEmpty() {
		assertEquals("SELECT_CLAUSE FROM_CLAUSE ", query.build());
	}
	
	@Test
	public void buildToken() {
		query.append("CRITERIA_CLAUSE ");
		assertEquals("SELECT_CLAUSE FROM_CLAUSE where CRITERIA_CLAUSE ", query.build());
	}
	
	//
	
	private QueryBuilder query;
	
	@Before
	public void setUp() {
		query = new AbstractQueryBuilder(TestingDomainObject.class) {
			@Override protected StringBuilder select() { return new StringBuilder("SELECT_CLAUSE "); }
			@Override protected StringBuilder from() { return new StringBuilder("FROM_CLAUSE "); }
			@Override protected StringBuilder orderBy() { return null; }
			public QueryBuilder eq(String fieldName, String constraint) { return this; }
			public QueryBuilder eq(String fieldName, int constraint) { return this; }
			@Override public QueryBuilder eq(String fieldName, long constraint) { return this; }
			@Override public QueryBuilder eq(String fieldName, char constraint) { return this; }
			@Override public QueryBuilder like(String fieldName, String constraint) { return this; }
		};
	}

}
