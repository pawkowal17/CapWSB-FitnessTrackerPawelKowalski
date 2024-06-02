package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Optional;
import java.util.Date;

/**
 * Interface (API) for retrieving operations on {@link Training} entities.
 * Implementing classes are responsible for fetching trainings from the database.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     *
     * @return a list containing all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves trainings for a specific user based on their ID.
     *
     * @param userId id of the user whose trainings are to be retrieved
     * @return a list of trainings for the specified user
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Retrieves all trainings that ended after a specified date.
     *
     * @param endTime the date after which trainings should be retrieved
     * @return a list of trainings that ended after the specified date
     */
    List<Training> getTrainingsByEndTimeAfter(Date endTime);

    /**
     * Retrieves all trainings of a specific activity type.
     *
     * @param activityType the type of activity for which trainings should be retrieved
     * @return a list of trainings of the specified activity type
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);
}