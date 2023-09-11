package com.clockedIn.userService;

public class Main {
    public static void main(String[] args) {
        /*Repository<User> userRepository = new UserRepository();
        Repository<LabTech> labTechRepository = new LabTechRepository();
        Faker faker = new Faker();

        userRepository.save(User.builder()
                        .userID(UUID.randomUUID())
                .email("mrStephenson@gmail.com")
                .build());
        userRepository.save(User.builder()
                .userID(UUID.randomUUID())
                .email("mrJakarta@gmail.com")
                .build());
        labTechRepository.save(LabTech.builder()
                .userID(UUID.randomUUID())
                .email("mrRoberts@gmail.com")
                .build());
        labTechRepository.save(LabTech.builder()
                .userID(UUID.randomUUID())
                .email("mrKurri@gmail.com")
                .build());

        List<Repository<? extends User>> repositories = new ArrayList<>();
        repositories.add(userRepository);
        repositories.add(labTechRepository);
        Iterable<? extends User> users = repositories.stream()
                .flatMap(repo -> StreamSupport.stream(repo.findAllByFilter(
                        user -> user.getEmail().equals("mrStephenson@gmail.com")).spliterator(), false)).toList();
//        users.forEach(System.out::println);

        EmailNotificationService testNotificationService = new EmailNotificationService();
        LabTech labTech1 = LabTech.builder()
                .userID(UUID.randomUUID())
                .email(faker.internet().emailAddress())
                .waitingRequests(new ArrayList<>())
                .build();
        LabTech labTech2 = LabTech.builder()
                .userID(UUID.randomUUID())
                .email(faker.internet().emailAddress())
                .waitingRequests(new ArrayList<>())
                .build();
        LabTech labTech3 = LabTech.builder()
                .userID(UUID.randomUUID())
                .waitingRequests(new ArrayList<>())
                .email(faker.internet().emailAddress())
                .build();
        testNotificationService.addObserver(labTech1);
        testNotificationService.addObserver(labTech2);
        testNotificationService.addObserver(labTech3);

        testNotificationService.send(Request.builder()
                        .reason("Just a simple test")
                        .build());

        System.out.println(labTech1);
        System.out.println(labTech2);
        System.out.println(labTech3);*/



    }
}
