package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Log;



public class JsonAdaptedLogTest {

    private static final String VALID_DATE = "2023-09-15 10:45:00";
    private static final String VALID_LEARNING_STYLE = "Visual";
    private static final String VALID_HOURS = "2";
    private static final String VALID_LESSON_CONTENT = "Trigonometry";
    private static final String VALID_NOTES = "Focus on Pythagorean theorem";

    private static final Log VALID_LOG = new Log(VALID_LEARNING_STYLE, VALID_HOURS, VALID_LESSON_CONTENT, VALID_NOTES,
            VALID_DATE);

    @Test
    public void toModelType_validDetails_returnsLog() throws Exception {
        JsonAdaptedLog jsonLog = new JsonAdaptedLog(VALID_DATE, VALID_LEARNING_STYLE, VALID_HOURS, VALID_LESSON_CONTENT,
                VALID_NOTES);
        assertEquals(VALID_LOG, jsonLog.toModelType());
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedLog jsonLog = new JsonAdaptedLog(null, VALID_LEARNING_STYLE, VALID_HOURS, VALID_LESSON_CONTENT,
                VALID_NOTES);
        String expectedMessage = String.format(JsonAdaptedLog.MISSING_FIELD_MESSAGE_FORMAT, "date");
        assertThrows(IllegalValueException.class, () -> jsonLog.toModelType(), expectedMessage);
    }

    @Test
    public void toModelType_nullLearningStyle_throwsIllegalValueException() {
        JsonAdaptedLog jsonLog = new JsonAdaptedLog(VALID_DATE, null, VALID_HOURS, VALID_LESSON_CONTENT,
                VALID_NOTES);
        String expectedMessage = String.format(JsonAdaptedLog.MISSING_FIELD_MESSAGE_FORMAT, "learning style");
        assertThrows(IllegalValueException.class, () -> jsonLog.toModelType(), expectedMessage);
    }

    @Test
    public void toModelType_nullHours_throwsIllegalValueException() {
        JsonAdaptedLog jsonLog = new JsonAdaptedLog(VALID_DATE, VALID_LEARNING_STYLE, null, VALID_LESSON_CONTENT,
                VALID_NOTES);
        String expectedMessage = String.format(JsonAdaptedLog.MISSING_FIELD_MESSAGE_FORMAT, "hours");
        assertThrows(IllegalValueException.class, () -> jsonLog.toModelType(), expectedMessage);
    }

    @Test
    public void toModelType_nullLessonContent_throwsIllegalValueException() {
        JsonAdaptedLog jsonLog = new JsonAdaptedLog(VALID_DATE, VALID_LEARNING_STYLE, VALID_HOURS, null,
                VALID_NOTES);
        String expectedMessage = String.format(JsonAdaptedLog.MISSING_FIELD_MESSAGE_FORMAT, "lesson content");
        assertThrows(IllegalValueException.class, () -> jsonLog.toModelType(), expectedMessage);
    }

    @Test
    public void toModelType_nullNotes_throwsIllegalValueException() {
        JsonAdaptedLog jsonLog = new JsonAdaptedLog(VALID_DATE, VALID_LEARNING_STYLE, VALID_HOURS,
                VALID_LESSON_CONTENT, null);
        String expectedMessage = String.format(JsonAdaptedLog.MISSING_FIELD_MESSAGE_FORMAT, "notes");
        assertThrows(IllegalValueException.class, () -> jsonLog.toModelType(), expectedMessage);
    }
}
