package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.EmailService;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingReportService;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingReportServiceImpl implements TrainingReportService {

    private final EmailService emailService;
    private final TrainingRepository trainingRepository;
    private final UserServiceImpl userService;

    @Override
    public void generateAndSendWeeklyReport() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date startDate = calendar.getTime();

        List<User> users = userService.findAllUsers();

        for (User user : users) {
            List<Training> userTrainings = trainingRepository.findByUserIdAndStartTimeBetween(user.getId(), startDate, currentDate);
            String report = prepareUserReport(user, userTrainings);
            emailService.sendEmailWithReport(user.getEmail(), "Weekly Training Report", report);
        }
    }

    @Override
    public String prepareUserReport(User user, List<Training> trainings) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Weekly Training Report for ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("\n\n");

        int totalTrainings = trainings.size();
        reportBuilder.append("Total Trainings: ").append(totalTrainings).append("\n\n");

        for (int i = 0; i < totalTrainings; i++) {
            Training training = trainings.get(i);
            reportBuilder.append("Training ").append(i + 1).append(":\n");
            reportBuilder.append("Start Time: ").append(training.getStartTime()).append("\n");
            reportBuilder.append("End Time: ").append(training.getEndTime()).append("\n");
            reportBuilder.append("Activity Type: ").append(training.getActivityType()).append("\n");
            reportBuilder.append("Distance: ").append(training.getDistance()).append(" km\n");
            reportBuilder.append("Average Speed: ").append(training.getAverageSpeed()).append(" km/h\n\n");
        }

        return reportBuilder.toString();
    }
}