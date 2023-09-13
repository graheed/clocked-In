package com.clockedIn.userService;

import com.clockedIn.userService.repositories.LabManagerRepository;
import com.clockedIn.userService.repositories.LabTechRepository;
import com.clockedIn.userService.repositories.UserRepository;
import com.github.javafaker.Faker;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        UserRepository userRepository = new UserRepository();
        LabTechRepository labTechRepository = new LabTechRepository();
        LabManagerRepository labManagerRepository = new LabManagerRepository();

        User newUser = User.builder()
                .userID(UUID.randomUUID())
                .universityID(620000987L)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNum(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .build();

        LabTech newLabTech = LabTech.builder()
                .userID(UUID.randomUUID())
                .universityID(620998555L)
                .userRole(UserRole.LABTECH)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNum(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .build();

        LabManager newLabManager = LabManager.builder()
                .userID(UUID.randomUUID())
                .universityID(620111969L)
                .userRole(UserRole.LABMANAGER)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNum(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .build();

        User updatedUser = User.builder()
                .userID(newUser.getUserID())
                .universityID(620000981L)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNum(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .build();
        LabTech updatedLabTech = LabTech.builder()
                .userID(newLabTech.getUserID())
                .universityID(620998559L)
                .userRole(UserRole.LABTECH)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNum(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .build();

        LabManager updatedLabManager = LabManager.builder()
                .userID(newLabManager.getUserID())
                .universityID(620111965L)
                .userRole(UserRole.LABMANAGER)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phoneNum(faker.phoneNumber().cellPhone())
                .email(faker.internet().emailAddress())
                .build();


        userRepository.save(newUser);
        labTechRepository.save(newLabTech);
        labManagerRepository.save(newLabManager);

        System.out.println(userRepository.findById(newUser.getUserID()));
        System.out.println(labTechRepository.findById(newLabTech.getUserID()));
        System.out.println(labManagerRepository.findById(newLabManager.getUserID()));
        System.out.println();
        System.out.println(userRepository.findByUniversityId(newUser.getUniversityID()));
        System.out.println(labTechRepository.findByUniversityId(newLabTech.getUniversityID()));
        System.out.println(labManagerRepository.findByUniversityId(newLabManager.getUniversityID()));
        System.out.println();
        userRepository.update(updatedUser);
        labTechRepository.update(updatedLabTech);
        labManagerRepository.update(updatedLabManager);
        System.out.println(userRepository.findById(newUser.getUserID()));
        System.out.println(labTechRepository.findById(newLabTech.getUserID()));
        System.out.println(labManagerRepository.findById(newLabManager.getUserID()));
        System.out.println();
        userRepository.deleteById(updatedUser.getUserID());
        labTechRepository.deleteById(updatedLabTech.getUserID());
        labManagerRepository.deleteById(updatedLabManager.getUserID());
        System.out.println(userRepository.findAll());
        System.out.println(labTechRepository.findAll());
        System.out.println(labManagerRepository.findAll());




    }
}
