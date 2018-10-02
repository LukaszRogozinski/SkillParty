package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
