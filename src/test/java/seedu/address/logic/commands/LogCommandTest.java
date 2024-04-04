package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class LogCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    private String getCurrentDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    @Test
    public void execute_logSession_success() throws Exception {
        Person personToLog = new PersonBuilder().withUniqueId("1").build();
        model.addPerson(personToLog);

        String date = getCurrentDateString();
        Log log = new Log("Visual", "2", "English Comprehension", "Great improvement!", date);
        LogCommand logCommand = new LogCommand(Integer.parseInt(personToLog.getUniqueId().toString()), log);

        CommandResult commandResult = logCommand.execute(model);
        assertEquals(String.format(LogCommand.MESSAGE_SUCCESS, log.toString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidPersonId_throwsCommandException() {
        int invalidId = 99999;
        Log log = new Log("Visual", "2", "English Comprehension", "Great improvement!", "2020-01-01 10:00:00");
        LogCommand logCommand = new LogCommand(invalidId, log);

        assertThrows(CommandException.class, () -> logCommand.execute(model));
        try {
            logCommand.execute(model);
        } catch (CommandException e) {
            assertTrue(e.getMessage().contains(LogCommand.MESSAGE_NO_SUCH_PERSON));
        }
    }

    @Test
    public void execute_negativePersonId_throwsCommandException() {
        int negativeId = -1;
        Log log = new Log("Visual", "2", "English Comprehension", "Great improvement!", "2020-01-01 10:00:00");
        LogCommand logCommand = new LogCommand(negativeId, log);

        assertThrows(CommandException.class, () -> logCommand.execute(model));
        try {
            logCommand.execute(model);
        } catch (CommandException e) {
            assertTrue(e.getMessage().contains(LogCommand.MESSAGE_POSITIVE_INTEGER_AND_ZERO));
        }
    }

    @Test
    public void equals() {
        int targetId = 1;
        String date = getCurrentDateString();
        Log log1 = new Log("Visual", "2", "English Comprehension", "Great improvement!", date);
        Log log2 = new Log("Auditory", "3", "Math Solving", "Needs improvement", date);

        LogCommand logCommand1 = new LogCommand(targetId, log1);
        LogCommand logCommand2 = new LogCommand(targetId, log1);
        LogCommand logCommand3 = new LogCommand(targetId, log2);

        // same object -> returns true
        assertEquals(logCommand1, logCommand1);

        // same values -> returns true
        assertEquals(logCommand1, logCommand2);

        // different types -> returns false
        assertFalse(logCommand1.equals(1));

        // null -> returns false
        assertFalse(logCommand1.equals(null));

        // different log details -> returns false
        assertFalse(logCommand1.equals(logCommand3));
    }
}
