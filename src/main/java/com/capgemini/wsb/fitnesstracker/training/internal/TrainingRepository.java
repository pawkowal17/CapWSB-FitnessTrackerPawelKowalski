package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for accessing and managing {@link Training} entities.
 * Extends the {@link JpaRepository} to provide CRUD operations and additional query methods.
 */
@Repository
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds a list of trainings associated with a specific user ID.
     *
     * @param userId the ID of the user to search for trainings
     * @return a list of trainings associated with the given user ID
     */
    List<Training> findByUserId(Long userId);

    /**
     * Finds a list of trainings that end after a specified date.
     *
     * @param endTime the date to compare against the end time of the trainings
     * @return a list of trainings that end after the specified date
     */
    List<Training> findByEndTimeAfter(Date endTime);

    /**
     * Finds a list of trainings of a specific activity type.
     *
     * @param activityType the activity type to search for
     * @return a list of trainings with the specified activity type
     */
    List<Training> findByActivityType(ActivityType activityType);

    /**
     * Finds a list of trainings associated with a specific user ID
     * and that start within a given date range.
     *
     * @param userId    the ID of the user to search for trainings
     * @param startTime the start date to compare against the start time of the trainings
     * @param endTime   the end date to compare against the start time of the trainings
     * @return a list of trainings associated with the given user ID that start within the specified date range
     */
    List<Training> findByUserIdAndStartTimeBetween(Long userId, Date startTime, Date endTime);
}