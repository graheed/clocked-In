package com.clockedIn.notificationService;

import com.clockedIn.userService.AbstractRequest;
import com.clockedIn.userService.patterns.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationService implements NotificationService {
    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }



    @Override
    public void send(AbstractRequest request) {
        for (Observer observer : observers) {
            observer.updateRequestList(request);
        }
    }
    public void clearObservers() {
        observers.clear();
    }
}
