package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.person.AllExamsList;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Adds an exam to a person's record.
 */
public class AddExamCommand extends Command {
    public static final String COMMAND_WORD = "addexam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exam to a person's record. "
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_EXAM_NAME + "EXAM_NAME "
            + PREFIX_DATE + "EXAM_DATE "
            + "[" + PREFIX_TIME + "EXAM_TIME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "000001 "
            + PREFIX_EXAM_NAME + "Math "
            + PREFIX_DATE + "2024-04-10 "
            + PREFIX_TIME + "14:00";

    public static final String MESSAGE_SUCCESS = "Added exam to person with ID: %1$s";

    private final String uniqueId;
    private final LocalDate examDate;
    private final Optional<LocalTime> examTime;
    private final String examName;

    /**
     * Creates an AddExamCommand to add the specified {@code Exam} to the person with the specified {@code Id}.
     */
    public AddExamCommand(String uniqueId, String examName, LocalDate examDate, Optional<LocalTime> examTime) {
        requireNonNull(uniqueId);
        requireNonNull(examName);
        requireNonNull(examDate);
        requireNonNull(examTime);
        this.uniqueId = uniqueId;
        this.examName = examName;
        this.examDate = examDate;
        this.examTime = examTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToUpdate = model.getPersonByUniqueId(uniqueId);

        if (personToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
        Set<Exam> updatedExams = new HashSet<>();

        if (personToUpdate.getExams() != null) {
            updatedExams.addAll(personToUpdate.getExams());
        }

        String personName = personToUpdate.getName().fullName;
        Id personUniqueId = personToUpdate.getUniqueId();
        Exam newExam = new Exam(examName, examDate, examTime, personName, personUniqueId);

        updatedExams.add(newExam);
        AllExamsList.addExamToList(newExam);

        Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(),
                personToUpdate.getEmail(), personToUpdate.getAddress(), personToUpdate.getTags(),
                personToUpdate.getSubject(), personToUpdate.getUniqueId(), updatedExams, personToUpdate.getPayment());

        model.setPerson(personToUpdate, updatedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, uniqueId));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddExamCommand)) {
            return false;
        }

        AddExamCommand otherCommand = (AddExamCommand) other;
        return uniqueId == otherCommand.uniqueId
                && examName.equals(otherCommand.examName)
                && examDate.equals(otherCommand.examDate)
                && examTime.equals(otherCommand.examTime);
    }
}
