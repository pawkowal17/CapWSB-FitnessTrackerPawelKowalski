package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handling training-related HTTP requests.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingReportServiceImpl trainingReportService;
    private final TrainingMapper trainingMapper;

    /**
     * Creates a new training record.
     *
     * @param trainingDto the DTO representation of the training to create
     * @return the DTO representation of the created training
     */
    @PostMapping
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * Retrieves a list of all trainings.
     *
     * @return a list of DTO representations of trainings
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of trainings associated with a specific user ID.
     *
     * @param userId the ID of the user to search for trainings
     * @return a list of DTO representations of trainings
     */
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of trainings that end after a specified date.
     *
     * @param afterDate the date to compare against the end time of the trainings
     * @return a list of DTO representations of trainings
     */
    @GetMapping("/after/{afterDate}")
    public List<TrainingDto> getTrainingsByEndTimeAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterDate) {
        return trainingService.getTrainingsByEndTimeAfter(afterDate)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of trainings of a specific activity type.
     *
     * @param activityType the activity type to search for
     * @return a list of DTO representations of trainings
     */
    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivityType(@PathVariable ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Updates an existing training record.
     *
     * @param id          the ID of the training to update
     * @param trainingDto the DTO representation of the updated training
     * @return the DTO representation of the updated training
     */
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Partially updates an existing training record.
     *
     * @param id      the ID of the training to update
     * @param updates a map containing the fields to update and their new values
     * @return the DTO representation of the updated training
     */
    @PatchMapping("/{id}")
    public TrainingDto partiallyUpdateTraining(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Training updatedTraining = trainingService.partiallyUpdateTraining(id, updates);
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Generates and sends a weekly training report.
     *
     * @return a ResponseEntity indicating the status of the operation
     */
    @PostMapping("/generate-and-send")
    public ResponseEntity<String> generateAndSendWeeklyReport() {
        try {
            // Generowanie i wysyłanie raportu
            trainingReportService.generateAndSendWeeklyReport();
            return ResponseEntity.ok("Weekly report generated and sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate and send weekly report: " + e.getMessage());
        }
    }
}