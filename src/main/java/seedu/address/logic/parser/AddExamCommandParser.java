package seedu.address.logic.parser;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

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
        assert argMultimap.getValue(PREFIX_ID).isPresent() : "Prefix ID value is missing";
        assert argMultimap.getValue(PREFIX_EXAM_NAME).isPresent() : "Prefix Exam Name value is missing";
        assert argMultimap.getValue(PREFIX_DATE).isPresent() : "Prefix Date value is missing";

        String uniqueId = argMultimap.getValue(PREFIX_ID).get();
        String examName = ParserUtil.parseExamName(argMultimap.getValue(PREFIX_EXAM_NAME).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
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
