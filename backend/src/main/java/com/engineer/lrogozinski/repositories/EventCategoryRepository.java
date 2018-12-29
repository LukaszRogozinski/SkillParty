package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.EventCategory;
import org.springframework.data.repository.CrudRepository;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {

    EventCategory findByName(String name);
}
