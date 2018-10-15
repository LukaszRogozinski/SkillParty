package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.repositories.EventCategoryRepository;
import com.engineer.lrogozinski.services.EventCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {

    private EventCategoryRepository eventCategoryRepository;

    public EventCategoryServiceImpl(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
    }

    @Override
    public List<EventCategory> findAll() {
        List<EventCategory> eventCategoryList = new ArrayList<>();
        eventCategoryRepository.findAll().forEach(eventCategoryList::add);
        return eventCategoryList;
    }

    @Override
    public EventCategory findById(Integer id) {
        return eventCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public EventCategory save(EventCategory object) {
        return eventCategoryRepository.save(object);
    }

    @Override
    public void delete(EventCategory object) {
        eventCategoryRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        eventCategoryRepository.deleteById(id);
    }

    @Override
    public EventCategory findByName(String name) {
        return eventCategoryRepository.findByName(name);
    }
}
