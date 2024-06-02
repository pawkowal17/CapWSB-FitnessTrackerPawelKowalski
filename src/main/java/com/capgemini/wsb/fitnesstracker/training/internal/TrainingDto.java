package com.capgemini.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.util.Date;

record TrainingDto(
        @Nullable Long id,
        Long userId,
        @JsonFormat(pattern = "yyyy-MM-dd") Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd") Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {}