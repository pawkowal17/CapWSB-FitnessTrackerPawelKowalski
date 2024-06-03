package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.List;

/**
 * Service interface for generating and sending training reports.
 */
public interface TrainingReportService {

    /**
     * Generates a weekly training report for all users and sends it via email.
     */
    void generateAndSendWeeklyReport();

    /**
     * Prepares a report for a specific user based on their trainings.
     *
     * @param user the user for whom the report is being prepared
     * @param trainings the list of trainings associated with the user
     * @return a string containing the prepared report
     */
    String prepareUserReport(User user, List<Training> trainings);
}