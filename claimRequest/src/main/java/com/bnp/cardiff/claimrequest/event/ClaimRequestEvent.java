package com.bnp.cardiff.claimrequest.event;

import lombok.*;

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
