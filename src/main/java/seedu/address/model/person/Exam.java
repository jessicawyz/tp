package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * Represents a Person's exam details in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidExamDate(LocalDate)}
 * and {@link #isValidExamTime(LocalTime)}
 */
public class Exam {

    public static final String MESSAGE_CONSTRAINTS_EXAM_NAME = "Exam name cannot be blank";
    public static final String MESSAGE_CONSTRAINTS_DATE = "Exam date must be a valid date in the format dd-MM-yyyy";
    public static final String MESSAGE_CONSTRAINTS_TIME = "Exam time must be a valid time in the format HH:mm";

    public final LocalDate date;
    public final Optional<LocalTime> time;

    public final String name;
    private String studentName;
    private Id uniqueId;

    /**
     * Constructs an {@code Exam} with optional time.
     *
     * @param date A valid exam date.
     * @param time A valid exam time (optional).
     */
    public Exam(String name, LocalDate date, Optional<LocalTime> time) {
        requireNonNull(name);
        requireNonNull(date);
        requireNonNull(time);
        checkArgument(isValidExamDate(date), MESSAGE_CONSTRAINTS_DATE);
        // Check if time is present, and if present, validate it, else time can be null
        if (time.isPresent()) {
            checkArgument(isValidExamTime(time.get()), MESSAGE_CONSTRAINTS_TIME);
        }
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * Represents a Person's exam details in the address book.
     * Guarantees: immutable; is valid as declared in {@link #isValidExamDate(LocalDate)}
     * and {@link #isValidExamTime(LocalTime)}
     */
    public Exam(String name, LocalDate date, Optional<LocalTime> time, String studentName, Id uniqueId) {
        requireNonNull(name);
        requireNonNull(date);
        requireNonNull(time);
        requireNonNull(studentName);
        requireNonNull(uniqueId);
        checkArgument(isValidExamDate(date), MESSAGE_CONSTRAINTS_DATE);
        // Check if time is present, and if present, validate it, else time can be null
        if (time.isPresent()) {
            checkArgument(isValidExamTime(time.get()), MESSAGE_CONSTRAINTS_TIME);
        }
        this.name = name;
        this.date = date;
        this.time = time;
        this.studentName = studentName;
        this.uniqueId = uniqueId;
    }

    /**
     * Returns true if the given exam name is valid.
     */
    public static boolean isValidExamName(String name) {
        // Exam name should not be null or empty
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Returns true if a given date is a valid exam date (from today onwards).
     */
    public static boolean isValidExamDate(LocalDate test) {
        LocalDate currentDate = LocalDate.now();
        return !test.isBefore(currentDate);
    }

    /**
     * Returns true if a given time is a valid exam time (in 24-hour clock format in HHmm).
     */
    public static boolean isValidExamTime(LocalTime test) {
        if (test == null) {
            return false; // Null time is not valid
        }
        // Check if the time format is in HHmm
        String timeString = test.format(DateTimeFormatter.ofPattern("HH:mm"));
        try {
            LocalTime parsedTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public long getDaysFromCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Math.abs(ChronoUnit.DAYS.between(currentDate, date));
    }

    @Override
    public String toString() {
        // Format date and time for display
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Date: ").append(date.format(dateFormatter));
        time.ifPresent(t -> stringBuilder.append(", Time: ").append(t.format(DateTimeFormatter.ofPattern("HH:mm"))));
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Exam)) {
            return false;
        }
        Exam otherExam = (Exam) other;
        return date.equals(otherExam.date)
                && time.equals(otherExam.time)
                && name.equals(otherExam.name)
                && studentName.equals(otherExam.studentName)
                && uniqueId.equals(otherExam.uniqueId);
    }

    @Override
    public int hashCode() {
        return date.hashCode() + time.hashCode();
    }

    public Object getDate() {
        return date;
    }
    public LocalDate getExamDate() {
        return date;
    }

    public Optional<LocalTime> getExamTime() {
        return time;
    }
    public Object getTime() {
        return time;
    }

    public String getExamName() {
        return name;
    }

    public String getStudentName() {
        return studentName;
    }
    public Id getUniqueId() {
        return uniqueId;
    }
}

