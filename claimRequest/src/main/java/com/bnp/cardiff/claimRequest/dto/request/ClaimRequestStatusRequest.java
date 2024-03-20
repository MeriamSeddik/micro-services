package com.bnp.cardiff.claimRequest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimRequestStatusRequest {
    private Timestamp statusAt;
    private String status;
    private String reason;
    private String step;
}
