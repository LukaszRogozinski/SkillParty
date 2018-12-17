package com.engineer.lrogozinski.dto;

import com.engineer.lrogozinski.domain.Keys;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class WebPushSubscriptionDto {

    private String endpoint;
    private Date expirationTime;
    private Keys keys;
}
