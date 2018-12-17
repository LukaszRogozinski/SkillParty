package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.WebPushSubscription;
import com.engineer.lrogozinski.dto.WebPushSubscriptionDto;
import com.engineer.lrogozinski.dto.converter.WebPushSubscriptionDtoToWebPushSubscription;
import com.engineer.lrogozinski.dto.converter.WebPushSubscriptionToWebPushSubscriptionDto;
import com.engineer.lrogozinski.repositories.WebPushSubscriptionRepository;
import com.engineer.lrogozinski.services.WebPushSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebPushSubscriptionServiceImpl implements WebPushSubscriptionService {

    private final WebPushSubscriptionRepository webPushSubscriptionRepository;

    private final WebPushSubscriptionToWebPushSubscriptionDto webPushSubscriptionToWebPushSubscriptionDto;

    private final WebPushSubscriptionDtoToWebPushSubscription webPushSubscriptionDtoToWebPushSubscription;

    public WebPushSubscriptionServiceImpl(WebPushSubscriptionRepository webPushSubscriptionRepository, WebPushSubscriptionToWebPushSubscriptionDto webPushSubscriptionToWebPushSubscriptionDto, WebPushSubscriptionDtoToWebPushSubscription webPushSubscriptionDtoToWebPushSubscription) {
        this.webPushSubscriptionRepository = webPushSubscriptionRepository;
        this.webPushSubscriptionToWebPushSubscriptionDto = webPushSubscriptionToWebPushSubscriptionDto;
        this.webPushSubscriptionDtoToWebPushSubscription = webPushSubscriptionDtoToWebPushSubscription;
    }

    @Override
    public List<WebPushSubscriptionDto> findAll() {
        List<WebPushSubscriptionDto> webPushSubscriptionDtoList = new ArrayList<>();
        webPushSubscriptionRepository.findAll().forEach(webPushSubscription -> {
            webPushSubscriptionDtoList.add(webPushSubscriptionToWebPushSubscriptionDto.convert(webPushSubscription));
    });
        return webPushSubscriptionDtoList;
    }

    @Override
    public WebPushSubscriptionDto findById(Integer id) {
        return webPushSubscriptionToWebPushSubscriptionDto.convert(webPushSubscriptionRepository.findById(id).orElse(null));
    }

    @Override
    public WebPushSubscription save(WebPushSubscriptionDto object) {
        return webPushSubscriptionRepository.save(webPushSubscriptionDtoToWebPushSubscription.convert(object));
    }

    @Override
    public void delete(WebPushSubscriptionDto object) {
        webPushSubscriptionRepository.delete(webPushSubscriptionDtoToWebPushSubscription.convert(object));
    }

    @Override
    public void deleteById(Integer id) {
        webPushSubscriptionRepository.deleteById(id);
    }
}
