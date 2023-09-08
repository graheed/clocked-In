package com.ClockedIn.shiftservice.repositories;

import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T, ID> {
    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllByFilter(Predicate<T> filter);

    void delete(ID id);

}
