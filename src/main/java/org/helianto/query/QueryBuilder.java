package org.helianto.query;

/**
 * Query builder interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface QueryBuilder {
	
	/**
	 * Build the query.
	 */
	String build();
	
	/**
	 * The alias used in query.
	 */
	String getAlias();
	
	/**
	 * Generic appender.
	 * 
	 * @param token
	 */
	QueryBuilder append(String token);
	
	/**
	 * String equals appender.
	 * 
	 * @param fieldName
	 * @param constraint
	 */
	QueryBuilder eq(String fieldName, String constraint);
	
	/**
	 * Integer equals appender.
	 * 
	 * @param fieldName
	 * @param constraint
	 */
	QueryBuilder eq(String fieldName, int constraint);
	
	/**
	 * Long equals appender.
	 * 
	 * @param fieldName
	 * @param constraint
	 */
	QueryBuilder eq(String fieldName, long constraint);
	
	/**
	 * Character equals appender.
	 * 
	 * @param fieldName
	 * @param constraint
	 */
	QueryBuilder eq(String fieldName, char constraint);
	
	/**
	 * String like appender.
	 * 
	 * @param fieldName
	 * @param constraint
	 */
	QueryBuilder like(String fieldName, String constraint);
	
	/**
	 * AND appender.
	 */
	QueryBuilder and();
	
	/**
	 * OR appender.
	 */
	QueryBuilder or();

	/**
	 * NOT appender.
	 */
	QueryBuilder not();
	
	/**
	 * Order appender.
	 */
	QueryBuilder appendOrderBy(String... orderList);
	
 	/**
	 * Limit getter.
	 */
	int getLimit(); 

 	/**
	 * Limit setter.
	 * 
	 * @param limit
	 */
	void setLimit(int limit); 
	
	/**
	 * Limit ignore
	 * 
	 */
	boolean isIgnoreLimit();

}
