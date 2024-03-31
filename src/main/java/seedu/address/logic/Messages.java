package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found";
    public static final String MESSAGE_EXAM_NOT_FOUND = "Exam to delete cannot be found";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append(" ; Phone: ")
                .append(person.getPhone())
                .append(" ; Email: ")
                .append(person.getEmail())
                .append(" ; Address: ")
                .append(person.getAddress())
                .append(" ; Subjects: ")
                .append(person.getSubject())
                .append(" ; Payment: ")
                .append(person.getPayment());
        builder.append(" ; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    public static String format(Log log) {
        final StringBuilder builder = new StringBuilder();
        builder.append(log.getDate())
                .append("; Time: ")
                .append(log.getHours())
                .append("; Hours: ")
                .append(log.getLearningStyle())
                .append("; Learning Style: ")
                .append(log.getNotes())
                .append("; Notes:");
        return builder.toString();
    }

}
