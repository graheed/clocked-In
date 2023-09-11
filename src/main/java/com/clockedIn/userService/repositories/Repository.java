package com.clockedIn.userService.repositories;

import com.clockedIn.userService.User;

import java.util.*;
import java.util.function.Predicate;

//BaseRepository
public class Repository <T extends User> {
    private Map<UUID, T> UserById = new HashMap<>();
    private Map<Long, T> UserByUniversityId = new HashMap<>();

    public T save(T entity) {
        UserById.putIfAbsent(entity.getUserID(), entity);
        UserByUniversityId.putIfAbsent(entity.getUniversityID(), entity);
        return entity;
    }


    public T update(T entity) {
        T user = UserById.put(entity.getUserID(), entity);
        UserByUniversityId.put(entity.getUniversityID(), entity);
        return user == null ? entity : user;

    }


    public Optional<T> findById(UUID id) {
        return Optional.of(UserById.get(id));

    }


    public Optional<T> findByUniversityId(Long id) {
        return Optional.of(UserByUniversityId.get(id));

    }


    public Iterable<T> findAll() {
        return UserById.values();
    }


    public Iterable<T> findAllByFilter(Predicate<T> filter) {
        return UserById.values().stream()
                                .filter(filter)
                                .toList();
    }


    public void deleteById(UUID id) {
        UserById.remove(id);
    }


    public void deleteByUniversityId(Long id) {
        UserByUniversityId.remove(id);
    }
}
