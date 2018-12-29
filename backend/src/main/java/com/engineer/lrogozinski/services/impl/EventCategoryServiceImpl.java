package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventCategoryDto;
import com.engineer.lrogozinski.dto.converter.EventCategoryToEventCategoryDto;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.EventCategoryRepository;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_EVENT_CATEGORY_WITH_PROVIDED_ID;
import static com.engineer.lrogozinski.security.Constants.HEADER_STRING;
import static com.engineer.lrogozinski.security.Constants.TOKEN_PREFIX;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {

    private final EventCategoryRepository eventCategoryRepository;

    private final UserDataService userDataService;

    private final JwtTokenUtil jwtTokenUtil;

    private final EventCategoryToEventCategoryDto eventCategoryToEventCategoryDto;


    public EventCategoryServiceImpl(EventCategoryRepository eventCategoryRepository, UserDataService userDataService, JwtTokenUtil jwtTokenUtil, EventCategoryToEventCategoryDto eventCategoryToEventCategoryDto) {
        this.eventCategoryRepository = eventCategoryRepository;
        this.userDataService = userDataService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.eventCategoryToEventCategoryDto = eventCategoryToEventCategoryDto;
    }

    @Override
    public List<EventCategoryDto> findAll() {
        List<EventCategoryDto> eventCategoryDtoList = new ArrayList<>();
        eventCategoryRepository.findAll().forEach(eventCategory -> eventCategoryDtoList.add(eventCategoryToEventCategoryDto.convert(eventCategory)));
        return eventCategoryDtoList;
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

    @Override
    public UserData addFavouriteEventCategoryToLoggedUser(String eventCategoryName,  HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        UserData userData = userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        EventCategory eventCategory = eventCategoryRepository.findByName(eventCategoryName);
        userData.addFavouriteEventCategory(eventCategory);
        return userDataService.save(userData);
    }
}
