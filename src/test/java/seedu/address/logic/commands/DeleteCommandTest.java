package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;

public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(new Id("invalid_id"));

        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, ID_FIRST_PERSON.getInt());
        DeleteCommand deleteCommand = new DeleteCommand(new Id("invalid_id"));

        int outOfBoundIndex = ID_SECOND_PERSON.getInt();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex < model.getAddressBook().getPersonList().size());
        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_validIdPersonDoesNotExist_throwsCommandException() {
        Id nonExistentId = new Id("non_existent_id");
        DeleteCommand deleteCommand = new DeleteCommand(nonExistentId);
        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteCommand(null));
    }

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        Model emptyModel = new ModelManager();
        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_PERSON);

        assertCommandFailure(deleteCommand, emptyModel, DeleteCommand.MESSAGE_PERSON_NOT_FOUND);
    }


    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ID_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ID_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        int targetIndex = 1;
        DeleteCommand deleteCommand = new DeleteCommand(new Id(targetIndex));
        String expected = DeleteCommand.class.getCanonicalName() + "{targetUniqueId=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
