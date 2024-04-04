package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.parseDate;
import static seedu.address.logic.parser.ParserUtil.parsePayment;
import static seedu.address.logic.parser.ParserUtil.parseTime;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;


public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseDate_validDate_returnsLocalDate() throws Exception {
        LocalDate expectedDate = LocalDate.of(2024, 1, 1);
        assertEquals(expectedDate, parseDate("2024-01-01"));
    }

    @Test
    public void parseDate_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> parseDate("2024-13-01"));
    }

    @Test
    public void parseTime_validTime_returnsOptionalLocalTime() throws Exception {
        Optional<LocalTime> expectedTime = Optional.of(LocalTime.of(14, 30));
        assertEquals(expectedTime, parseTime("14:30"));
    }

    @Test
    public void parseTime_invalidTime_throwsParseException() {
        assertThrows(ParseException.class, () -> parseTime("25:30"));
    }

    @Test
    public void parseTime_emptyString_returnsEmptyOptional() throws Exception {
        assertEquals(Optional.empty(), parseTime(""));
    }

    @Test
    public void parseExamName_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExamName(""));
    }

    @Test
    public void parseDate_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("2023/12/31"));
    }

    @Test
    public void parseTime_nonExistingTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("25:00"));
    }

    @Test
    public void parseTimeFromStorage_validTimeWithSeconds_returnsTime() throws Exception {
        assertEquals(Optional.of(LocalTime.of(14, 30)), ParserUtil.parseTimeFromStorage("14:30:00"));
    }

    @Test
    public void parseTimeFromStorage_nullTime_returnsEmpty() throws Exception {
        assertEquals(Optional.empty(), ParserUtil.parseTimeFromStorage("null"));
    }

    @Test
    public void parsePayment_validPayment_returnsPayment() throws Exception {
        Payment expectedPayment = new Payment(100.00);
        assertEquals(expectedPayment, parsePayment(Optional.of("100.00")));
    }

    @Test
    public void parsePayment_invalidPayment_throwsParseException() {
        assertThrows(ParseException.class, () -> parsePayment(Optional.of("abc")));
    }

    @Test
    public void parsePayment_emptyOptional_returnsDefaultPayment() throws Exception {
        Payment expectedPayment = new Payment(0.0);
        assertEquals(expectedPayment, parsePayment(Optional.empty()));
    }

    @Test
    public void parsePayment_negativeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePayment(Optional.of("-100")));
    }

    @Test
    public void parsePayment_nonNumericValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePayment(Optional.of("abc")));
    }

    @Test
    public void parseSubject_whitespace_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject(" "));
    }

    @Test
    public void parseSubject_validSubjectWithWhitespace_returnsSubject() throws Exception {
        Subject expectedSubject = new Subject("Mathematics");
        assertEquals(expectedSubject, ParserUtil.parseSubject(" Mathematics "));
    }

    @Test
    public void parseId_validIdNoLeadingZeros_success() throws ParseException {
        String validId = "123";
        Id expectedId = new Id("123");
        assertEquals(expectedId, ParserUtil.parseId(validId));
    }

    @Test
    public void parseId_validIdWithLeadingZeros_success() throws ParseException {
        String validId = "00123";
        Id expectedId = new Id("00123");
        assertEquals(expectedId, ParserUtil.parseId(validId));
    }

    @Test
    public void parseId_emptyString_throwsParseException() {
        String emptyId = "";
        assertThrows(ParseException.class, () -> ParserUtil.parseId(emptyId));
    }

    @Test
    public void parseId_whitespaceOnly_throwsParseException() {
        String whitespaceId = "    ";
        assertThrows(ParseException.class, () -> ParserUtil.parseId(whitespaceId));
    }

    @Test
    public void parseId_nonNumeric_throwsParseException() {
        String nonNumericId = "a123";
        assertThrows(ParseException.class, () -> ParserUtil.parseId(nonNumericId));
    }

    @Test
    public void parseId_containsSpecialCharacters_throwsParseException() {
        String specialCharId = "123#";
        assertThrows(ParseException.class, () -> ParserUtil.parseId(specialCharId));
    }

    @Test
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId(null));
    }


}
