package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user entity to be created
     * @return the created user entity
     */
    User createUser(User user);

    /**
     * Deletes a user based on their ID.
     *
     * @param userId the ID of the user to be deleted
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user.
     *
     * @param userId the ID of the user to be updated
     * @param user the updated user entity
     * @return the updated user entity
     */
    User updateUser(Long userId, User user);
}