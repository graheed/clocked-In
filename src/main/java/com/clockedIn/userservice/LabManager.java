package com.clockedIn.userservice;

import com.clockedIn.userservice.patterns.observers.Observer;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@ToString(callSuper = true)
public class LabManager extends User implements Observer {
    private String test;



}
