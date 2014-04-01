helianto-query
==============

Helianto query project

Helianto query builder provides an alternative to type-safe query languages.

Examples:

```java

//  some service method

@Transactional
public List<ADomain> findADomain<String, Object> filterMap) {
    QueryBuilder query = new JPAQueryBuilder(ADomain.class);
    entityFilter.filter(filterMap, query);
    controlFilter.filter(filterMap, query);
    return aDomainRepository.findAll(query);
}

@Transactional
public List<BDomain> findBDomain<String, Object> filterMap) {
    QueryBuilder query = new JPAQueryBuilder(BDomain.class);
    entityFilter.filter(filterMap, query);
    otherFilter.filter(filterMap, query);
    return bDomainRepository.findAll(query);
}

```

All filters above must implement the MapFilter interface.

```java

import java.util.Map;

import org.helianto.core.domain.Entity;
import org.helianto.filter.MapFilter;
import org.helianto.query.QueryBuilder;

/**
 * Entity filter.
 * 
 */
public class EntityFilter 
	implements MapFilter
{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Entity.class);
	}

	public void filter(Map<String, Object> filterMap, QueryBuilder query) {
		if (filterMap.containsKey("entityId")) {
			query.eq("entity.id", (int) filterMap.get("entityId"));
		}
	}

}

```