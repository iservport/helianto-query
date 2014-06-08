package org.helianto.query.jpa;

import org.helianto.query.QueryBuilder;

/**
 * Decora um {@link AbstractQueryBuilder} para modificar a cláusula de seleção e permitir contagem.
 * 
 * @author mauriciofernandesdecastro
 */
public class JPAQueryCountBuilderDecorator 
	extends JPAQueryBuilder
{
	private final AbstractQueryBuilder query;
	
	public JPAQueryCountBuilderDecorator(AbstractQueryBuilder query) {
		super(query.getClazz());
		this.query = query;
	}
	
	@Override
	protected StringBuilder getInternalBuilder() {
		return query.getInternalBuilder();
	}

	@Override
	protected StringBuilder select() {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(").append(getAlias()).append(".id) ");
		return builder;
	}
	
	@Override
	protected StringBuilder from() {
		return query.from();
	}
	
	@Override
	protected StringBuilder orderBy() {
		return query.orderBy();
	}

	@Override
	public String getAlias() {
		return query.getAlias();
	}

	@Override
	public QueryBuilder append(String token) {
		return query.append(token);
	}

	@Override
	public QueryBuilder eq(String fieldName, String constraint) {
		return query.eq(fieldName, constraint);
	}

	@Override
	public QueryBuilder eq(String fieldName, int constraint) {
		return query.eq(fieldName, constraint);
	}

	@Override
	public QueryBuilder eq(String fieldName, long constraint) {
		return query.eq(fieldName, constraint);
	}

	@Override
	public QueryBuilder eq(String fieldName, char constraint) {
		return query.eq(fieldName, constraint);
	}

	@Override
	public QueryBuilder like(String fieldName, String constraint) {
		return query.like(fieldName, constraint);
	}

	@Override
	public QueryBuilder and() {
		return query.and();
	}

	@Override
	public QueryBuilder or() {
		return query.or();
	}

	@Override
	public QueryBuilder not() {
		return query.not();
	}
	
	@Override
	public boolean isIgnoreLimit() {
		return true;
	}

}
