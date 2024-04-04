package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.IsSameIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_viewByName_success() {
        String keyword = "Alice";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList(keyword));
        ViewCommand command = new ViewCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model,
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, expectedModel.getFilteredPersonList().size()),
                expectedModel);
    }

    @Test
    public void execute_viewById_success() {
        Id id = new Id("000004");
        IsSameIdPredicate predicate = new IsSameIdPredicate(id);
        ViewCommand command = new ViewCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model,
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, expectedModel.getFilteredPersonList().size()),
                expectedModel);
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertEquals(viewFirstCommand, viewFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(viewFirstCommand, 1);

        // null -> returns false
        assertNotNull(viewFirstCommand);

        // different predicate -> returns false
        assertNotEquals(viewFirstCommand, viewSecondCommand);
    }
}
