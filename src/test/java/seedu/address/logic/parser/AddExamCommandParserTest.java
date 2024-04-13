package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddExamCommandParserTest {

    public static final String MESSAGE_CONSTRAINTS_PAST_DATE = "Exam date in the past is invalid. "
            + "Please input exam dates from today onwards";
    private AddExamCommandParser parser = new AddExamCommandParser();

    @Test
    public void parse_validArgs_returnsAddExamCommand() throws Exception {
        String uniqueId = "000001";
        String examName = "Math";
        String date = "2025-04-10";
        String time = "14:00";
        String args = String.format(" -id %s -exam %s -date %s -time %s", uniqueId, examName, date, time);

        AddExamCommandParser parser = new AddExamCommandParser();
        AddExamCommand command = parser.parse(args);

        LocalDate expectedDate = LocalDate.parse(date);
        Optional<LocalTime> expectedTime = Optional.of(LocalTime.parse(time));
        AddExamCommand expectedCommand = new AddExamCommand(uniqueId, examName, expectedDate, expectedTime);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_validArgsWithoutTime_returnsAddExamCommand() throws Exception {
        String uniqueId = "000001";
        String examName = "Math";
        String date = "2025-04-10";
        String args = String.format(" -id %s -exam %s -date %s", uniqueId, examName, date);

        AddExamCommandParser parser = new AddExamCommandParser();
        AddExamCommand command = parser.parse(args);

        LocalDate expectedDate = LocalDate.parse(date);
        Optional<LocalTime> expectedTime = Optional.empty();
        AddExamCommand expectedCommand = new AddExamCommand(uniqueId, examName, expectedDate, expectedTime);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_currentDatePastTime_returnsAddExamCommand() throws Exception {
        String uniqueId = "000001";
        String examName = "Math";
        String date = "2025-05-10";
        String time = "14:00";
        String args = String.format(" -id %s -exam %s -date %s -time %s", uniqueId, examName, date, time);

        AddExamCommandParser parser = new AddExamCommandParser();
        AddExamCommand command = parser.parse(args);

        LocalDate expectedDate = LocalDate.parse(date);
        Optional<LocalTime> expectedTime = Optional.of(LocalTime.parse(time));
        AddExamCommand expectedCommand = new AddExamCommand(uniqueId, examName, expectedDate, expectedTime);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_nonLeapYearFeb29th_returnsFeb28th() throws Exception {
        String uniqueId = "000001";
        String examName = "Math";
        String nonLeapYearDate = "2025-02-29"; // 29th February in a non-leap year
        String time = "14:00";
        String args = String.format(" -id %s -exam %s -date %s -time %s", uniqueId, examName, nonLeapYearDate, time);

        AddExamCommandParser parser = new AddExamCommandParser();
        AddExamCommand command = parser.parse(args);

        LocalDate expectedDate = LocalDate.of(2025, 2, 28); // February 28th in the same year
        Optional<LocalTime> expectedTime = Optional.of(LocalTime.parse(time));
        AddExamCommand expectedCommand = new AddExamCommand(uniqueId, examName, expectedDate, expectedTime);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_pastDate_throwsParseException() {
        String uniqueId = "000001";
        String examName = "Math";
        String pastDate = "2020-01-01"; // A past date
        String args = String.format(" -id %s -exam %s -date %s", uniqueId, examName, pastDate);

        AddExamCommandParser parser = new AddExamCommandParser();

        ParseException thrownException = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(MESSAGE_CONSTRAINTS_PAST_DATE, thrownException.getMessage());
    }

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

    @Test
    public void parse_extraArguments_throwsParseException() {
        // Extra unparsed arguments
        String userInput = " -id 123 -exam Maths -date 2023-04-13 -time 14:00 -extra unused";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        // Empty string input
        String userInput = " ";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
