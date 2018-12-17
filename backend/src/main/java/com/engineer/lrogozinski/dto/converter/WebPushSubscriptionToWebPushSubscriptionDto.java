package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.WebPushSubscription;
import com.engineer.lrogozinski.dto.WebPushSubscriptionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WebPushSubscriptionToWebPushSubscriptionDto implements Converter<WebPushSubscription, WebPushSubscriptionDto> {
    @Override
    public WebPushSubscriptionDto convert(WebPushSubscription webPushSubscription) {

        return WebPushSubscriptionDto.builder()
                .endpoint(webPushSubscription.getEndpoint())
                .expirationTime(webPushSubscription.getExpirationTime())
                .keys(webPushSubscription.getKeys())
              /*  .p256dh(webPushSubscription.getP256dh())
                .auth(webPushSubscription.getAuth())*/
                .build();
        }
}
