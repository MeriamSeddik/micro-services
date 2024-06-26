package com.bnp.cardiff.notification.events;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClaimRequestEvent {

    protected LocalDateTime timeStamp;
    private String uri;
    protected String message;
    protected Map<?,?> data;
}
