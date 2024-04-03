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
import static seedu.address.testutil.TypicalExams.ART_PROJECT_FIONA;
import static seedu.address.testutil.TypicalExams.BIOLOGY_FINAL_IDA;
import static seedu.address.testutil.TypicalExams.CHEMISTRY_TEST_HOON;
import static seedu.address.testutil.TypicalExams.CHINESE_EXAM_AMY;
import static seedu.address.testutil.TypicalExams.ENGLISH_EXAM_BENSON;
import static seedu.address.testutil.TypicalExams.ENGLISH_QUIZ_DANIEL;
import static seedu.address.testutil.TypicalExams.GEOGRAPHY_EXAM_ELLE;
import static seedu.address.testutil.TypicalExams.HISTORY_EXAM_CARL;
import static seedu.address.testutil.TypicalExams.MATH_EXAM_ALICE;
import static seedu.address.testutil.TypicalExams.PHYSICS_LAB_GEORGE;
import static seedu.address.testutil.TypicalExams.SCIENCE_EXAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
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
            .withExams(new HashSet<>(Set.of(MATH_EXAM_ALICE)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withSubject(VALID_SUBJECT_AMY)
            .withUniqueId("000002")
            .withExams(new HashSet<>(Set.of(CHINESE_EXAM_AMY)))
            .withPayment(VALID_PAYMENT_AMY)
            .withTags("friends")
            .withEmptyLogs()
            .build();

    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends")
            .withSubject("Math")
            .withUniqueId("000003")
            .withExams(new HashSet<>(Set.of(ENGLISH_EXAM_BENSON)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withSubject(VALID_SUBJECT_BOB)
            .withUniqueId("000004")
            .withExams(new HashSet<>(Set.of(SCIENCE_EXAM_BOB)))
            .withPayment(VALID_PAYMENT_BOB)
            .withTags("friends")
            .withEmptyLogs()
            .build();

    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withSubject("Science")
            .withUniqueId("000005")
            .withExams(new HashSet<>(Set.of(HISTORY_EXAM_CARL)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withSubject("History")
            .withUniqueId("000006")
            .withExams(new HashSet<>(Set.of(ENGLISH_QUIZ_DANIEL)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withSubject("Geography")
            .withUniqueId("000007")
            .withExams(new HashSet<>(Set.of(GEOGRAPHY_EXAM_ELLE)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withSubject("Art")
            .withUniqueId("000008")
            .withExams(new HashSet<>(Set.of(ART_PROJECT_FIONA)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withSubject("Physics")
            .withUniqueId("000009")
            .withExams(new HashSet<>(Set.of(PHYSICS_LAB_GEORGE)))
            .withPayment("0.0")
            .withEmptyLogs()
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withSubject("Chemistry")
            .withUniqueId("000013")
            .withExams(new HashSet<>(Set.of(CHEMISTRY_TEST_HOON)))
            .withPayment("0.0")
            .withTags("friends")
            .withEmptyLogs()
            .build();

    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withSubject("Biology")
            .withUniqueId("000011")
            .withExams(new HashSet<>(Set.of(BIOLOGY_FINAL_IDA)))
            .withPayment("0.0")
            .withTags("friends")
            .withEmptyLogs()
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
