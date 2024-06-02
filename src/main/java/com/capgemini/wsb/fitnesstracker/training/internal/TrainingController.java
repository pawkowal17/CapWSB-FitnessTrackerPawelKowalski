package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @PostMapping
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/after/{afterDate}")
    public List<TrainingDto> getTrainingsByEndTimeAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterDate) {
        return trainingService.getTrainingsByEndTimeAfter(afterDate)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivityType(@PathVariable ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(updatedTraining);
    }

    @PatchMapping("/{id}")
    public TrainingDto partiallyUpdateTraining(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Training updatedTraining = trainingService.partiallyUpdateTraining(id, updates);
        return trainingMapper.toDto(updatedTraining);
    }
}