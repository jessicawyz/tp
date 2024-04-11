package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddExamCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_examAcceptedByModel_addSuccessful() throws Exception {
        Person personToAddExam = new PersonBuilder().withUniqueId("000001").build();
        model.addPerson(personToAddExam);
        Id idOfPersonToAddExam = personToAddExam.getUniqueId();

        String examName = "Math";
        LocalDate examDate = LocalDate.of(2024, 5, 10);
        LocalTime examTime = LocalTime.of(14, 0);

        AddExamCommand addExamCommand = new AddExamCommand(idOfPersonToAddExam.toString(), examName, examDate,
                Optional.of(examTime));

        Exam expectedExam = new Exam(examName, examDate, Optional.of(examTime), personToAddExam.getName().fullName,
                idOfPersonToAddExam);
        String expectedMessage = String.format(AddExamCommand.MESSAGE_SUCCESS, idOfPersonToAddExam);

        CommandResult commandResult = addExamCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedExam, model.getPersonByUniqueId(idOfPersonToAddExam.toString()).getExams()
                .iterator().next());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Id invalidId = new Id("nonExistentId");
        AddExamCommand addExamCommand = new AddExamCommand(invalidId.toString(), "Math",
                LocalDate.of(2024, 4, 10), Optional.of(LocalTime.of(14, 0)));

        assertThrows(CommandException.class,
                Messages.MESSAGE_PERSON_NOT_FOUND, () -> addExamCommand.execute(model));
    }

    @Test
    public void equals() {
        Id idOfPerson = new Id("000001");
        String examName = "Math";
        LocalDate examDate = LocalDate.of(2024, 4, 10);
        LocalTime examTime = LocalTime.of(14, 0);

        AddExamCommand addExamToPersonCommand = new AddExamCommand(idOfPerson.toString(), examName, examDate,
                Optional.of(examTime));

        // same object -> returns true
        assertEquals(addExamToPersonCommand, addExamToPersonCommand);

        // same values -> returns true
        AddExamCommand addExamToPersonCommandCopy = new AddExamCommand(idOfPerson.toString(), examName, examDate,
                Optional.of(examTime));
        assertEquals(addExamToPersonCommand, addExamToPersonCommandCopy);

        // different types -> returns false
        assertFalse(addExamToPersonCommand.equals(1));

        // null -> returns false
        assertFalse(addExamToPersonCommand.equals(null));

        // different exam name -> returns false
        AddExamCommand addDifferentExamToPersonCommand = new AddExamCommand(idOfPerson.toString(), "Science",
                examDate, Optional.of(examTime));
        assertFalse(addExamToPersonCommand.equals(addDifferentExamToPersonCommand));
    }
}
