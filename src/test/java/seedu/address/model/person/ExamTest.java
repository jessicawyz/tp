package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ExamTest {

    @Test
    public void constructor_validParameters_success() {
        String name = "Math Final";
        LocalDate date = LocalDate.now().plusDays(1); // valid future date
        LocalTime time = LocalTime.of(14, 0);
        String studentName = "John Doe";
        Id uniqueId = new Id("000001");

        Exam exam = new Exam(name, date, Optional.of(time), studentName, uniqueId);

        assertEquals(name, exam.getExamName());
        assertEquals(date, exam.getExamDate());
        assertEquals(Optional.of(time), exam.getExamTime());
        assertEquals(studentName, exam.getStudentName());
        assertEquals(uniqueId, exam.getUniqueId());
    }

    @Test
    public void isValidExamDate_pastDate_false() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertFalse(Exam.isValidExamDate(pastDate));
    }

    @Test
    public void isValidExamDate_futureDate_true() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertTrue(Exam.isValidExamDate(futureDate));
    }

    @Test
    public void isValidExamTime_invalidTime_throwsDateTimeException() {
        assertThrows(DateTimeException.class, () -> LocalTime.of(25, 0));
    }

    @Test
    public void isValidExamTime_validTime_true() {
        LocalTime validTime = LocalTime.of(14, 30);
        assertTrue(Exam.isValidExamTime(validTime));
    }

    @Test
    public void getDaysFromCurrentDate_validDate_success() {
        LocalDate date = LocalDate.now().plusDays(5);
        Exam exam = new Exam("Math Final", date, Optional.empty());

        assertEquals(5, exam.getDaysFromCurrentDate());
    }

    @Test
    public void equals_sameExamAndStudentAttributes_true() {
        Id studentId = new Id("000002");
        Exam exam1 = new Exam("Math Final", LocalDate.of(2024, 10, 10),
                Optional.of(LocalTime.of(10, 0)), "Amy Bee", studentId);
        Exam exam2 = new Exam("Math Final", LocalDate.of(2024, 10, 10),
                Optional.of(LocalTime.of(10, 0)), "Amy Bee", studentId);

        assertTrue(exam1.equals(exam2));
    }

    @Test
    public void equals_differentAttributes_false() {
        Exam exam1 = new Exam("Math Final", LocalDate.of(2024, 10, 10),
                Optional.of(LocalTime.of(10, 0)));
        Exam exam2 = new Exam("Math Midterm", LocalDate.of(2024, 11, 10),
                Optional.of(LocalTime.of(12, 0)));

        assertFalse(exam1.equals(exam2));
    }
}
