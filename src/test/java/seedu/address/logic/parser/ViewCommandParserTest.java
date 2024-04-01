package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.StatCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_noArguments_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" -unknown"));
    }

    @Test
    public void parse_allPrefix_returnsListCommand() throws ParseException {
        assertTrue(parser.parse(" -all ") instanceof ListCommand);
    }

    @Test
    public void parse_statisticsPrefix_returnsStatCommand() throws ParseException {
        assertTrue(parser.parse(" -statistics ") instanceof StatCommand);
    }

    @Test
    public void parse_statsPrefix_returnsStatCommand() throws ParseException {
        assertTrue(parser.parse(" -stats ") instanceof StatCommand);
    }

    @Test
    public void parse_namePrefixValidName_returnsViewCommand() throws ParseException {
        assertTrue(parser.parse(" -name John Doe ") instanceof ViewCommand);
    }

    @Test
    public void parse_idPrefixValidId_returnsViewCommand() throws ParseException {
        assertTrue(parser.parse(" -id 123 ") instanceof ViewCommand);
    }

    @Test
    public void parse_extraArgumentsWithoutPrefixes_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" John Doe "));
    }

    @Test
    public void parse_multipleValidPrefixes_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" -name John Doe -id 123 "));
    }

    @Test
    public void parse_namePrefixInvalidName_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" -name "));
    }

    @Test
    public void parse_idPrefixInvalidId_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" -id "));
    }

    @Test
    public void parse_idPrefixInvalidIdFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" -id abc "));
    }

    @Test
    public void parse_moreThanTwoPrefixes_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" -name John Doe -id 123 -stats "));
    }

}
