package org.helianto.query.data.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.helianto.query.data.QueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * Query repository factory bean.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <R>
 * @param <T>
 * @param <I>
 */
public class QueryRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, I> {

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new FilterRepositoryFactory<T, I>(entityManager);
	}

	/**
	 * Repository factory inner class.
	 * 
	 * @author mauriciofernandesdecastro
	 *
	 * @param <T>
	 * @param <I>
	 */
	private static class FilterRepositoryFactory<T, I extends Serializable> extends
			JpaRepositoryFactory {

		/**
		 * Constructor.
		 * 
		 * @param entityManager
		 */
		public FilterRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		@SuppressWarnings("unchecked")
		protected JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
			return new QueryFilterRepositoryImpl<T, I>((JpaEntityInformation<T, I>) getEntityInformation(metadata.getDomainType()), entityManager);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return QueryRepository.class;
		}
	}
}