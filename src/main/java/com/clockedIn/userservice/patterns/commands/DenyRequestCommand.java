package com.clockedIn.userservice.patterns.commands;

import com.clockedIn.userservice.AbstractRequest;

public class DenyRequestCommand implements RequestCommand {
    @Override
    public AbstractRequest execute(AbstractRequest request) {
        request.deny();
        return request;
    }
}
