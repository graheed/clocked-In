package com.clockedIn.userservice.patterns.observers;

import com.clockedIn.userservice.AbstractRequest;

public interface Observer {
    void updateRequestList(AbstractRequest request);
}
