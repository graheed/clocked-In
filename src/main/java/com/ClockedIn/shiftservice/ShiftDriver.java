package com.ClockedIn.shiftservice;

import com.ClockedIn.Course;
import com.ClockedIn.shiftservice.services.ShiftCollectionService;
import java.util.HashMap;
import java.util.Map;

public class ShiftDriver {
    public static void main(String[] args) {
        Map<Long, Shift> shiftHashMap = new HashMap<>();
        ShiftCollectionService shiftCollectionService = new ShiftCollectionService(shiftHashMap);

        //Creating shifts to be used
        Shift shift1 = Shift.builder().shiftId(1L).courseEnum(Course.COMP1126).build();
        Shift shift2 = Shift.builder().shiftId(2L).courseEnum(Course.COMP1126).build();
        Shift shiftUpdate = Shift.builder().shiftId(2L).courseEnum(Course.COMP3161).build();

        //Save two shifts
        Shift firstSavedShift = shiftCollectionService.save(shift1);
        Shift secondSavedShift = shiftCollectionService.save(shift2);

        //Find Shift By ID
        Shift shiftFoundById = shiftCollectionService.findById(1L).get();

        //Find Shift By Course
        Iterable<Shift> shiftsFoundByCourse = shiftCollectionService.findShiftsByCourse(Course.COMP1126);

        //Find All Courses
        Iterable<Shift> allShifts = shiftCollectionService.findAll();

        //Output the Results
        System.out.println("First shift that was saved: " + firstSavedShift.toString());
        System.out.println("Second shift that was saved: " + secondSavedShift.toString());
        System.out.println("Shift with ID number 1: " + shiftFoundById);
        System.out.println("Shifts for the Course COMP1126: " + shiftsFoundByCourse.toString());
        System.out.println("All shifts: " + allShifts);

        //Update a Shift
        Shift updatedShift = shiftCollectionService.save(shiftUpdate);

        //Delete a Shift
        shiftCollectionService.delete(1L);

        //Output Results
        System.out.println("All shifts after deleting Shift with ID 1: " + allShifts);
        System.out.println("Updated Shift: " + updatedShift.toString());

        //Find All Shifts With New Update to Shift
        Iterable<Shift> allShiftsWithNewUpdate = shiftCollectionService.findAll();
        System.out.println("All shifts with newly updated shift: " + allShiftsWithNewUpdate);

    }

}
