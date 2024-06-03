package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing a user.
 */
record UserDto(@Nullable Long Id, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
               String email) {

}