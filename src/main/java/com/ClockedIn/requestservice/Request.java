package com.ClockedIn.requestservice;

public interface Request {
    void submit();
    void approve();
    void decline();
    void getStatus();
}
