package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.EventCategoryDto;
import com.engineer.lrogozinski.dto.converter.EventCategoryDtoToEventCategory;
import com.engineer.lrogozinski.dto.converter.EventCategoryToEventCategoryDto;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.security.Constants.HEADER_STRING;
import static com.engineer.lrogozinski.security.Constants.TOKEN_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/eventCategory")
public class EventCategoryController {

    private final EventCategoryService eventCategoryService;

    private final EventCategoryToEventCategoryDto eventCategoryToEventCategoryDto;

    private final EventCategoryDtoToEventCategory eventCategoryDtoToEventCategory;

    private final UserDataService userDataService;

    private final JwtTokenUtil jwtTokenUtil;

    public EventCategoryController(EventCategoryService eventCategoryService, EventCategoryToEventCategoryDto eventCategoryToEventCategoryDto, EventCategoryDtoToEventCategory eventCategoryDtoToEventCategory, UserDataService userDataService, JwtTokenUtil jwtTokenUtil) {
        this.eventCategoryService = eventCategoryService;
        this.eventCategoryToEventCategoryDto = eventCategoryToEventCategoryDto;
        this.eventCategoryDtoToEventCategory = eventCategoryDtoToEventCategory;
        this.userDataService = userDataService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<EventCategoryDto> getAllEventCategories(){

        List<EventCategoryDto> eventCategoryDtoList = new ArrayList<>();
         eventCategoryService.findAll().forEach(eventCategory -> eventCategoryDtoList.add(eventCategoryToEventCategoryDto.convert(eventCategory)));

         return eventCategoryDtoList;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value ="/add", method = RequestMethod.POST)
   // @Transactional
    public ResponseEntity<?> addEventCategoryToFavouriteList(@RequestBody EventCategoryDto eventCategory, HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        UserData userData = userDataService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        EventCategory eventCategoryy = eventCategoryService.findByName(eventCategory.getName());// eventCategoryDtoToEventCategory.convert(eventCategory);
        userData.addFavouriteEventCategory(eventCategoryy);
        userDataService.save(userData);
        return ResponseEntity.ok().build();
    }
}
