package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Event;
import com.engineer.lrogozinski.repositories.EventRepository;
import com.engineer.lrogozinski.services.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
}
