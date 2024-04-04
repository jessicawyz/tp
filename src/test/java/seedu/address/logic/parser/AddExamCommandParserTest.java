package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class AddExamCommandParserTest {

    private AddExamCommandParser parser = new AddExamCommandParser();

    //    @Test
    //    public void parse_validArgs_returnsAddExamCommand() throws Exception {
    //        String uniqueId = "000001";
    //        String examName = "Math";
    //        String date = "2024-04-10";
    //        String time = "14:00";
    //        String args = String.format("-id %s -exam %s -date %s -time %s", uniqueId, examName, date, time);
    //
    //        AddExamCommandParser parser = new AddExamCommandParser();
    //        AddExamCommand command = parser.parse(args);
    //
    //        LocalDate expectedDate = LocalDate.parse(date);
    //        Optional<LocalTime> expectedTime = Optional.of(LocalTime.parse(time));
    //        AddExamCommand expectedCommand = new AddExamCommand(uniqueId, examName, expectedDate, expectedTime);
    //        assertEquals(expectedCommand, command);
    //    }


    @Test
    public void parse_missingRequiredPrefix_throwsParseException() {
        String uniqueId = "000001";
        String examName = "Math";
        String date = "2024-04-10";
        String args = String.format("-id %s -examname %s -date %s", uniqueId, examName, date);

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        String uniqueId = "000001";
        String examName = "Math";
        String date = "10-04-2024";
        String args = String.format("-id %s -examname %s -date %s", uniqueId, examName, date);

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidTimeFormat_throwsParseException() {
        String uniqueId = "000001";
        String examName = "Math";
        String date = "2024-04-10";
        String time = "14:60"; // Invalid minute
        String args = String.format("-id %s -examname %s -date %s -time %s", uniqueId, examName, date, time);

        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
