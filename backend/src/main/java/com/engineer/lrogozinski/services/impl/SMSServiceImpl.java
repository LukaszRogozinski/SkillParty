package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.SMSService;
import com.engineer.lrogozinski.services.UserDataService;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SMSServiceImpl implements SMSService {


    @Value("${sms.test.account_sid}")
    private String ACCOUNT_SID;

    @Value("${sms.test.auth_token}")
    private String AUTH_TOKEN;

    private final UserDataService userDataService;

    private final EventCategoryService eventCategoryService;

    public SMSServiceImpl(UserDataService userDataService, EventCategoryService eventCategoryService) {
        this.userDataService = userDataService;
        this.eventCategoryService = eventCategoryService;
    }

    @Override
    public void sendMessage(String eventCategoryName) {
        EventCategory eventCategory = this.eventCategoryService.findByName(eventCategoryName);
        List<UserData> userDataList = this.userDataService.findAllByFavouriteEventCategoriesContaining(eventCategory);
        int z = 5;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        for (UserData user:
             userDataList) {
            Message twilio_message = Message.creator(
                    new PhoneNumber(user.getPhoneNo()),
                    new PhoneNumber("+15005550006"),
                    "New " + eventCategoryName.toLowerCase() + " event has been added!")
                    .create();
            //log.info(twilio_message.getSid());
            System.out.println(twilio_message.getSid());

            ListenableFuture<ResourceSet<Message>> future = Message.reader().readAsync();
            Futures.addCallback(
                    future,
                    new FutureCallback<ResourceSet<Message>>() {
                        public void onSuccess(ResourceSet<Message> messages) {
                            for (Message message : messages) {
                                //log.info(message.getSid() + " : " + message.getStatus());
                                System.out.println(message.getSid() + " : " + message.getStatus());
                            }
                        }
                        public void onFailure(Throwable t) {
                            //log.error("Failed to get message status: " + t.getMessage());
                            System.out.println("Failed to get message status: " + t.getMessage());
                        }
                    });
        }
    }
}
