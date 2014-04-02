package org.helianto.query.filter;

import java.util.Map;

import org.helianto.query.QueryBuilder;

/**
 * Mapping filter interface.
 * 
 * <p>
 * A map filter is designed to modify a query string in response to the contents of a map. Map filters may
 * be applied in sequence to produce the required query.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
public interface MapFilter {
	
	/**
	 * True if this filter is applicable.
	 * 
	 * @param clazz
	 */
	boolean supports(Class<?> clazz);
	
	/**
	 * Apply the filter to the query builder according to the given map.
	 * 
	 * @param filterMap
	 * @param query
	 */
	void filter(Map<String, Object> filterMap, QueryBuilder query);

}
