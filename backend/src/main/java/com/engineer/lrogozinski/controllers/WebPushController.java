package com.engineer.lrogozinski.controllers;


import com.engineer.lrogozinski.dto.WebPushSubscriptionDto;
import com.engineer.lrogozinski.services.WebPushSubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/web-push")
public class WebPushController {

    private final WebPushSubscriptionService webPushSubscriptionService;

    public WebPushController(WebPushSubscriptionService webPushSubscriptionService) {
        this.webPushSubscriptionService = webPushSubscriptionService;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public void saveWebPushSubscription(@RequestBody WebPushSubscriptionDto webPushSubscriptionDto) {
        webPushSubscriptionService.save(webPushSubscriptionDto);
    }

    @RequestMapping(value = "/getAllWebSubscriptions", method = RequestMethod.GET)
    public List<WebPushSubscriptionDto> getAllWebPushSubscriptions() {
        return webPushSubscriptionService.findAll();
    }
}
