package com.clockedIn.userService.patterns.observers;

import com.clockedIn.userService.AbstractRequest;

public interface Observer {
    void updateRequestList(AbstractRequest request);
}
