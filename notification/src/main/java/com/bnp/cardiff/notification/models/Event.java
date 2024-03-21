package com.bnp.cardiff.notification.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(value = "event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    private String id;
    protected LocalDateTime timeStamp;
    private String uri;
    protected String message;
    protected Map<?,?> data;
}
