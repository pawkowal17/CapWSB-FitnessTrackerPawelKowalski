package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.util.Date;

/**
 * Data transfer object (DTO) representing a training.
 */
public record TrainingDto(
        @Nullable Long id,
        Long userId,
        @JsonFormat(pattern = "yyyy-MM-dd") Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd") Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {}