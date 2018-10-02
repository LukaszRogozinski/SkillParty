package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.repositories.UserDataRepository;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;

    public UserDataServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
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
}
