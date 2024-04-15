package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.IsSameIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Viewing the stats of students";
    public static final String MESSAGE_MULTIPREFIX = "Please only use one prefix in your command!";

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views student related details\n"
            + "Prefixes available: -all, -name, -id, -stats\n"
            + "Note that field after -id must be a positive integer "
            + "and should not exceed 6 digits excluding leading 0s\n"
            + "Example: " + COMMAND_WORD + " -name John OR "
            + COMMAND_WORD + " -id 12345 OR "
            + COMMAND_WORD + " -all OR "
            + COMMAND_WORD + " -stats";

    private final Logger logger = LogsCenter.getLogger(ViewCommand.class);
    private final NameContainsKeywordsPredicate namePredicate;
    private final IsSameIdPredicate idPredicate;

    /**
     * Constructor for a non search based view command (stats or all)
     */
    public ViewCommand() {
        this.namePredicate = null;
        this.idPredicate = null;
    }

    /**
     * Constructor for a view by name command.
     * @param predicate Name to search for in list.
     */
    public ViewCommand(NameContainsKeywordsPredicate predicate) {
        this.namePredicate = predicate;
        this.idPredicate = null;
    }

    /**
     * Constructor for a view by id command.
     * @param id Id to search for in list.
     */
    public ViewCommand(IsSameIdPredicate id) {
        this.namePredicate = null;
        this.idPredicate = id;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (namePredicate != null) {
            generateFilteredNameList(model);
            if (model.getFilteredPersonList().size() == 0) {
                logger.info("No person with name found");
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_FOUND);
            }
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        } else if (idPredicate != null) {
            generateFilteredIdList(model);
            int filteredListSize = model.getFilteredPersonList().size();
            if (filteredListSize == 0) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_FOUND);
            } else {
                Person person = model.getFilteredPersonList().get(0);
                return new CommandResult(
                        String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                        person.getLogs().toString(),
                        false, false, false, true);
            }
        } else {
            // Should not reach here
            assert false;
            return null;
        }
    }

    private void generateFilteredNameList(Model model) {
        assert idPredicate == null : "only 1 prefix should be present";
        logger.info("Started executing view name command");
        model.updateFilteredPersonList(namePredicate);
    }

    private void generateFilteredIdList(Model model) {
        assert namePredicate == null : "only 1 prefix should be present";
        logger.info("Started executing view id command");
        if (!model.hasPersonById(idPredicate.getTestId())) {
            logger.info("No person with id found");
        } else if (model.hasPersonById(idPredicate.getTestId())) {
            logger.info("Found a student with id");
            model.updateFilteredPersonList(idPredicate);
            assert model.getFilteredPersonList().size() == 1 : "There should only be 1 student with the matching id";
        } else {
            // Should not reach here
            assert false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return namePredicate.equals(otherViewCommand.namePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .toString();
    }
}
