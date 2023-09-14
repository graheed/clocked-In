package com.clockedIn.userservice;

import com.clockedIn.notificationservice.EmailNotificationService;
import com.clockedIn.userservice.patterns.commands.RequestCommand;
import com.clockedIn.userservice.patterns.observers.Observer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User implements Observer {
    protected UUID userID = UUID.randomUUID();
    protected long universityID;
    protected String firstName;
    protected String lastName;
    protected UserRole userRole;
    protected String phoneNum;
    protected String email;
    protected Map<RequestStatus, HashMap<UUID, AbstractRequest>> otherRequests;
    protected RequestCommand approveCommand;
    protected RequestCommand denyCommand;
    protected EmailNotificationService notificationService;

    public void processRequest(RequestCommand command, AbstractRequest request) {
        RequestStatus prevRequestStatus = request.getRequestStatus();
        if (prevRequestStatus != RequestStatus.PENDING) {
            return;
        }
        command.execute(request);
        otherRequests.get(prevRequestStatus).remove(request.getRequestID());
        otherRequests.get(request.getStatus()).put(request.getRequestID(), request);
        notificationService.addObserver(request.getRequester());
        notificationService.send(request);
        notificationService.clearObservers();

    }
    public void approveRequest(AbstractRequest request) {
        processRequest(approveCommand, request);

    }
    public void denyRequest(AbstractRequest request) {
        processRequest(denyCommand, request);
    }

    @Override
    public void updateRequestList(AbstractRequest request) {
        if (request.getStatus() == RequestStatus.PENDING) {
            otherRequests.get(RequestStatus.PENDING).put(request.getRequestID(), request);
        }

    }
}
