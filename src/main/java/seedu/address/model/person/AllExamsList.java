package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllExamsList {
    public static ObservableList<Exam> exams;

    public AllExamsList() {
        exams = FXCollections.observableArrayList();
    }

    public static void addExamToList(Exam exam) {
        exams.add(exam);
        getSortedByDate();
    }

    public static void getSortedByDate() {
        Comparator<Exam> comparator = (exam1, exam2) -> {
            LocalDate currentDate = LocalDate.now();
            LocalDate examDate1 = exam1.date;
            LocalDate examDate2 = exam2.date;
            long daysDifference1 = Math.abs(ChronoUnit.DAYS.between(currentDate, examDate1));
            long daysDifference2 = Math.abs(ChronoUnit.DAYS.between(currentDate, examDate2));
            return Long.compare(daysDifference1, daysDifference2);
        };

        exams.sorted(comparator);
        System.out.println(exams);
    }
}
