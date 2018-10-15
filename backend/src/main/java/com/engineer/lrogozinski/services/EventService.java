package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;

import java.util.List;

public interface EventService{
    List<Event> findAll();

    Event findById(Integer id);

    Event save(Event object);

    void delete(Event object);

    void deleteById(Integer id);

    List<EventDto> findAllDto();

    EventDto findByIdDto(Integer id);

    void saveDto(EventDto object);
}
