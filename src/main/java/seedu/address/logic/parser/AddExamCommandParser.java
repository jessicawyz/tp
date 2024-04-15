package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_CONSTRAINTS_PAST_DATE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddExamCommand object.
 */
public class AddExamCommandParser implements Parser<AddExamCommand> {

    @Override
    public AddExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_EXAM_NAME, PREFIX_DATE, PREFIX_TIME);
        assert argMultimap != null : "ArgumentMultimap cannot be null";
        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_EXAM_NAME, PREFIX_DATE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
        }

        String uniqueId = argMultimap.getValue(PREFIX_ID).get();
        String examName = ParserUtil.parseExamName(argMultimap.getValue(PREFIX_EXAM_NAME).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        validateDate(date);
        Optional<String> timeValue = argMultimap.getValue(PREFIX_TIME);
        Optional<LocalTime> time = validateTime(timeValue);

        return new AddExamCommand(uniqueId, examName, date, time);
    }

    /**
     * Validates that the given date is not in the past.
     * @param date The date to be validated.
     * @throws ParseException If the given date is in the past.
     */
    private void validateDate(LocalDate date) throws ParseException {
        if (date.isBefore(LocalDate.now())) {
            throw new ParseException(MESSAGE_CONSTRAINTS_PAST_DATE);
        }
    }

    /**
     * Validates that the given time is not null.
     * @param time The time to be validated.
     * @throws ParseException If the given time is null.
     */
    private Optional<LocalTime> validateTime(Optional<String> time) throws ParseException {
        Optional<LocalTime> parsedTime;
        if (time.isPresent()) {
            parsedTime = ParserUtil.parseTime(time.get());
        } else {
            parsedTime = Optional.empty();
        }
        return parsedTime;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
