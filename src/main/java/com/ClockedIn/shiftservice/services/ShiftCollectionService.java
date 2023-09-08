package com.ClockedIn.shiftservice.services;

import com.ClockedIn.shiftservice.Shift;
import com.ClockedIn.shiftservice.repositories.ShiftRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShiftCollectionService implements ShiftRepository {
    private List<Shift> shiftList;

    public ShiftCollectionService(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @Override
    public <S extends Shift> S save(S shift) {
        shiftList.add(shift);
        return shift;
    }

    @Override
    public <S extends Shift> S update(S shift) {
        shiftList = shiftList.stream()
                .map(shift1 -> {
                    if (shift1.getShiftId() == shift.getShiftId()) {
                        return shift;
                    }
                    return shift;
                })
                .collect(Collectors.toList());
        return shift;
    }

    @Override
    public Optional<Shift> findById(Integer shiftId) {
        return shiftList.stream()
                .filter(shift -> shift.getShiftId() == shiftId)
                .reduce(((shift, shift2) -> {
                    throw new IllegalStateException("Duplicate Id values Found");
                }));
    }

    @Override
    public Iterable<Shift> findAll() {
        return shiftList;
    }

    @Override
    public Iterable<Shift> findAllByFilter(Predicate<Shift> filter) {
        return shiftList.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
    @Override
    public void delete(Integer shiftId) {
        shiftList = shiftList.stream()
                .filter(shift -> shift.getShiftId() != shiftId)
                .collect(Collectors.toList());
    }

}
