package com.clockedIn.userService;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class LabManager extends User {
    private String temp;

    public void sayHello(){
        System.out.println("HELLO FROM LAB MANAGER ");
    }
}
