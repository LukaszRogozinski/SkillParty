package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.EventCategoryRepository;
import com.engineer.lrogozinski.services.EventCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_EVENT_CATEGORY_WITH_PROVIDED_ID;

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
        return eventCategoryRepository.findById(id).orElseThrow(() -> new ServiceException(CANNOT_FIND_EVENT_CATEGORY_WITH_PROVIDED_ID));
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
