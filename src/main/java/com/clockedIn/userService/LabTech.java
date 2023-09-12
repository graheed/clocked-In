package com.clockedIn.userService;

import com.clockedIn.userService.patterns.observers.Observer;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.*;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@ToString(callSuper = true)
public class LabTech extends User implements Observer {
    private Map<RequestStatus, HashMap<UUID, AbstractRequest>> myRequests;



    @Override
    public void updateRequestList(AbstractRequest request) {
        if (request.getStatus() == RequestStatus.PENDING) {
            otherRequests.get(RequestStatus.PENDING).put(request.getRequestID(), request);
        } else {
            myRequests.get(RequestStatus.PENDING).remove(request.getRequestID());
            myRequests.get(request.getStatus()).put(request.getRequestID(), request);
        }
    }

    public AbstractRequest makeShiftChangeRequest() {
        Observer testUser = User.builder().userID(UUID.randomUUID()).build();
        List<Observer> approvers = new ArrayList<>();
        approvers.add(testUser);
        AbstractRequest request = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .requestApprover(approvers)
                .build();
        return sendAbstractRequest(request);
    }

    public AbstractRequest makeTimeOffRequest() {
        Observer testUser = User.builder().userID(UUID.randomUUID()).build();
        List<Observer> approvers = new ArrayList<>();
        approvers.add(testUser);
        AbstractRequest request = TimeOffRequest.builder()
                .requestID(UUID.randomUUID())
                .requestApprover(approvers)
                .build();
        return sendAbstractRequest(request);
    }

    @NotNull
    private AbstractRequest sendAbstractRequest(AbstractRequest request) {
        request.submit();
        for(Observer observer : request.getRequestApprover()) {
            notificationService.addObserver(observer);
        }
        notificationService.send(request);
        notificationService.clearObservers();
        myRequests.get(request.getStatus()).put(request.getRequestID(), request);
        return request;
    }
}
