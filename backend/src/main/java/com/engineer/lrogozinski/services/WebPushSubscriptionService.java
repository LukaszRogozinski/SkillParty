package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.WebPushSubscription;
import com.engineer.lrogozinski.dto.WebPushSubscriptionDto;

import java.util.List;

public interface WebPushSubscriptionService {

    List<WebPushSubscriptionDto> findAll();

    WebPushSubscriptionDto findById(Integer id);

    WebPushSubscription save(WebPushSubscriptionDto object);

    void delete(WebPushSubscriptionDto object);

    void deleteById(Integer id);
}
