package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String subject} into a {@code Subject}.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }

    /**
     * Parses id input to check if id is valid to instantiate a new Id object.
     * @param id Id to check for validity;
     * @return New Id object with the input id.
     * @throws ParseException If input id is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(id);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code uniqueId} into an int and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified uniqueId is not an integer.
     */
    public static int parseUniqueId(String uniqueId) throws ParseException {
        String trimmedUniqueId = uniqueId.trim();
        try {
            return Integer.parseInt(trimmedUniqueId);
        } catch (NumberFormatException e) {
            throw new ParseException("Unique ID must be an integer.");
        }
    }

    public static Id parseUniqueIdtoId(int uniqueId) throws ParseException {
        return new Id(uniqueId);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date format. Please enter the date in the format dd-MM-yyyy.");
        }
    }

    /**
     * Parses a {@code String time} into a {@code Option<LocalTime>}.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Optional<LocalTime> parseTime(String time) throws ParseException {
        if (time == null || time.isEmpty()) {
            return Optional.empty();
        }

        String trimmedTime = time.trim();
        try {
            LocalTime parsedTime = LocalTime.parse(trimmedTime, DateTimeFormatter.ofPattern("HH:mm"));
            return Optional.of(parsedTime);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid time format. Please enter the time in the format HH:mm.");
        }
    }

    /**
     * Parses a {@code String examName} into an {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code examName} is invalid.
     */
    public static String parseExamName(String examName) throws ParseException {
        requireNonNull(examName);
        String trimmedExamName = examName.trim();
        if (!Exam.isValidExamName(trimmedExamName)) {
            throw new ParseException(Exam.MESSAGE_CONSTRAINTS_EXAM_NAME);
        }
        return trimmedExamName;
    }

    /**
     * Parses an {@code Exam} object from the input {@code name}, {@code date} and {@code time}.
     *
     * @throws ParseException if either the date or time is invalid.
     */
    /*public static Exam parseExam(String name, String date, String time) throws ParseException {
        LocalDate parsedDate = parseDate(date);
        Optional<LocalTime> parsedTime;
        if (time != null && !time.isEmpty()) {
            parsedTime = parseTime(time);
        }
        return new Exam(name, parsedDate, parsedTime);
    }*/
}
