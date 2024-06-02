package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsByEndTimeAfter(Date endTime) {
        return trainingRepository.findByEndTimeAfter(endTime);
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    @Override
    public Training updateTraining(Long id, Training updatedTraining) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training not found for ID: " + id));

        if (updatedTraining.getStartTime() != null) {
            existingTraining.setStartTime(updatedTraining.getStartTime());
        }
        if (updatedTraining.getEndTime() != null) {
            existingTraining.setEndTime(updatedTraining.getEndTime());
        }
        if (updatedTraining.getActivityType() != null) {
            existingTraining.setActivityType(updatedTraining.getActivityType());
        }
        if (updatedTraining.getDistance() != 0) {
            existingTraining.setDistance(updatedTraining.getDistance());
        }
        if (updatedTraining.getAverageSpeed() != 0) {
            existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
        }

        return trainingRepository.save(existingTraining);
    }

    @Override
    public Training partiallyUpdateTraining(Long id, Map<String, Object> updates) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "userId":
                    training.setUser((User) value);
                    break;
                case "startTime":
                    training.setStartTime((Date) value);
                    break;
                case "endTime":
                    training.setEndTime((Date) value);
                    break;
                case "activityType":
                    training.setActivityType(ActivityType.valueOf((String) value));
                    break;
                case "distance":
                    training.setDistance((Double) value);
                    break;
                case "averageSpeed":
                    training.setAverageSpeed((Double) value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + key);
            }
        });
        return trainingRepository.save(training);
    }
}