package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class LogTest {

    @Test
    public void constructor_withDate_properInitialization() {
        String date = "2020-01-01 10:00:00";
        Log log = new Log("Visual", "2", "English Comprehension",
                "Great improvement!", date);

        assertEquals(date, log.getDate());
        assertEquals("Visual", log.getLearningStyle());
        assertEquals("2", log.getHours());
        assertEquals("English Comprehension", log.getLessonContent());
        assertEquals("Great improvement!", log.getNotes());
    }

    @Test
    public void constructor_withoutDate_currentDateSet() {
        Log log = new Log("Visual", "2", "English Comprehension",
                "Great improvement!", null);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String expectedDate = dateFormat.format(currentDate);

        assertEquals(expectedDate.substring(0, 10), log.getDate().substring(0, 10));
        assertEquals("Visual", log.getLearningStyle());
        assertEquals("2", log.getHours());
        assertEquals("English Comprehension", log.getLessonContent());
        assertEquals("Great improvement!", log.getNotes());
    }

    @Test
    public void toString_validLog_correctStringRepresentation() {
        String date = "2020-01-01 10:00:00";
        Log log = new Log("Visual", "2", "English Comprehension",
                "Great improvement!", date);

        String expected = String.format("Log on %s\nHours: 2\nLearning Style: Visual\nLesson Content: "
                + "English Comprehension\nNotes: Great improvement!\n", date);
        assertEquals(expected, log.toString());
    }
}
