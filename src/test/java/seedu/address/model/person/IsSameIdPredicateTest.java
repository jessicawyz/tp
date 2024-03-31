package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IsSameIdPredicateTest {

    @Test
    public void test_sameId_returnsTrue() {
        Person person = new PersonBuilder().withUniqueId("123").build();
        IsSameIdPredicate predicate = new IsSameIdPredicate(new Id("123"));

        assertTrue(predicate.test(person));
    }

    @Test
    public void test_differentId_returnsFalse() {
        Person person = new PersonBuilder().withUniqueId("123").build();
        IsSameIdPredicate predicate = new IsSameIdPredicate(new Id("456"));

        assertFalse(predicate.test(person));
    }

    @Test
    public void test_invalidIdFormat_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> new IsSameIdPredicate(new Id("invalid")));
    }
}
