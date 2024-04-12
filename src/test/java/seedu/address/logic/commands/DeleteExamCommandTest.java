package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DeleteExamCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_examDeletedSuccessfully() throws CommandException {
        Person person = new PersonBuilder().withUniqueId("000001").build();
        model.addPerson(person);

        String examName = "Math";
        LocalDate examDate = LocalDate.of(2024, 5, 10);
        Optional<LocalTime> examTime = Optional.of(LocalTime.of(14, 0));
        Exam exam = new Exam(examName, examDate, examTime, person.getName().fullName, person.getUniqueId());

        Set<Exam> newExams = new HashSet<>(person.getExams());
        newExams.add(exam);
        person.setExams(newExams);

        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(person.getUniqueId().toString(), examName,
                examDate, examTime);

        CommandResult commandResult = deleteExamCommand.execute(model);
        assertEquals(String.format(DeleteExamCommand.MESSAGE_SUCCESS, person.getUniqueId()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_examNotFound_throwsCommandException() {
        Person person = new PersonBuilder().withUniqueId("000002").build();
        model.addPerson(person);

        String examName = "Science";
        LocalDate examDate = LocalDate.of(2024, 5, 15);
        Optional<LocalTime> examTime = Optional.of(LocalTime.of(10, 30));

        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(person.getUniqueId().toString(), examName,
                examDate, examTime);

        assertThrows(CommandException.class,
                Messages.MESSAGE_EXAM_NOT_FOUND, () -> deleteExamCommand.execute(model));
    }

    @Test
    public void equals() {
        String uniqueId = "000003";
        String examName = "History";
        LocalDate examDate = LocalDate.of(2024, 6, 20);
        Optional<LocalTime> examTime = Optional.of(LocalTime.of(9, 0));

        DeleteExamCommand deleteExamCommand1 = new DeleteExamCommand(uniqueId, examName, examDate, examTime);
        DeleteExamCommand deleteExamCommand2 = new DeleteExamCommand(uniqueId, examName, examDate, examTime);

        assertEquals(deleteExamCommand1, deleteExamCommand2);

        DeleteExamCommand deleteExamCommand3 = new DeleteExamCommand(uniqueId, "Geography", examDate, examTime);
        assertFalse(deleteExamCommand1.equals(deleteExamCommand3));
    }
}
