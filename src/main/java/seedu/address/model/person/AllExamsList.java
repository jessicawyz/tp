package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AllExamsList {
    public static ObservableList<Exam> exams;

    public AllExamsList() {
        exams = FXCollections.observableArrayList();
    }

    public static void addExamToList(Exam exam) {
        if (exam == null) {
            System.out.println("exam is null before adding to allexamlist");
        }
        exams.add(exam);
        getSortedByDate(exams);
    }

    public static void deleteExamFromList(Exam examToDelete) {
        Iterator<Exam> iterator = exams.iterator();
        while (iterator.hasNext()) {
            Exam exam = iterator.next();
            if (exam.equals(examToDelete)) {
                iterator.remove();
                break;
            }
        }
    }

    public static void getSortedByDate(List<Exam> exams) {
        Comparator<Exam> comparator = (exam1, exam2) -> {
            LocalDate currentDate = LocalDate.now();
            LocalDate examDate1 = exam1.date;
            LocalDate examDate2 = exam2.date;
            int dateComparison = Long.compare(Math.abs(ChronoUnit.DAYS.between(currentDate, examDate1)),
                    Math.abs(ChronoUnit.DAYS.between(currentDate, examDate2)));

            // If the dates are equal, compare by time
            if (dateComparison == 0) {
                Optional<LocalTime> time1 = exam1.time;
                Optional<LocalTime> time2 = exam2.time;

                // Handle the case when one or both exams have no time
                if (time1.isPresent() && time2.isPresent()) {
                    return time1.get().compareTo(time2.get());
                } else if (time1.isPresent()) {
                    return -1;
                } else if (time2.isPresent()) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return dateComparison;
        };

        Collections.sort(exams, comparator); // Sort the exams list using the comparator
    }
}