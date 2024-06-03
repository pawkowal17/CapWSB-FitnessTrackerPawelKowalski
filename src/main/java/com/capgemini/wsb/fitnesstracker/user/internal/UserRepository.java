package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link User} entities.
 * Extends the {@link JpaRepository} to provide CRUD operations and additional query methods.
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Finds users who are older than a given age. The age is calculated based on the current date.
     *
     * @param age the age threshold for filtering users
     * @return a list of users who are older than the specified age
     */
    default List<User> findUsersOlderThan(int age) {
        LocalDate cutoffDate = LocalDate.now().minusYears(age);
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(cutoffDate))
                .toList();
    }
}