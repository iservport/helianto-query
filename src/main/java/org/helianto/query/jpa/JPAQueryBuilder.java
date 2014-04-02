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
	
	private String[] fields = new String[0];
	
	/**
	 * Required constructor.
	 * 
	 * @param clazz
	 */
	public JPAQueryBuilder(Class<?> clazz) {
		super(clazz);
	}
	
	/**
	 * Selectable fields constructor.
	 * 
	 * <p>
	 * If any field is supplied, the select clause will include only them.
	 * </p>
	 * 
	 * @param clazz
	 * @param fields
	 */
	public JPAQueryBuilder(Class<?> clazz, String... fields) {
		this(clazz);
		setFields(fields);
	}
	
	/**
	 * Selectable fields property.
	 */
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	
	/**
	 * This implementation reacts to the fields array content. If the array is empty, all fields are
	 * selected. Otherwise, only the fields indicated by the array are selected.
	 */
	protected StringBuilder select() {
		StringBuilder builder = new StringBuilder();
		builder.append("select ");
		if (getFields().length>0) {
			String separator = "";
			for (String field: getFields()) {
				builder.append(separator).append(getAlias()).append(".").append(field);
				separator = ", ";
			}
		}
		else {
			builder.append(getAlias());
		}
		builder.append(" ");
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
