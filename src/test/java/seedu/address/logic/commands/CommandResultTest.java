package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", null, false,
                false, false, false);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        assertTrue(commandResult.equals(new CommandResult("feedback", null, false, false, false, false)));


        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null, true,
                false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null, false,
                true, false, false)));

        // different showStudentDetails value -> returns true -> only java fx does work
        assertTrue(commandResult.equals(new CommandResult("feedback", null, false,
                false, false, true)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                true, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                false, true, false, false).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        CommandResult commandResult = new CommandResult("feedback", "popupDisplay", true, true, true, true);
        CommandResult commandResultCopy = new CommandResult("feedback", "popupDisplay", true, true, true, true);

        // Same values -> returns true
        assertEquals(commandResult, commandResultCopy);
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        CommandResult commandResult = new CommandResult("feedback", null, false, false, false, false);
        CommandResult differentCommandResult = new CommandResult("different feedback", null, true, true, true, true);

        // Different values -> returns false
        assertNotEquals(commandResult, differentCommandResult);
    }

    @Test
    public void equals_null_returnsFalse() {
        CommandResult commandResult = new CommandResult("feedback");

        // Null -> returns false
        assertFalse(commandResult.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        CommandResult commandResult = new CommandResult("feedback");

        // Different type -> returns false
        assertFalse(commandResult.equals(0.5f));
    }

    @Test
    public void hashCode_test() {
        CommandResult commandResult = new CommandResult("feedback", "popupDisplay", true, false, true, true);
        CommandResult sameValuesCommandResult = new CommandResult("feedback", "popupDisplay", true, false, true, true);

        assertEquals(commandResult.hashCode(), sameValuesCommandResult.hashCode());
    }

}
