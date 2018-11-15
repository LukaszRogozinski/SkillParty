package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.dto.EventCategoryDto;

import java.util.List;

public interface EventCategoryService {

    List<EventCategory> findAll();

    EventCategory findById(Integer id);

    EventCategory save(EventCategory object);

    void delete(EventCategory object);

    void deleteById(Integer id);

    EventCategory findByName(String name);
}
