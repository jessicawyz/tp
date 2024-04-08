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

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_DATE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
        }

        String uniqueId = argMultimap.getValue(PREFIX_ID).get();
        String examName = ParserUtil.parseExamName(argMultimap.getValue(PREFIX_EXAM_NAME).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        if (date.isBefore(LocalDate.now())) {
            throw new ParseException(MESSAGE_CONSTRAINTS_PAST_DATE);
        }
        Optional<String> timeValue = argMultimap.getValue(PREFIX_TIME);
        Optional<LocalTime> time;
        if (timeValue.isPresent()) {
            time = ParserUtil.parseTime(timeValue.get());
        } else {
            time = Optional.empty();
        }

        return new AddExamCommand(uniqueId, examName, date, time);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
