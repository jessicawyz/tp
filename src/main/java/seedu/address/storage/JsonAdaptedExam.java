package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Exam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {

    private final Object date;
    private final Object time; // Optional time field

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code date} and {@code time}.
     */
    @JsonCreator
    public JsonAdaptedExam(Object date, Object time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        this.date = source.getDate();
        this.time = source.getTime();
    }

    @JsonValue
    public Object getDate() {
        return date;
    }

    public Object getTime() {
        return time;
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code Exam} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exam.
     */
    public Exam toModelType() throws IllegalValueException {
        // Parse date
        LocalDate parsedDate;
        try {
            parsedDate = ParserUtil.parseDate(date.toString()); // Convert Object to String
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        // Parse optional time
        Optional<LocalTime> parsedTime;
        if (time instanceof Optional) {
            Optional<?> optionalTime = (Optional<?>) time;
            if (optionalTime.isPresent()) {
                try {
                    parsedTime = Optional.of(ParserUtil.parseTime(optionalTime.get().toString())); // Convert Object to String
                } catch (ParseException e) {
                    throw new IllegalValueException(e.getMessage());
                }
            } else {
                parsedTime = Optional.empty();
            }
        } else {
            throw new IllegalValueException("Invalid time format"); // Handle unexpected type for time
        }

        return new Exam(parsedDate, parsedTime);
    }
}
