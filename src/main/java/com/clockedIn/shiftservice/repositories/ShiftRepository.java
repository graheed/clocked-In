package com.clockedIn.shiftservice.repositories;

import com.clockedIn.shiftservice.Course;

import java.util.Optional;

public interface ShiftRepository<T, R> {


    T save(T entity);

    Optional<T> findById(R id);

    Iterable<T> findAll();

    Iterable<T> findShiftsByCourse(Course course);

    void delete(R id);

}
