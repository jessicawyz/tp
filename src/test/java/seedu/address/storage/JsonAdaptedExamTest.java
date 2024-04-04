package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedExamTest {

    private static final String VALID_NAME = "Math Final";
    private static final LocalDate VALID_DATE = LocalDate.of(2024, 12, 15);
    private static final LocalTime VALID_TIME = LocalTime.of(9, 0);
    private static final String VALID_STUDENT_NAME = "Alice Pauline";
    private static final String VALID_UNIQUE_ID = "000001";

    private static final String INVALID_DATE = "15-12-2023"; // Wrong format

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_NAME, INVALID_DATE, VALID_TIME.toString(),
                VALID_STUDENT_NAME, VALID_UNIQUE_ID);
        assertThrows(IllegalValueException.class, exam::toModelType);
    }
}
