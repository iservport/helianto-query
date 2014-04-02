package org.helianto.query.jpa;

import org.helianto.query.QueryBuilder;

/**
 * Query builder implementation for JPA.
 * 
 * @author mauriciofernandesdecastro
 */
public class JPAQueryBuilder 
	extends AbstractQueryBuilder
{
	
	/**
	 * Required constructor.
	 * 
	 * @param clazz
	 */
	public JPAQueryBuilder(Class<?> clazz) {
		this(clazz, "alias");
	}
	
	/**
	 * Alias constructor.
	 * 
	 * @param clazz
	 * @param alias
	 */
	public JPAQueryBuilder(Class<?> clazz, String alias) {
		super(clazz);
		setAlias(alias);
	}
	
	protected StringBuilder select() {
		StringBuilder builder = new StringBuilder();
		builder.append("select ").append(getAlias()).append(" ");
		return builder;
	}
	
	protected StringBuilder from() {
		StringBuilder builder = new StringBuilder();
		builder.append("from ").append(getClazz().getSimpleName()).append(" ");
		return builder;
	}
	
	@Override
	protected StringBuilder orderBy() {
		StringBuilder builder = new StringBuilder();
		builder.append("order by ").append("");
		return builder;
	}
	
	public QueryBuilder eq(String fieldName, String constraint) {
		appendFieldName(fieldName).append(" = ").append("'").append(constraint).append("'");
		return this;
	}
	
	public QueryBuilder eq(String fieldName, int constraint) {
		appendFieldName(fieldName).append(" = ").append(String.valueOf(constraint));
		return this;
	}
	
	public QueryBuilder eq(String fieldName, long constraint) {
		appendFieldName(fieldName).append(" = ").append(String.valueOf(constraint));
		return this;
	}
	
	public QueryBuilder eq(String fieldName, char constraint) {
		appendFieldName(fieldName).append(" = ").append("'").append(String.valueOf(constraint)).append("'");
		return this;
	}
	
	public QueryBuilder like(String fieldName, String constraint) {
		appendFieldName(fieldName).append(" like ").append("'%").append(constraint).append("%'");
		return this;
	}
	
}
