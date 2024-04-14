package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllExamsListTest {

    private AllExamsList allExamsList;
    private Exam exam1;
    private Exam exam2;
    private Exam exam3;

    @BeforeEach
    public void setUp() {
        allExamsList = new AllExamsList();
        exam1 = new Exam("Math", LocalDate.of(2025, 5, 10),
                Optional.of(LocalTime.of(14, 30)), "John Doe", new Id("000001"));
        exam2 = new Exam("Science", LocalDate.of(2025, 5, 12),
                Optional.of(LocalTime.of(9, 50)), "Jane Doe", new Id("000002"));
        exam3 = new Exam("History", LocalDate.of(2024, 5, 13),
                Optional.of(LocalTime.of(10, 50)), "Doo Doe", new Id("000003"));
    }

    @Test
    public void addExamToList_validExam_success() {
        allExamsList.addExamToList(exam1);
        List<Exam> exams = AllExamsList.getExams();
        assertEquals(1, exams.size());
        assertEquals(exam1, exams.get(0));
    }

    @Test
    public void addExamToList_duplicateExam_throwsIllegalArgumentException() {
        allExamsList.addExamToList(exam1);
        assertThrows(IllegalArgumentException.class, () -> allExamsList.addExamToList(exam1));
    }

    @Test
    public void deleteExamFromList_validExam_success() {
        allExamsList.addExamToList(exam1);
        allExamsList.addExamToList(exam2);
        allExamsList.deleteExamFromList(exam1);
        List<Exam> exams = AllExamsList.getExams();
        assertEquals(1, exams.size());
        assertEquals(exam2, exams.get(0));
    }

    @Test
    public void deleteAllExamFromList_validExam_success() {
        allExamsList.addExamToList(exam1);
        allExamsList.addExamToList(exam2);
        allExamsList.deleteAllExamFromList(exam1);
        List<Exam> exams = AllExamsList.getExams();
        assertEquals(1, exams.size());
        assertEquals(exam2, exams.get(0));
    }

    @Test
    public void getSortedByDate_validExams_success() {
        allExamsList.addExamToList(exam2);
        allExamsList.addExamToList(exam1);
        List<Exam> exams = AllExamsList.getExams();
        assertEquals(exam1, exams.get(0));
        assertEquals(exam2, exams.get(1));
    }

    @Test
    public void getUpcomingMonthExamCount_noExams_returnsZero() {
        assertEquals(0, allExamsList.getUpcomingMonthExamCount());
    }

    @Test
    public void getUpcomingMonthExamCount_oneUpcomingExam_returnsOne() {
        allExamsList.addExamToList(exam3);
        assertEquals(1, allExamsList.getUpcomingMonthExamCount());
    }

    @Test
    public void getUpcomingMonthExamCount_onePastExam_returnsZero() {
        exam1 = new Exam("Math", LocalDate.of(2025, 5, 10),
                Optional.of(LocalTime.of(14, 30)), "John Doe", new Id("000001"));
        allExamsList.addExamToList(exam1);
        assertEquals(0, allExamsList.getUpcomingMonthExamCount());
    }
}

