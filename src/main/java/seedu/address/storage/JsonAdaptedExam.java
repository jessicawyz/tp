package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Missing %s field in JSON data.";
    private final String name;
    private final Object date;
    private final Object time;
    private final String studentName;
    private final String uniqueId;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code name}, {@code date}, and {@code time}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("name") String name,
                           @JsonProperty("date") Object date,
                           @JsonProperty("time") Object time,
                           @JsonProperty("studentName") String studentName,
                           @JsonProperty("uniqueId") String uniqueId) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.studentName = studentName;
        this.uniqueId = uniqueId;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        this.name = source.getExamName();
        this.date = source.getDate();
        this.time = source.getTime();
        this.studentName = source.getStudentName();
        this.uniqueId = source.getUniqueId().toString();
    }

    public String getName() {
        return name;
    }

    public Object getDate() {
        return date;
    }

    public Object getTime() {
        return time;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code Exam} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exam.
     */
    public Exam toModelType() throws IllegalValueException {
        // Parse name
        String parsedName = name;

        // Parse date
        LocalDate parsedDate;
        try {
            parsedDate = ParserUtil.parseDate(date.toString());
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // Parse optional time
        Optional<LocalTime> parsedTime;
        try {
            String timeString = time.toString();
            timeString = timeString.substring(timeString.indexOf('=') + 1, timeString.lastIndexOf('}'));
            parsedTime = ParserUtil.parseTimeFromStorage(timeString);
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // Parse student name
        String parsedStudentName = studentName;

        Id parsedUniqueId = ParserUtil.parseId(uniqueId);

        Exam newExam = new Exam(parsedName, parsedDate, parsedTime, parsedStudentName, parsedUniqueId);

        return newExam;
    }

    /**
     * Checks if the given exam is overdue based on the current date and time.
     * If the exam's date is before the current date or if the exam's date is today
     * and its time is before the current time, then the exam is considered overdue.
     *
     * @param exam The JsonAdaptedExam object representing the exam to be checked.
     * @return true if the exam is overdue, false otherwise.
     */
    public boolean isExamOverdue(JsonAdaptedExam exam) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Parse date
        LocalDate parsedDate;
        try {
            parsedDate = ParserUtil.parseDate(date.toString());
        } catch (ParseException e) {
            return false;
        }

        // Check if date is before current date
        if (parsedDate.isBefore(currentDate)) {
            return true;
        }

        // Parse optional time
        Optional<LocalTime> parsedTime;
        try {
            String timeString = time.toString();
            timeString = timeString.substring(timeString.indexOf('=') + 1, timeString.lastIndexOf('}'));
            parsedTime = ParserUtil.parseTimeFromStorage(timeString);
        } catch (ParseException e) {
            return false;
        }

        // Check if date is today and time is before current time
        if (parsedDate.isEqual(currentDate) && parsedTime.isPresent() && parsedTime.get().isBefore(currentTime)) {
            return true;
        }

        return false;
    }
}
