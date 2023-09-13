package com.ClockedIn.shiftservice.repositories;

import com.ClockedIn.Course;

import java.util.Optional;

public interface ShiftRepository<T, R> {


    T save(T entity);

    Optional<T> findById(R id);

    Iterable<T> findAll();

    Iterable<T> findShiftsByCourse(Course course);

    void delete(R id);

}
