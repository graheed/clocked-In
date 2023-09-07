package com.ClockedIn.shiftservice;

public interface Request {
    void submit();
    void approve();
    void decline();
    void getStatus();
}
