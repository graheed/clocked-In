package com.clockedIn.userService.patterns.commands;

import com.clockedIn.userService.AbstractRequest;

public class DenyRequestCommand implements RequestCommand {
    @Override
    public AbstractRequest execute(AbstractRequest request) {
        request.deny();
        return request;
    }
}
