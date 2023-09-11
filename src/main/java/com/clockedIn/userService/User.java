package com.clockedIn.userService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class User {
    private UUID userID = UUID.randomUUID();
    private long universityID;
    private String firstName;
    private String lastName;
    private UserRole userRole;
    private String phoneNum;
    private String email;

    public void sayHello(){
        System.out.println("HELLO FROM USER");
    }
}
