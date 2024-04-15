package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends ViewCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Viewing all students";
    private final Logger logger = LogsCenter.getLogger(ListCommand.class);


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Executing ListCommand");
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.info("ListCommand executed successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
