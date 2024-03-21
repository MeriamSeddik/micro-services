package com.bnp.cardiff.notification.Service;

import com.bnp.cardiff.notification.events.ClaimRequestEvent;
import com.bnp.cardiff.notification.models.Event;
import com.bnp.cardiff.notification.repository.ClaimRequestEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClaimRequestEventService {
    private final ClaimRequestEventRepository claimRequestEventRepository;


    public void createEvent(ClaimRequestEvent claimRequestEvent){
        Event event = Event.builder()
                .message(claimRequestEvent.getMessage())
                .uri(claimRequestEvent.getUri())
                .timeStamp(claimRequestEvent.getTimeStamp())
                .data(claimRequestEvent.getData())
                .build();
        claimRequestEventRepository.save(event);
        log.info("Event {} is saved ",claimRequestEvent.getMessage());

    }
}
