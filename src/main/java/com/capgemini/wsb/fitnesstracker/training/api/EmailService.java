package com.capgemini.wsb.fitnesstracker.training.api;

/**
 * Service interface for sending emails.
 */
public interface EmailService {

    /**
     * Sends an email with the specified recipient, subject, and content.
     *
     * @param recipient the email address of the recipient
     * @param subject the subject of the email
     * @param content the content of the email
     */
    void sendEmailWithReport(String recipient, String subject, String content);
}