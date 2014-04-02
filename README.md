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


## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues].

 
## LICENSE

Copyright 2014 iServ Consultoria Empresarial Ltda.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
