package com.clockedIn.userservice;

public interface Request {
    void submit();
    void deny();

    void approve();
    RequestStatus getStatus();

}
