package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AllExamsList;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Person;

/**
 * Deletes an exam from a person's record.
 */
public class DeleteExamCommand extends Command {
    public static final String COMMAND_WORD = "deleteexam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an exam from a person's record. "
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

    public static final String MESSAGE_SUCCESS = "Deleted exam from person with ID: %1$s";

    private final String uniqueId;
    private final LocalDate examDate;
    private final Optional<LocalTime> examTime;
    private final String examName;

    /**
     * Creates a DeleteExamCommand to delete the specified {@code Exam} from the person with the specified {@code Id}.
     */
    public DeleteExamCommand(String uniqueId, String examName, LocalDate examDate, Optional<LocalTime> examTime) {
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

        Person personToUpdate = getPersonToUpdate(model);

        Set<Exam> updatedExams = getUpdatedExams(personToUpdate);

        Exam examToDelete = findExamToDelete(updatedExams);
        removeExamIfFound(updatedExams, examToDelete);

        updateModelWithUpdatedPerson(model, personToUpdate, updatedExams);

        return new CommandResult(String.format(MESSAGE_SUCCESS, uniqueId));
    }

    /**
     * Retrieves the person to be updated from the provided model based on the unique identifier.
     * Throws a CommandException if the person is not found in the model.
     *
     * @param model The model containing the persons.
     * @return The person to be updated.
     * @throws CommandException If the person is not found in the model.
     */
    private Person getPersonToUpdate(Model model) throws CommandException {
        Person personToUpdate = model.getPersonByUniqueId(uniqueId);
        if (personToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
        return personToUpdate;
    }

    /**
     * Creates a new set containing the exams of the specified person.
     *
     * @param personToUpdate The person whose exams are to be copied.
     * @return A new set containing the exams of the person.
     */
    private Set<Exam> getUpdatedExams(Person personToUpdate) {
        return new HashSet<>(personToUpdate.getExams());
    }

    /**
     * Finds and returns the exam to be deleted from the provided set of exams.
     * Throws a CommandException if the exam with the specified details is not found.
     *
     * @param updatedExams The set of exams to search for the exam to delete.
     * @return The exam to delete.
     * @throws CommandException If the exam to delete is not found in the set of exams.
     */
    private Exam findExamToDelete(Set<Exam> updatedExams) throws CommandException {
        for (Exam exam : updatedExams) {
            if (exam.getExamName().equals(examName)
                    && exam.getExamDate().equals(examDate)
                    && exam.getExamTime().equals(examTime)) {
                return exam;
            }
        }
        throw new CommandException(Messages.MESSAGE_EXAM_NOT_FOUND);
    }

    /**
     * Removes the specified exam from the provided set of updated exams.
     * Additionally, removes the exam from the global list of all exams.
     *
     * @param updatedExams  The set of updated exams from which the exam will be removed.
     * @param examToDelete The exam to remove from the set of updated exams and the global list of all exams.
     */
    private void removeExamIfFound(Set<Exam> updatedExams, Exam examToDelete) {
        updatedExams.remove(examToDelete);
        AllExamsList.deleteExamFromList(examToDelete);
    }

    /**
     * Updates the provided model with the updated person object containing the updated set of exams.
     * The original person in the model is replaced with the updated person.
     *
     * @param model          The model to update.
     * @param originalPerson The original person object to be updated.
     * @param updatedExams   The updated set of exams for the person.
     */
    private void updateModelWithUpdatedPerson(Model model, Person originalPerson, Set<Exam> updatedExams) {
        Person updatedPerson = new Person(originalPerson.getName(), originalPerson.getPhone(),
                originalPerson.getEmail(), originalPerson.getAddress(), originalPerson.getTags(),
                originalPerson.getSubject(), originalPerson.getUniqueId(), updatedExams,
                originalPerson.getPayment(), originalPerson.getLogs());

        model.setPerson(originalPerson, updatedPerson);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof DeleteExamCommand)) {
            return false;
        }

        DeleteExamCommand otherCommand = (DeleteExamCommand) other;
        return uniqueId.equals(otherCommand.uniqueId)
                && examName.equals(otherCommand.examName)
                && examDate.equals(otherCommand.examDate)
                && examTime.equals(otherCommand.examTime);
    }
}

