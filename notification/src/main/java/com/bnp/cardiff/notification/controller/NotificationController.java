package com.bnp.cardiff.notification.controller;

import com.bnp.cardiff.notification.Service.ClaimRequestEventService;
import com.bnp.cardiff.notification.models.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final ClaimRequestEventService claimRequestEventService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEvents(){
        return claimRequestEventService.getAllEvents();
    }
}
