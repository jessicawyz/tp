package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {


    public static final Person ALICE = new PersonBuilder()
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withSubject("English")
            .withTags("friends")
            .withUniqueId("000001")
            .withPayment("0.0")
            .withExams(
                    new Exam("English Exam", LocalDate.of(2024, 10, 30), Optional.of(LocalTime.of(9, 0))),
                    new Exam("Math Exam", LocalDate.of(2024, 11, 15), Optional.empty())
            )
            .withLogs(
                    new Log("Meeting scheduled", "2", "English concepts", "Prepare for exam", null),
                    new Log("Email received", "1.5", "Math problems", "Study for test", null)
            )
            .build();

    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withSubject(VALID_SUBJECT_AMY)
            .withUniqueId("000002")
            .withPayment(VALID_PAYMENT_AMY)
            .withTags("friends")
            .withExams(
                    new Exam("Math Exam", LocalDate.of(2024, 12, 1), Optional.of(LocalTime.of(10, 30)))
            )
            .withLogs(
                    new Log("Study group", "3", "Algebra", "Prepare for exam", null),
                    new Log("Online lecture", "2", "Geometry", "Review concepts", null)
            )
            .build();

    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends")
            .withSubject("Math")
            .withUniqueId("000003")
            .withPayment("0.0")
            .withExams(
                    new Exam("Math Exam", LocalDate.of(2024, 12, 1), Optional.of(LocalTime.of(10, 30)))
            )
            .withLogs(
                    new Log("Study group", "3", "Algebra", "Prepare for exam", null),
                    new Log("Online lecture", "2", "Geometry", "Review concepts", null)
            )
            .build();

    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withSubject(VALID_SUBJECT_BOB)
            .withUniqueId("000004")
            .withPayment(VALID_PAYMENT_BOB)
            .withTags("friends")
            .withExams(
                    new Exam("Chemistry Exam", LocalDate.of(2024, 11, 25), Optional.of(LocalTime.of(10, 0))),
                    new Exam("Physics Exam", LocalDate.of(2024, 12, 10), Optional.empty())
            )
            .withLogs(
                    new Log("Study session", "2", "Chemical Reactions", "Review concepts", null),
                    new Log("Online lecture", "1.5", "Thermodynamics", "Watch video", null)
            )
            .build();

    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withSubject("Science")
            .withUniqueId("000005")
            .withPayment("0.0")
            .withExams(
                    new Exam("Biology Exam", LocalDate.of(2024, 11, 20), Optional.of(LocalTime.of(9, 0))),
                    new Exam("Chemistry Exam", LocalDate.of(2024, 12, 5), Optional.empty())
            )
            .withLogs(
                    new Log("Study group", "2.5", "Genetics", "Discuss topics", null),
                    new Log("Practice questions", "1", "Cell Biology", "Solve problems", null)
            )
            .build();

    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withSubject("History")
            .withUniqueId("000006")
            .withPayment("0.0")
            .withExams(
                    new Exam("History Exam", LocalDate.of(2024, 11, 18), Optional.of(LocalTime.of(13, 30))),
                    new Exam("Geography Exam", LocalDate.of(2024, 12, 8), Optional.empty())
            )
            .build();

    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withSubject("Geography")
            .withUniqueId("000007")
            .withPayment("0.0")
            .withLogs(
                    new Log("Group discussion", "2", "Shakespearean plays", "Prepare for exam", null),
                    new Log("Essay writing", "1.5", "Modernist literature", "Write draft", null)
            )
            .build();

    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withSubject("Art")
            .withUniqueId("000008")
            .withPayment("0.0")
            .build();

    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withSubject("Physics")
            .withUniqueId("000009")
            .withPayment("0.0")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withSubject("Chemistry")
            .withUniqueId("000013")
            .withPayment("0.0")
            .withTags("friends")
            .build();

    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withSubject("Biology")
            .withUniqueId("000011")
            .withPayment("0.0")
            .withTags("friends")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private static int uniqueIdCounter = 1;

    private TypicalPersons() {} // prevents instantiation

    //    private static String getNextUniqueId() {
    //        return String.format("#%06d", uniqueIdCounter++);
    //    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, AMY, BENSON, BOB, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
