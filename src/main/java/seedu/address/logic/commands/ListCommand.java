package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends ViewCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Viewing all students";
    private static final Logger logger = Logger.getLogger(ListCommand.class.getName());


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.log(Level.INFO, "Executing ListCommand");
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.log(Level.INFO, "ListCommand executed successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
