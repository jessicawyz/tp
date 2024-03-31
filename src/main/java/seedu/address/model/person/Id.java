package seedu.address.model.person;
/**
 * Represents a Person's id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Ids should only contain numbers";

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
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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
