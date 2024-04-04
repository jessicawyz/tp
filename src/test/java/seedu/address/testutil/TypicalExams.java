package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;

/**
 * A utility class containing a list of {@code Exam} objects to be used in tests.
 */
public class TypicalExams {

    public static final Exam MATH_EXAM_ALICE = new Exam(
            "Math Final",
            LocalDate.of(2024, 12, 15),
            Optional.of(LocalTime.of(9, 0)),
            "Alice Pauline",
            new Id("000001")
    );

    public static final Exam CHINESE_EXAM_AMY = new Exam(
            "Chinese Midterm",
            LocalDate.of(2024, 9, 11),
            Optional.of(LocalTime.of(14, 0)),
            "Amy Bee",
            new Id("000002")
    );

    public static final Exam ENGLISH_EXAM_BENSON = new Exam(
            "English Final",
            LocalDate.of(2023, 11, 20),
            Optional.of(LocalTime.of(10, 0)),
            "Benson Meier",
            new Id("000003")
    );

    public static final Exam SCIENCE_EXAM_BOB = new Exam(
            "Science Midterm",
            LocalDate.of(2024, 10, 10),
            Optional.of(LocalTime.of(14, 0)),
            "Bob Choo",
            new Id("000004")
    );

    public static final Exam HISTORY_EXAM_CARL = new Exam(
            "History Final",
            LocalDate.of(2024, 11, 20),
            Optional.empty(), // Assuming no specific time set for this exam
            "Carl Kurz",
            new Id("000005")
    );

    public static final Exam ENGLISH_QUIZ_DANIEL = new Exam(
            "English Quiz",
            LocalDate.of(2024, 9, 5),
            Optional.of(LocalTime.of(11, 30)),
            "Daniel Meier",
            new Id("000006")
    );

    public static final Exam GEOGRAPHY_EXAM_ELLE = new Exam(
            "Geography End-Term",
            LocalDate.of(2024, 11, 30),
            Optional.of(LocalTime.of(15, 0)),
            "Elle Meyer",
            new Id("000007")
    );

    public static final Exam ART_PROJECT_FIONA = new Exam(
            "Art Project Submission",
            LocalDate.of(2024, 12, 20),
            Optional.empty(), // No specific time for submission deadline
            "Fiona Kunz",
            new Id("000008")
    );

    public static final Exam PHYSICS_LAB_GEORGE = new Exam(
            "Physics Lab Exam",
            LocalDate.of(2024, 10, 25),
            Optional.of(LocalTime.of(9, 30)),
            "George Best",
            new Id("000009")
    );

    public static final Exam CHEMISTRY_TEST_HOON = new Exam(
            "Chemistry Midterm Test",
            LocalDate.of(2024, 9, 15),
            Optional.of(LocalTime.of(10, 45)),
            "Hoon Meier",
            new Id("000010")
    );

    public static final Exam BIOLOGY_FINAL_IDA = new Exam(
            "Biology Final Exam",
            LocalDate.of(2024, 11, 15),
            Optional.of(LocalTime.of(13, 0)),
            "Ida Mueller",
            new Id("000011")
    );

    private TypicalExams() {}
}
