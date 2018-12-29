package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventCategoryDto;
import com.engineer.lrogozinski.dto.converter.EventCategoryDtoToEventCategory;
import com.engineer.lrogozinski.dto.converter.EventCategoryToEventCategoryDto;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/eventCategory")
public class EventCategoryController {

    private final EventCategoryService eventCategoryService;


    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<EventCategoryDto> getAllEventCategories(){

         return eventCategoryService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public UserData addEventCategoryToFavouriteList(@RequestBody EventCategoryDto eventCategoryDto, HttpServletRequest req) {
       return eventCategoryService.addFavouriteEventCategoryToLoggedUser(eventCategoryDto.getName(), req);
    }
}
