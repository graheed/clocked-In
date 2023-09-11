package com.clockedIn.userService.observers;

import com.clockedIn.userService.AbstractRequest;

public interface Observer {
    void updateRequests(AbstractRequest request);
}
