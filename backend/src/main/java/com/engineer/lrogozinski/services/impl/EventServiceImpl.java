package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.dto.EventDto;
import com.engineer.lrogozinski.dto.converter.EventDtoToEvent;
import com.engineer.lrogozinski.dto.converter.EventToEventDto;
import com.engineer.lrogozinski.repositories.EventRepository;
import com.engineer.lrogozinski.services.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    private EventDtoToEvent eventDtoToEvent;

    private EventToEventDto eventToEventDto;

    public EventServiceImpl(EventRepository eventRepository, EventDtoToEvent eventDtoToEvent, EventToEventDto eventToEventDto) {
        this.eventRepository = eventRepository;
        this.eventDtoToEvent = eventDtoToEvent;
        this.eventToEventDto = eventToEventDto;
    }

    @Override
    public List<Event> findAll() {
        List<Event> eventList = new ArrayList<>();
        eventRepository.findAll().forEach(eventList::add);
        return eventList;
    }

    @Override
    public Event findById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event object) {
        return eventRepository.save(object);
    }

    @Override
    public void delete(Event object) {
        eventRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> findAllDto() {
        List<EventDto> eventDtoList = new ArrayList<>();
        eventRepository.findAll().forEach(event -> {
            eventDtoList.add(eventToEventDto.convert(event));
        });
        return eventDtoList;
    }

    @Override
    public EventDto findByIdDto(Integer id) {
        return eventToEventDto.convert(eventRepository.findById(id).orElse(null));
    }

    @Override
    public void saveDto(EventDto object) {
        eventRepository.save(eventDtoToEvent.convert(object));
    }

}
