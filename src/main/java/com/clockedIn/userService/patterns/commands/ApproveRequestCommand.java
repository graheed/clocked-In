package com.clockedIn.userService.patterns.commands;

import com.clockedIn.userService.AbstractRequest;

public class ApproveRequestCommand implements RequestCommand {
    @Override
    public AbstractRequest execute(AbstractRequest request) {
        request.approve();
        return request;
    }
}
