package com.clockedIn.userService.patterns.commands;

import com.clockedIn.userService.AbstractRequest;

public interface RequestCommand {
    AbstractRequest execute(AbstractRequest request);
}
