package org.helianto.filter;

import java.util.Map;

import org.helianto.query.QueryBuilder;
import org.helianto.query.TestingDomainObject;

/**
 * Testing stub.
 * 
 * @author mauriciofernandesdecastro
 */
public class ObjectFilterStub 
	implements MapFilter
{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(TestingDomainObject.class);
	}

	@Override
	public void filter(Map<String, Object> filterMap, QueryBuilder query) {
		if (filterMap.containsKey("fieldOneId")) {
			query.eq("fieldOne.id", (int) filterMap.get("fieldOneId"));
		}
		if (filterMap.containsKey("fieldTwo")) {
			query.eq("fieldTwo", (Character) filterMap.get("fieldTwo"));
		}
	}

}
