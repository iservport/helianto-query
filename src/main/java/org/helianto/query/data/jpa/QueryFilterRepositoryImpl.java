package org.helianto.query.data.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.helianto.query.QueryBuilder;
import org.helianto.query.data.QueryRepository;
import org.helianto.query.jpa.AbstractQueryBuilder;
import org.helianto.query.jpa.JPAQueryCountBuilderDecorator;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Query filter factory implementation.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 * @param <ID>
 */
public class QueryFilterRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements QueryRepository<T, ID> {

	private EntityManager entityManager;

	public QueryFilterRepositoryImpl(JpaEntityInformation<T, ID> metadata, EntityManager entityManager) {
		super(metadata, entityManager);
		this.entityManager = entityManager;
	}

	public long count(QueryBuilder query) {
		QueryBuilder decoratedQuery = new JPAQueryCountBuilderDecorator((AbstractQueryBuilder) query);
		if(!query.isIgnoreLimit() && query.getLimit()>0) {
			return (Long) entityManager.createQuery(decoratedQuery.build()).setMaxResults(query.getLimit()).getSingleResult();
		}		
		return (Long) entityManager.createQuery(decoratedQuery.build()).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public Iterable<T> find(QueryBuilder query, Pageable pageable) {
		// TODO consider the Pageable interface too.
		if(!query.isIgnoreLimit() && query.getLimit()>0) {
			return entityManager.createQuery(query.build()).setMaxResults(query.getLimit()).getResultList();
		}		
		return entityManager.createQuery(query.build()).getResultList();
	}
	
	public Iterable<T> find(QueryBuilder query) {
		return find(query, null);
	}
	
	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		if (entityManager instanceof HibernateEntityManager) {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(entity);
			return entity;
		}
		return super.save(entity);
	}
	
}