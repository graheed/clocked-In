package com.clockedIn.userService;


import com.clockedIn.notificationService.EmailNotificationService;
import com.clockedIn.userService.patterns.commands.ApproveRequestCommand;
import com.clockedIn.userService.patterns.commands.DenyRequestCommand;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    User testUser;
    List<AbstractRequest> requestList;
    EmailNotificationService emailNotificationService = mock(EmailNotificationService.class);

    @BeforeEach
    void setUp() {
        AbstractRequest request1 = ShiftSwapRequest.builder()
                                                   .requestID(UUID.randomUUID())
                                                   .build();

        AbstractRequest request2 = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .build();

        HashMap<UUID, AbstractRequest> requestsMap = new HashMap<>();
        requestList = new ArrayList<>();

        request1.submit();
        request2.submit();

        requestList.add(request1);
        requestList.add(request2);

        requestsMap.put(request1.getRequestID(), request1);
        requestsMap.put(request2.getRequestID(), request2);

        HashMap<RequestStatus, HashMap<UUID, AbstractRequest>> otherRequests = new HashMap<>();
        otherRequests.put(RequestStatus.PENDING, requestsMap);
        otherRequests.put(RequestStatus.APPROVED, requestsMap);
        otherRequests.put(RequestStatus.DENIED, requestsMap);


        testUser = User.builder()
                         .userID(UUID.randomUUID())
                         .otherRequests(otherRequests)
                         .notificationService(emailNotificationService)
                         .denyCommand(new DenyRequestCommand())
                         .approveCommand(new ApproveRequestCommand())
                         .build();
    }

    @Test
    void approvePendingRequestsTest() {
        //given

        //when
        for (AbstractRequest request : requestList) {
            doNothing().when(emailNotificationService).addObserver(request.getRequester());
            doNothing().when(emailNotificationService).send(request);
            doNothing().when(emailNotificationService).clearObservers();

            testUser.approveRequest(request);
        }

        //then
        for (AbstractRequest request : requestList) {
            assertEquals(testUser
                    .getOtherRequests()
                    .get(RequestStatus.APPROVED)
                    .get(request.getRequestID()), request);
        }

    }

    @Test
    void denyPendingRequestsTest() {
        //given

        //when
        for (AbstractRequest request : requestList) {
            doNothing().when(emailNotificationService).addObserver(request.getRequester());
            doNothing().when(emailNotificationService).send(request);
            doNothing().when(emailNotificationService).clearObservers();

            testUser.denyRequest(request);
        }

        //then
        //then
        for (AbstractRequest request : requestList) {
            assertEquals(testUser
                    .getOtherRequests()
                    .get(RequestStatus.DENIED)
                    .get(request.getRequestID()), request);
        }
    }

    @Test
    void failApprovalOfBadRequest() {
        //given
        AbstractRequest request = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .build();
        request.deny();

        //when
        testUser.approveRequest(request);

        //then
        assertEquals(RequestStatus.DENIED, request.getRequestStatus());

    }

    @Test
    void failDenialOfBadRequest() {
        //given
        AbstractRequest request = ShiftSwapRequest.builder()
                .requestID(UUID.randomUUID())
                .build();
        request.approve();

        //when
        testUser.denyRequest(request);

        //then
        assertEquals(RequestStatus.APPROVED, request.getRequestStatus());

    }

    @Test
    void updateRequestList() {
        //given

        //when
        for (AbstractRequest request: requestList) {
            testUser.updateRequestList(request);
        }

        //then
        for (AbstractRequest request: requestList) {
            assertEquals(testUser.getOtherRequests().get(RequestStatus.PENDING).get(request.getRequestID()), request);
        }

    }
}