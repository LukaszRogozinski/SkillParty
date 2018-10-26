package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.dto.EventCategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventCategoryDtoToEventCategory implements Converter<EventCategoryDto, EventCategory> {

    @Override
    public EventCategory convert(EventCategoryDto eventCategoryDto) {

        EventCategory eventCategory = new EventCategory();
       // eventCategory.setName(eventCategoryDto.getName());
        return eventCategory;
    }
}
