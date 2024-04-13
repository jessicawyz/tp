package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LogCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Log;

public class LogCommandParserTest {
    private LogCommandParser parser = new LogCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        // Complete valid input using correct prefixes
        String userInput = " -id 1 -hours 2 -notes Meeting -content Discussed project -style Informal";
        Log expectedLog = new Log("Informal", "2", "Discussed project", "Meeting", null);
        LogCommand expectedCommand = new LogCommand(1, expectedLog);
        assertEquals(expectedCommand, parser.parse(userInput));
    }

    @Test
    public void parse_optionalFieldsMissing_throwsParseException() {
        // Missing style (considered essential based on parse method logic)
        String userInput = " -id 1 -hours 2 -notes Meeting -content Discussed project";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_compulsoryFieldMissing_throwsParseException() {
        // Missing ID
        final String userInput = " -hours 2 -notes Meeting -content Discussed project -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

        // Missing hours
        final String userInput2 = " -id 1 -notes Meeting -content Discussed project -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput2));
    }

    @Test
    public void parse_invalidValues_throwsParseException() {
        // Invalid ID (non-integer)
        final String userInput = " -id one -hours 2 -notes Meeting -content Discussed project -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

        // Missing content
        final String userInput2 = " -id 1 -hours 2 -notes Meeting -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput2));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        // Unwanted preamble text
        final String userInput = "random text -id 1 -hours 2 -notes Meeting -content Discussed project -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_allFieldsPresentButDuplicated_throwsParseException() {
        // Duplicated ID field
        final String userInput = " -id 1 -id 2 -hours 2 -notes Meeting -content Discussed project -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    // Additional test to ensure no empty fields allowed
    @Test
    public void parse_emptyFields_throwsParseException() {
        final String userInput = " -id 1 -hours-notes Meeting -content Discussed project -style Informal";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
