package seedu.address.model.person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private final String date;
    private final String learningStyle;
    private final String hours;
    private final String lessonContent;
    private final String notes;

    public Log(String learningStyle, String hours, String lessonContent, String notes) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = dateFormat.format(currentDate);

        this.learningStyle = learningStyle;
        this.hours = hours;
        this.lessonContent = lessonContent;
        this.notes = notes;
    }

    public String getDate() {
        return this.date;
    }

    public String getLearningStyle() {
        return this.learningStyle;
    }

    public String getHours() {
        return this.hours;
    }

    public String getLessonContent() {
        return this.lessonContent;
    }

    public String getNotes() {
        return this.notes;
    }
}
