package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Exam;

/**
 * A UI component that displays information of a {@code Exam}.
 */
public class ExamDisplayCard extends UiPart<Region> {

    private static final String FXML = "ExamDisplayCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label personNameLabel;

    @FXML
    private Label personIdLabel;

    @FXML
    private Label examNameLabel;

    @FXML
    private Label examDateLabel;

    @FXML
    private Label examTimeLabel;

    @FXML
    private Label daysUntilLabel;
    private final Exam exam;

    /**
     * Creates a {@code ExamDisplayCard} with the given {@code Exam} and index to display.
     */
    public ExamDisplayCard(Exam exam) {
        super(FXML);
        System.out.println("this is exam display card");
        this.exam = exam;
        personNameLabel.setText(exam.getStudentName());
        personIdLabel.setText("#" + exam.getUniqueId().toString());
        examNameLabel.setText(exam.getExamName());
        examDateLabel.setText(exam.getDate().toString());
        exam.getExamTime().ifPresent(time -> examTimeLabel.setText(time.toString()));
        daysUntilLabel.setText(exam.getDaysFromCurrentDate() + " Days from now");
    }
}
