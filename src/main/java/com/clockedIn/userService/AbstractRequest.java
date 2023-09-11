package com.clockedIn.userService;

import com.clockedIn.userService.observers.Observer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractRequest implements Request{
    private Observer requester;
    private String reason;
    private List<Observer> requestApprover;
    private RequestStatus requestStatus;
    private LocalDateTime timeCreated;
    private LocalDateTime timeResolved;

}
