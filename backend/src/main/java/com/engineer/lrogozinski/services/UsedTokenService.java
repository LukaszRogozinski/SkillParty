package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.UsedToken;

import java.util.List;

public interface UsedTokenService {

    List<UsedToken> findAll();

    UsedToken findById(Integer id);

    UsedToken save(UsedToken object);

    void delete(UsedToken object);

    void deleteById(Integer id);
}
