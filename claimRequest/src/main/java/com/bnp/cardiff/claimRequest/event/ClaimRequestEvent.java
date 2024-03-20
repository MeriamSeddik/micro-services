package com.bnp.cardiff.claimRequest.event;

import lombok.*;
import org.springframework.http.HttpStatus;

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
