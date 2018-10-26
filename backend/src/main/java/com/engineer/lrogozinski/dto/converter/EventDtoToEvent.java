package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.services.EventCategoryService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EventDtoToEvent  implements Converter<EventDto, Event> {

    private final EventCategoryService eventCategoryService;

    public EventDtoToEvent(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @Override
    public Event convert(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setAvaliableQuantity(eventDto.getAvaliableQuantity());
        event.setPrice(eventDto.getPrice());
        event.setAverageVote(eventDto.getAverageVote());
        event.setImageUrl(eventDto.getImageUrl());
        event.setEventCategory(eventCategoryService.findByName(eventDto.getEventCategory()));
        return event;
    }
}
