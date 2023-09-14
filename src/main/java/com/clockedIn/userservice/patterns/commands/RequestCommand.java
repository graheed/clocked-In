package com.clockedIn.userservice.patterns.commands;

import com.clockedIn.userservice.AbstractRequest;

public interface RequestCommand {
    AbstractRequest execute(AbstractRequest request);
}
