package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExamCommand object.
 */
public class DeleteExamCommandParser implements Parser<DeleteExamCommand> {

    @Override
    public DeleteExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_EXAM_NAME, PREFIX_DATE, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_EXAM_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExamCommand.MESSAGE_USAGE));
        }

        String uniqueId = argMultimap.getValue(PREFIX_ID).get();
        String examName = argMultimap.getValue(PREFIX_EXAM_NAME).get();
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Optional<String> timeValue = argMultimap.getValue(PREFIX_TIME);
        Optional<LocalTime> time;
        if (timeValue.isPresent()) {
            time = ParserUtil.parseTime(timeValue.get());
        } else {
            time = Optional.empty();
        }

        return new DeleteExamCommand(uniqueId, examName, date, time);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
