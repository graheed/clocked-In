package com.ClockedIn.requestservice;
import com.ClockedIn.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeOffRequest implements Request {
    private User requestor;
    private String reason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private RequestStatus requestStatus;
    private User requestApprover;
    private LocalDateTime timeCreated;
    private LocalDateTime timeResolved;

    @Override
    public void submit() {

    }

    @Override
    public void approve() {

    }

    @Override
    public void decline() {

    }

    @Override
    public void getStatus() {

    }
}
