package org.helianto.query.jpa;

import org.helianto.query.QueryBuilder;

/**
 * Base class to any class implementing {@link QueryBuilder}.
 * 
 * <p>
 * Provides basic features as appropriate aliasing and segment counting.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractQueryBuilder 
	implements QueryBuilder
{
	
	private Class<?> clazz;
	private String alias = "alias";
	private boolean suppressNextConnector = false;
	private String[] orderList = new String[0];
	
	protected final StringBuilder internalBuilder;
	
	/**
	 * Default constructor.
	 */
	protected AbstractQueryBuilder(Class<?> clazz) {
		super();
		setClazz(clazz);
		internalBuilder = new StringBuilder();
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Alias.
	 */
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	protected void setAlias(Class<?> clazz) {
		setAlias(uncapFirst(clazz.getSimpleName()));
	}
	
	/**
	 * Order list.
	 * 
	 * @return
	 */
	public String[] getOrderList() {
		return orderList;
	}
	
	/**
	 * Hook for selection clause.
	 */
	protected abstract StringBuilder select();
	
	/**
	 * Hook for from clause.
	 */
	protected abstract StringBuilder from();
	
	/**
	 * Hook for order clause.
	 */
	protected abstract StringBuilder orderBy();
	
	public QueryBuilder append(String token) {
		internalBuilder.append(token);
		return this;
	}

    /**
     * Change first character to lower case.
     * 
     * @param name
     */
	protected static String uncapFirst(String name) {
    	return new StringBuilder(name)
		.replace(0, 1, String.valueOf(name.charAt(0)).toLowerCase())
		.toString();
    }
    
	/**
	 * Field name appender.
	 * 
	 * <p>
	 * Prepends automatically the AND connector unless another is explicitly supplied before
	 * the call.
	 * </p>
	 * 
	 * @param fieldName
	 */
	protected QueryBuilder appendFieldName(String fieldName) {
		and().append(getAlias()).append(".").append(fieldName);
		return this;
	}

	public QueryBuilder appendOrderBy(String... orderList) {
		this.orderList = orderList;
		return this;
	}
		
	/**
	 * AND connector.
	 */
	public QueryBuilder and() {
		if (suppressNextConnector) {
			suppressNextConnector = false;
			return this;
		}
		return connect("AND", requiresConnector());
	}

	/**
	 * OR connector.
	 */
	public QueryBuilder or() {
		suppressNextConnector = true;
		return connect("OR", requiresConnector());
	}

	/**
	 * NOT connector.
	 */
	public QueryBuilder not() {
		return connect("NOT", true);
	}

	/**
	 * Generic connector.
	 * 
	 * @param force
	 */
	protected QueryBuilder connect(String connector, boolean force) {
		if (force) {
			internalBuilder.append(" ").append(connector).append(" ");
		}
		return this;
	}
	
	/**
	 * True if not the first criterion.
	 */
	protected boolean requiresConnector() {
		return internalBuilder.length()>0;
	}
	
	public String build() {
		StringBuilder builder = select();
		builder.append(from());
		if (internalBuilder.length()>0) {
			builder.append("where ").append(internalBuilder);
		}
		if (getOrderList().length>0) {
			builder.append("order by ");
			String separator = "";
			for (String orderField: getOrderList()) {
				builder.append(separator).append(getAlias()).append(".").append(orderField);
				separator = ", ";
			}
		}
		return builder.toString();
	}

}
