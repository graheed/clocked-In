package com.clockedIn.userService;

public interface Request {
    void submit();
    void deny();

    void approve();
    RequestStatus getStatus();

}
