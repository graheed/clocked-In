package com.clockedIn.notificationService;

import com.clockedIn.userService.AbstractRequest;
import com.clockedIn.userService.observers.Observer;

public interface NotificationService {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void send(AbstractRequest request);
}
