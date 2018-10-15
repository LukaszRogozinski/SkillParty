package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.repositories.UserDataRepository;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;

    private AccountService accountService;

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
        return userDataRepository.findById(id).orElse(null);
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
                accountService.findByUsername(username).getUserData().getId()).orElse(null);
    }
}
