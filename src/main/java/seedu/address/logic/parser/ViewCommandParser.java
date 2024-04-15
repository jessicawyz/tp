package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATSLONG;

import java.util.Arrays;
import java.util.List;
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
        if (isNoPrefixPresent(argMultimap)) {
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
            NameContainsKeywordsPredicate keywords = getNamePredicate(argMultimap, trimmedArgs);
            return new ViewCommand(keywords);
        } else if (!argMultimap.getAllValues(PREFIX_ID).isEmpty()) {
            IsSameIdPredicate idPred = getIdPredicate(argMultimap);
            return new ViewCommand(idPred);
        } else if (arePrefixesPresent(argMultimap, PREFIX_STATS) || arePrefixesPresent(argMultimap, PREFIX_STATSLONG)) {
            return new StatCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks if at least 1 allowed prefix is present.
     * @param argMultimap ArgumentMultimap containing all the prefixes and their values.
     * @return True if no prefixes are found.
     */
    private boolean isNoPrefixPresent(ArgumentMultimap argMultimap) {
        return !arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_ID)
                && !arePrefixesPresent(argMultimap, PREFIX_STATS)
                && !arePrefixesPresent(argMultimap, PREFIX_STATSLONG)
                && !arePrefixesPresent(argMultimap, PREFIX_ALL)
                || !argMultimap.getPreamble().isEmpty();
    }

    /**
     * Instantiates the NameContainsKeywordPredicate for view -name command.
     * @param argMultimap ArgumentMultimap containing all the prefixes and their values.
     * @param trimmedArgs String input for all keywords to search for.
     * @return NameContainsKeywordsPredicate with all the keywords to search for.
     * @throws ParseException If the name is of an invalid format.
     */
    private NameContainsKeywordsPredicate getNamePredicate(ArgumentMultimap argMultimap, String trimmedArgs)
            throws ParseException {
        try {
            ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        List<String> keywordList = Arrays.asList(nameKeywords);
        return new NameContainsKeywordsPredicate(keywordList);
    }

    /**
     * Instantiates the IsSameIdPredicate for view -name command.
     * @param argMultimap ArgumentMultimap containing all the prefixes and their values.
     * @return IsSameIdPredicate containing the id to filter for.
     * @throws ParseException If the ID entered is not valid.
     */
    private IsSameIdPredicate getIdPredicate(ArgumentMultimap argMultimap) throws ParseException {
        Id id = null;
        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        return new IsSameIdPredicate(id);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
