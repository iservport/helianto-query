package org.helianto.query.jpa;

import static org.junit.Assert.assertEquals;

import org.helianto.query.QueryBuilder;
import org.helianto.query.TestingDomainObject;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JPAQueryCountBuilderDecoratorTests {

	@Test
	public void test() {
		JPAQueryBuilder query = new JPAQueryBuilder(TestingDomainObject.class);
		QueryBuilder decoratedQuery = new JPAQueryCountBuilderDecorator(query);
		query.eq("FIELD", "1");
		assertEquals("select count(alias.id) from TestingDomainObject alias where alias.FIELD = '1'"
				, decoratedQuery.build());
	}

}
