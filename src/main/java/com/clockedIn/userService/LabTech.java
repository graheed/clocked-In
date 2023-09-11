package com.clockedIn.userService;

import com.clockedIn.notificationService.EmailNotificationService;
import com.clockedIn.userService.observers.Observer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@ToString(callSuper = true)
public class LabTech extends User implements Observer {
    private Map<RequestStatus, List<AbstractRequest>> myRequests;
    private Map<RequestStatus, List<AbstractRequest>> otherRequests;
    private EmailNotificationService notificationService;


    @Override
    public void updateRequests(AbstractRequest request) {
        if (request.getStatus() == RequestStatus.PENDING) {
            otherRequests.get(RequestStatus.PENDING).add(request);
        } else {
            myRequests.get(request.getStatus()).add(request);
        }
    }

    public void approveRequest(AbstractRequest request) {
        request.approve();
        otherRequests.get(request.getStatus()).add(request);
        notificationService.addObserver(request.getRequester());
        notificationService.send(request);
        notificationService.clearObservers();
    }
    public void denyRequest(AbstractRequest request) {
        request.deny();
        otherRequests.get(request.getStatus()).add(request);
        notificationService.addObserver(request.getRequester());
        notificationService.send(request);
        notificationService.clearObservers();
    }
    public AbstractRequest makeShiftChangeRequest() {
        AbstractRequest request = ShiftSwapRequest.builder().build();
        request.submit();
        myRequests.get(request.getStatus()).add(request);
        return request;
    }

    public AbstractRequest makeTimeOffRequest() {
        AbstractRequest request = TimeOffRequest.builder().build();
        request.submit();
        myRequests.get(request.getStatus()).add(request);
        return request;
    }
}
