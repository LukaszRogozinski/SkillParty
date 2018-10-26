package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventToEventDto implements Converter<Event, EventDto> {



    @Override
    public EventDto convert(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .avaliableQuantity(event.getAvaliableQuantity())
                .price(event.getPrice())
                .averageVote(event.getAverageVote())
                .eventCategory(event.getEventCategory().getName())
                .imageUrl(event.getImageUrl())
                .build();
    }
}
