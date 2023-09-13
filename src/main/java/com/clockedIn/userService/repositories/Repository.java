package com.clockedIn.userService.repositories;

import com.clockedIn.userService.User;

import java.util.*;
import java.util.function.Predicate;

/**
 * BaseRepository class for User objects.
 * @param <T> a type that extends User
 */
public class Repository <T extends User> {
    private Map<UUID, T> UserById = new HashMap<>();
    private Map<Long, T> UserByUniversityId = new HashMap<>();

    /**
     * Saves a user entity to the repository.
     * @param entity the user entity to save
     * @return the saved user entity
     */
    public T save(T entity) {
        UserById.putIfAbsent(entity.getUserID(), entity);
        UserByUniversityId.putIfAbsent(entity.getUniversityID(), entity);
        return entity;
    }

    /**
     * Updates a user entity in the repository.
     * @param entity the user entity to update
     * @return the updated user entity
     */
    public T update(T entity) {
        T user = UserById.put(entity.getUserID(), entity);
        UserByUniversityId.put(entity.getUniversityID(), entity);
        return user == null ? entity : user;
    }

    /**
     * Finds a user by their UUID.
     * @param id the UUID of the user
     * @return an Optional containing the found user, or an empty Optional if not found
     */
    public Optional<T> findById(UUID id) {
        return Optional.of(UserById.get(id));
    }

    /**
     * Finds a user by their university ID.
     * @param id the university ID of the user
     * @return an Optional containing the found user, or an empty Optional if not found
     */
    public Optional<T> findByUniversityId(Long id) {
        return Optional.of(UserByUniversityId.get(id));
    }

    /**
     * Returns all users in the repository.
     * @return an Iterable of all users
     */
    public Iterable<T> findAll() {
        return UserById.values();
    }

    /**
     * Returns all users in the repository that match a given filter.
     * @param filter a Predicate to filter users by
     * @return an Iterable of all matching users
     */
    public Iterable<T> findAllByFilter(Predicate<T> filter) {
        return UserById.values().stream()
                .filter(filter)
                .toList();
    }

    /**
     * Deletes a user from the repository by their UUID.
     * @param id the UUID of the user to delete
     */
    public void deleteById(UUID id) {
        UserById.remove(id);
    }

    /**
     * Deletes a user from the repository by their university ID.
     * @param id the university ID of the user to delete
     */
    public void deleteByUniversityId(Long id) {
        UserByUniversityId.remove(id);
    }
}
