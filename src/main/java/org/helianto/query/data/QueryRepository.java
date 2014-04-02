package org.helianto.query.data;

import java.io.Serializable;

import org.helianto.query.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Shared behavior.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 * @param <ID>
 * @param <F>
 */
@NoRepositoryBean
public interface QueryRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * Add find by query.
	 * 
	 * @param query
	 */
	Iterable<T> find(QueryBuilder query);
	
	/**
	 * Add find by query.
	 * 
	 * @param query
	 */
	Iterable<T> find(QueryBuilder query, Pageable pageable);
	
	/**
	 * Add count by query.
	 * 
	 * @param query
	 */
	long count(QueryBuilder query);
	
}
