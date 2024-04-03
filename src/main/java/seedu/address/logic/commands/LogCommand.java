package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STYLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;


/**
 * Logs a session for a student.
 */
public class LogCommand extends Command {
    public static final String COMMAND_WORD = "log";
    public static final String MESSAGE_SUCCESS = "Student logged successfully";
    public static final String MESSAGE_NO_SUCH_PERSON = "No student with this id exists!";
    public static final String MESSAGE_POSITIVE_INTEGER_AND_ZERO =
            "The unique ID must be a positive integer and/or zero";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Logs a session for a student. "
            + "Parameters: "
            + PREFIX_ID + " ID "
            + PREFIX_HOURS + " HOURS "
            + PREFIX_CONTENT + " CONTENT "
            + PREFIX_STYLE + " LEARNING STYLE "
            + PREFIX_NOTES + " NOTES \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + " 1 "
            + PREFIX_HOURS + " 2 "
            + PREFIX_CONTENT + " English Comprehension "
            + PREFIX_STYLE + " Visual "
            + PREFIX_NOTES + " Great improvement!";

    private final int targetId;
    private final Log logDetails;

    /**
     * Creates a LogCommand to log the specified {@code Log} to the person with the specified {@code Id}.
     */
    public LogCommand(int targetId, Log log) {
        requireAllNonNull(log, targetId);
        this.targetId = targetId;
        this.logDetails = log;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetId < 0) { // Positive Integer or 0, to discuss
            throw new CommandException(MESSAGE_POSITIVE_INTEGER_AND_ZERO);
        }

        Person targetPerson = model.getPersonByUniqueId(String.valueOf(targetId));

        if (targetPerson == null) {
            throw new CommandException(MESSAGE_NO_SUCH_PERSON);
        }

        model.addLog(targetPerson, logDetails);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(logDetails)));
    }
}
