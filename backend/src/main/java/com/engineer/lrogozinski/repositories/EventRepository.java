package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {

}
