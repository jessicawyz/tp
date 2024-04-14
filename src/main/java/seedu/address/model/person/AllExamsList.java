package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.Messages;

/**
 * Represents the list of all exams in the address book.
 */
public class AllExamsList {
    private static ObservableList<Exam> exams;
    private static int upcomingMonthExamCount = 0;

    public AllExamsList() {
        exams = FXCollections.observableArrayList();
    }

    /**
     * Gets the list of all exams.
     *
     * @return The observable list of all exams.
     */
    public static ObservableList<Exam> getExams() {
        return exams;
    }


    /**
     * Adds an exam to the list of all exams.
     *
     * @param exam The exam to be added.
     */
    public static void addExamToList(Exam exam) throws IllegalArgumentException {
        if (!exams.contains(exam)) {
            exams.add(exam);
            getSortedByDateTime(exams);
        } else {
            throw new IllegalArgumentException(Messages.MESSAGE_EXAM_ALREADY_EXIST);
        }
    }

    /**
     * Deletes all matching exams from the list.
     *
     * @param examToDelete The exam to delete.
     */
    public static void deleteAllExamFromList(Exam examToDelete) {
        Iterator<Exam> iterator = exams.iterator();
        while (iterator.hasNext()) {
            Exam exam = iterator.next();
            if (exam.equals(examToDelete)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Deletes an exam from the list.
     *
     * @param examToDelete The exam to delete.
     */
    public static void deleteExamFromList(Exam examToDelete) {
        Iterator<Exam> iterator = exams.iterator();
        while (iterator.hasNext()) {
            Exam exam = iterator.next();
            if (exam.equals(examToDelete)) {
                iterator.remove();
                return;
            }
        }
    }

    /**
     * Sorts exams by date and time.
     *
     * @param exams The list of exams to be sorted.
     */
    public static void getSortedByDateTime(List<Exam> exams) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        Comparator<Exam> comparator = (exam1, exam2) -> {

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

    /**
     * Calculates and returns the count of exams scheduled for the upcoming month from the current date.
     * The count includes exams scheduled for the entire upcoming month, including today.
     * @return The count of exams scheduled for the upcoming month.
     */
    public int getUpcomingMonthExamCount() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextMonthAndADay = currentDate.plusMonths(1).plusDays(1);

        upcomingMonthExamCount = 0;
        for (Exam exam : exams) {
            LocalDate examDate = exam.date;
            if (examDate.isAfter(currentDate) && examDate.isBefore(nextMonthAndADay) || examDate.isEqual(currentDate)) {
                upcomingMonthExamCount++;
            }
        }
        return upcomingMonthExamCount;
    }

}
