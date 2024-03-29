package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Person;

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
    private final Exam exam;

    public ExamDisplayCard(Exam exam) {
        super(FXML);
        System.out.println("this is exam display card");
        this.exam = exam;
        personNameLabel.setText(exam.getStudentName());
        personIdLabel.setText("#" + exam.getUniqueId().toString());
        examNameLabel.setText(exam.getExamName());
        examDateLabel.setText(exam.getDate().toString());
        exam.getExamTime().ifPresent(time -> examTimeLabel.setText(time.toString()));
    }
}
