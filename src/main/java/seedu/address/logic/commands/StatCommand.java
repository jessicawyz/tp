package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class StatCommand extends ViewCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "-stats" + ": shows the summary stats"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing the Summary stats of students";

    private static final Logger logger = LogsCenter.getLogger(StatCommand.class);


    /**
     * Constructor for a non search based view command (stats or all)
     */
    public StatCommand() {
        logger.info("Stat Command is Constructed");
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, null, false, false, true, false);
    }


    //very strict equal method
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatCommand)) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
