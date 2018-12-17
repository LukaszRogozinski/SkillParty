package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.WebPushSubscription;
import com.engineer.lrogozinski.dto.WebPushSubscriptionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WebPushSubscriptionDtoToWebPushSubscription implements Converter<WebPushSubscriptionDto, WebPushSubscription> {
    @Override
    public WebPushSubscription convert(WebPushSubscriptionDto webPushSubscriptionDto) {
        WebPushSubscription webPushSubscription = new WebPushSubscription();
        webPushSubscription.setEndpoint(webPushSubscriptionDto.getEndpoint());
        webPushSubscription.setExpirationTime(webPushSubscriptionDto.getExpirationTime());
        webPushSubscription.setKeys(webPushSubscriptionDto.getKeys());
    /*    webPushSubscription.setP256dh(webPushSubscriptionDto.getP256dh());
        webPushSubscription.setAuth(webPushSubscriptionDto.getAuth());*/
        return webPushSubscription;
    }
}
