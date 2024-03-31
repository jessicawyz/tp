package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATSLONG;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.StatCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.person.IsSameIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand or ViewCommand object depending on the arguments.
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Parses view command arguments and returns the new command as a new view command object.
     * @param args Input arguments.
     * @return ViewCommand object of the right constructor.
     * @throws ParseException If args are missing or invalid.
     */
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_STATS, PREFIX_STATSLONG, PREFIX_ALL);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_ID)
                && !arePrefixesPresent(argMultimap, PREFIX_STATS)
                && !arePrefixesPresent(argMultimap, PREFIX_STATSLONG)
                && !arePrefixesPresent(argMultimap, PREFIX_ALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = args.trim();
        Set<Prefix> usedPrefixes = argMultimap.getAllPrefixes();
        // Check size of prefix set is not more than 2 (1 for preamble, 1 for prefix)
        if (usedPrefixes.size() > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_MULTIPREFIX));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_ALL)) {
            return new ListCommand();
        } else if (!argMultimap.getAllValues(PREFIX_NAME).isEmpty()) {
            try {
                ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (!argMultimap.getAllValues(PREFIX_ID).isEmpty()) {
            Id id = null;
            try {
                id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }
            return new ViewCommand(new IsSameIdPredicate(id));
        } else if (arePrefixesPresent(argMultimap, PREFIX_STATS) || arePrefixesPresent(argMultimap, PREFIX_STATSLONG)) {
            return new StatCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
