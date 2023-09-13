package com.ClockedIn.shiftservice.services;

import com.ClockedIn.Course;
import com.ClockedIn.shiftservice.Shift;
import com.ClockedIn.shiftservice.repositories.ShiftRepository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShiftCollectionService implements ShiftRepository<Shift, Long> {
    private final Map<Long, Shift> shiftHashMap;

    public ShiftCollectionService(Map<Long, Shift> shiftHashMap) {
        this.shiftHashMap = shiftHashMap;
    }

    @Override
    public Shift save(Shift shift) {
        shiftHashMap.put(shift.getShiftId(), shift);
        return shift;
    }

    @Override
    public Optional<Shift> findById(Long shiftId) {
        return Optional.of(shiftHashMap.get(shiftId));
    }

    @Override
    public Iterable<Shift> findAll() {
        return shiftHashMap.values();
    }

    @Override
    public Iterable<Shift> findShiftsByCourse(Course course) {
        return shiftHashMap.values().stream()
                .filter(shift -> course.equals(shift.getCourseEnum()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long shiftId) {
        shiftHashMap.remove(shiftId);
    }

}
