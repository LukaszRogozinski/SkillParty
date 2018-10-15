package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.UsedToken;
import com.engineer.lrogozinski.repositories.UsedTokenRepository;
import com.engineer.lrogozinski.services.UsedTokenService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsedTokenServiceImpl implements UsedTokenService {

   private final UsedTokenRepository usedTokenRepository;

    public UsedTokenServiceImpl(UsedTokenRepository usedTokenRepository) {
        this.usedTokenRepository = usedTokenRepository;
    }

    @Override
    public List<UsedToken> findAll() {
        List<UsedToken> usedTokens = new ArrayList<>();
        usedTokenRepository.findAll().forEach(usedTokens::add);
        return usedTokens;
    }

    @Override
    public UsedToken findById(Integer id) {
        return usedTokenRepository.findById(id).orElse(null);
    }

    @Override
    public UsedToken save(UsedToken object) {
        return usedTokenRepository.save(object);
    }

    @Override
    public void delete(UsedToken object) {
        usedTokenRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        usedTokenRepository.deleteById(id);
    }
}
