package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.dto.EventCategoryDto;

public interface EventCategoryService extends CrudService<EventCategory, Integer> {
    EventCategory findByName(String name);
}
