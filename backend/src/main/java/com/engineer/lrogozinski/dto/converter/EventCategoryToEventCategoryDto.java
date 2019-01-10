package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.dto.EventCategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventCategoryToEventCategoryDto implements Converter<EventCategory,EventCategoryDto> {

    @Override
    public EventCategoryDto convert(EventCategory eventCategory) {

        return EventCategoryDto.builder()
                .name(eventCategory.getName())
                .imageUrl(eventCategory.getImageUrl())
                .build();
    }
}