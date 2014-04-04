package org.helianto.query.jpa;

import static org.junit.Assert.assertEquals;

import org.helianto.query.TestingDomainObject;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JPAQueryBuilderTests {

	@Test
	public void buildSelect() {
		query = new JPAQueryBuilder(TestingDomainObject.class, "field1", "field2");
		query.eq("FIELD", "1");
		assertEquals("select alias.field1, alias.field2 from TestingDomainObject alias where alias.FIELD = '1'"
				, query.build());
	}
	
	@Test
	public void buildOneField() {
		query.eq("FIELD", "1");
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD = '1'"
				, query.build());
	}
	
	@Test
	public void buildTwoFields() {
		query.eq("FIELD", "1").eq("OTHER", "2");
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD = '1' AND alias.OTHER = '2'"
				, query.build());
	}
	
	@Test
	public void buildOrFields() {
		query.eq("FIELD", "1").or().eq("OTHER", "2");
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD = '1' OR alias.OTHER = '2'"
				, query.build());
	}
	
	@Test
	public void buildLike() {
		query.like("FIELD", "TEXT");
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD like '%TEXT%'"
				, query.build());
	}
	
	@Test
	public void buildInt() {
		query.eq("FIELD", 1);
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD = 1"
				, query.build());
	}
	
	@Test
	public void buildLong() {
		query.eq("FIELD", 1L);
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD = 1"
				, query.build());
	}
	
	@Test
	public void buildChar() {
		query.eq("FIELD", 'X');
		assertEquals("select alias from TestingDomainObject alias where alias.FIELD = 'X'"
				, query.build());
	}
	
	@Test
	public void buildOrderBy() {
		query.appendOrderBy("FIELD");
		assertEquals("select alias from TestingDomainObject alias order by alias.FIELD"
				, query.build());
	}
	
	@Test
	public void buildOrderByDesc() {
		query.appendOrderBy("FIELD ASC", "OTHER DESC");
		assertEquals("select alias from TestingDomainObject alias order by alias.FIELD ASC, alias.OTHER DESC"
				, query.build());
	}
	
	//
	
	private JPAQueryBuilder query;
	
	@Before
	public void setUp() {
		query = new JPAQueryBuilder(TestingDomainObject.class);
	}

}
