package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {

    private final String name;
    private final Object date;
    private final Object time;
    private final String studentName; // Student name
    private final int uniqueId; // Unique ID

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code name}, {@code date}, and {@code time}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("name") String name,
                           @JsonProperty("date") Object date,
                           @JsonProperty("time") Object time,
                           @JsonProperty("studentName") String studentName,
                           @JsonProperty("uniqueId") int uniqueId) {
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
        this.uniqueId = source.getUniqueId().getInt();
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

    public int getUniqueId() {
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
            parsedDate = ParserUtil.parseDate(date.toString()); // Convert Object to String
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // Parse optional time
        Optional<LocalTime> parsedTime;
        try {
            parsedTime = ParserUtil.parseTime(time.toString()); // Convert Object to String
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // Parse student name
        String parsedStudentName = studentName;

        // Parse unique ID
        Id parsedUniqueId = ParserUtil.parseUniqueIdtoId(uniqueId);

        return new Exam(parsedName, parsedDate, parsedTime, parsedStudentName, parsedUniqueId);
    }
}
