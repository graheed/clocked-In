package com.clockedIn.userService;


import com.clockedIn.notificationService.EmailNotificationService;
import com.clockedIn.userService.patterns.commands.ApproveRequestCommand;
import com.clockedIn.userService.patterns.commands.DenyRequestCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class LabTechTest {

    LabTech testLabTech;
    EmailNotificationService emailNotificationService = mock(EmailNotificationService.class);
    AbstractRequest shiftSwapApproved;
    AbstractRequest shiftSwapDenied;
    AbstractRequest shiftSwapPending;
    @BeforeEach
    void setUp() {
        shiftSwapApproved = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .build();

        shiftSwapDenied = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .build();;
        shiftSwapPending = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .build();;
        HashMap<UUID, AbstractRequest> myPendingMap = new HashMap<>();
        HashMap<UUID, AbstractRequest> myApprovedMap = new HashMap<>();
        HashMap<UUID, AbstractRequest> myDeniedMap = new HashMap<>();

        HashMap<UUID, AbstractRequest> otherPendingMap = new HashMap<>();
        HashMap<UUID, AbstractRequest> otherApprovedMap = new HashMap<>();
        HashMap<UUID, AbstractRequest> otherDeniedMap = new HashMap<>();



        shiftSwapApproved.approve();
        shiftSwapDenied.deny();
        shiftSwapPending.submit();

        HashMap<RequestStatus, HashMap<UUID, AbstractRequest>> otherRequests = new HashMap<>();
        HashMap<RequestStatus, HashMap<UUID, AbstractRequest>> myRequests = new HashMap<>();

        otherRequests.put(RequestStatus.PENDING, otherPendingMap);
        otherRequests.put(RequestStatus.APPROVED, otherApprovedMap);
        otherRequests.put(RequestStatus.DENIED, otherDeniedMap);

        myRequests.put(RequestStatus.PENDING, myPendingMap);
        myRequests.put(RequestStatus.APPROVED,myApprovedMap);
        myRequests.put(RequestStatus.DENIED, myDeniedMap);


        testLabTech = LabTech.builder()
                .userID(UUID.randomUUID())
                .otherRequests(otherRequests)
                .myRequests(myRequests)
                .notificationService(emailNotificationService)
                .denyCommand(new DenyRequestCommand())
                .approveCommand(new ApproveRequestCommand())
                .build();
    }
    @Test
    void updateRequestList() {
        //given

        //when
        testLabTech
                .getMyRequests()
                .get(RequestStatus.PENDING)
                .put(shiftSwapApproved.getRequestID(), shiftSwapApproved);
        testLabTech
                .getMyRequests()
                .get(RequestStatus.PENDING)
                .put(shiftSwapDenied.getRequestID(), shiftSwapDenied);

        testLabTech.updateRequestList(shiftSwapApproved);
        testLabTech.updateRequestList(shiftSwapDenied);
        testLabTech.updateRequestList(shiftSwapPending);


        //then
        assertEquals(testLabTech
                .getMyRequests()
                .get(RequestStatus.DENIED)
                .get(shiftSwapDenied.getRequestID()), shiftSwapDenied);

        assertEquals(testLabTech
                .getMyRequests()
                .get(RequestStatus.APPROVED)
                .get(shiftSwapApproved.getRequestID()), shiftSwapApproved);

        assertEquals(testLabTech
                .getOtherRequests()
                .get(RequestStatus.PENDING)
                .get(shiftSwapPending.getRequestID()), shiftSwapPending);


        assertEquals(testLabTech
                .getMyRequests()
                .get(RequestStatus.PENDING), new HashMap<UUID, AbstractRequest>());

    }

    @Test
    void makeShiftChangeRequest() {

        //given
        AbstractRequest labTechRequest;

        //when
        labTechRequest = testLabTech.makeShiftChangeRequest();
        doNothing().when(emailNotificationService).addObserver(labTechRequest.getRequester());
        doNothing().when(emailNotificationService).send(labTechRequest);
        doNothing().when(emailNotificationService).clearObservers();


        //then
        assertEquals(testLabTech
                .getMyRequests()
                .get(RequestStatus.PENDING)
                .get(labTechRequest.getRequestID()), labTechRequest);
    }

    @Test
    void makeTimeOffRequest() {
        //given
        AbstractRequest labTechRequest;

        //when
        labTechRequest = testLabTech.makeTimeOffRequest();
        doNothing().when(emailNotificationService).addObserver(labTechRequest.getRequester());
        doNothing().when(emailNotificationService).send(labTechRequest);
        doNothing().when(emailNotificationService).clearObservers();


        //then
        assertEquals(testLabTech
                .getMyRequests()
                .get(RequestStatus.PENDING)
                .get(labTechRequest.getRequestID()), labTechRequest);
    }

}