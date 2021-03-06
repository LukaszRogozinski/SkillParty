package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.UserDataDto;
import com.engineer.lrogozinski.dto.converter.UserDataDtoToUserData;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.EventCategoryRepository;
import com.engineer.lrogozinski.repositories.UserDataRepository;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_USER_DATA_WITH_PROVIDED_ID;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;

    private final AccountService accountService;


    public UserDataServiceImpl(UserDataRepository userDataRepository, AccountService accountService) {
        this.userDataRepository = userDataRepository;
        this.accountService = accountService;
    }

    @Override
    public List<UserData> findAll() {
        List<UserData> userDataList = new ArrayList<>();
        userDataRepository.findAll().forEach(userDataList::add);
        return userDataList;
    }

    @Override
    public UserData findById(Integer id) {
        return userDataRepository.findById(id).orElseThrow(() -> new ServiceException(CANNOT_FIND_USER_DATA_WITH_PROVIDED_ID));
    }

    @Override
    @Transactional
    public UserData save(UserData object) {
        return userDataRepository.save(object);
    }

    @Override
    public void delete(UserData object) {
        userDataRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        userDataRepository.deleteById(id);
    }


    @Override
    @Transactional
    public UserData findByUsername(String username) {
        return userDataRepository.findById(
                accountService.findByUsername(username).getUserData().getId()).orElseThrow(() -> new ServiceException(CANNOT_FIND_USER_DATA_WITH_PROVIDED_ID));
    }

    @Override
    public List<UserData> findAllByFavouriteEventCategoriesContaining(EventCategory eventCategory) {
        return this.userDataRepository.findAllByFavouriteEventCategoriesContaining(eventCategory);
    }
}