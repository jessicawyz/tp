package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Ids should only contain numbers and should have the value"
            + "between the range of 1 and 999999 (both inclusive)";

    public static final String VALIDATION_REGEX = "[#]";

    /**
     * id is current stored with #000000 format
     */
    public final String id;

    public Id(String id) {
        this.id = id;
    }

    public Id(int id) {
        this.id = Integer.toString(id);
    }
    /**
     * Checks if the given id is a valid Id.
     * @param test Input id to validate.
     * @return Result of validation.
     */
    public static boolean isValidId(String test) {
        try {
            int tempTest = Integer.parseInt(test);
            isBetweenRange(tempTest);
        } catch (IllegalValueException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;

        }
        return true;
    }

    private static void isBetweenRange(int testValue) throws IllegalValueException {
        if (testValue < 1 || testValue > 999999) {
            throw new IllegalValueException("If you're seeing this error means you've"
                + "edited the addressbook.json and id should be between 1 and 999999");
        }
    }
    public int getInt() {
        int results;
        if (this.id.matches(VALIDATION_REGEX)) {
            String temp = this.id.substring(1);
            results = Integer.parseInt(temp);
        } else {
            results = Integer.parseInt(this.id);
        }
        return results;
    }


    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Id) {
            Id otherId = (Id) other;
            if (isValidId(otherId.id) && isValidId(id)) {
                return Integer.parseInt(otherId.id) == Integer.parseInt(id);
            } else {
                return otherId.id.equals(id);
            }
        } else {
            return false;
        }
    }

}