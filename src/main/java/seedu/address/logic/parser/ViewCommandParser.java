package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

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
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    /*
    public Command parse(String arguments) throws ParseException {
        if (arguments.trim().equals("-all")) {
            return new ListCommand();
        } else if (arguments.trim().equals("-statistics")) {
            return new ViewCommand();
        } else if (arguments.trim().equals("-stats")) {
            return new ViewCommand();
        } else {
            throw new ParseException("Invalid arguments for 'view' command");
        }
    }*/
    // TEMPORARY - Move to CliSyntax
    public static final Prefix PREFIX_STATS = new Prefix("-stats");
    public static final Prefix PREFIX_STATSLONG = new Prefix("-statistics");
    public static final Prefix PREFIX_ALL = new Prefix("-all");

    // End of temporary

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
        System.out.println(usedPrefixes.toString());
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
