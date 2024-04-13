package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class DeleteExamCommandParserTest {

    private DeleteExamCommandParser deleteExamParser = new DeleteExamCommandParser();

    @Test
    public void parse_allCompulsoryFieldsPresent_success() throws ParseException {
        // Complete valid input
        String userInput = " -id 1 -exam Maths -date 2025-04-13";
        DeleteExamCommand expectedCommand = new DeleteExamCommand("1", "Maths",
                LocalDate.of(2025, 4, 13), Optional.empty());
        assertEquals(expectedCommand, deleteExamParser.parse(userInput));
    }

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        // Complete valid input
        String userInput = " -id 1 -exam Maths -date 2025-04-13 -time 14:00";
        DeleteExamCommand expectedCommand = new DeleteExamCommand("1", "Maths",
                LocalDate.of(2025, 4, 13), Optional.of(LocalTime.of(14, 0)));
        assertEquals(expectedCommand, deleteExamParser.parse(userInput));
    }
    @Test
    public void parse_compulsoryFieldMissing_throwsParseException() {
        // Missing ID
        final String userInputNoId = " -examname Maths -date 2023-04-13 -time 14:00";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInputNoId));

        // Missing examName
        final String userInputNoExam = " -id 123 -date 2023-04-13 -time 14:00";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInputNoExam));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // Invalid date format
        final String userInputWrongDate = " -id 123 -examName Maths -date 04-13-2023 -time 14:00";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInputWrongDate));

        // Invalid time format
        final String userInputWrongTime = " -id 123 -examname Maths -date 2023-04-13 -time 1400";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInputWrongTime));
    }

    @Test
    public void parse_extraArguments_throwsParseException() {
        // Extra unparsed arguments
        String userInput = " -id 123 -examname Maths -date 2023-04-13 -time 14:00 -extra unused";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInput));
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        // Empty string input
        String userInput = " ";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInput));
    }

    @Test
    public void parse_onlyIdProvided_throwsParseException() {
        // Only ID provided
        String userInput = " -id 123";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInput));
    }

    @Test
    public void parse_noPreambleAllowed_throwsParseException() {
        // No preamble allowed
        String userInput = "Some text -id 123 -examname Maths -date 2023-04-13";
        assertThrows(ParseException.class, () -> deleteExamParser.parse(userInput));
    }
}
