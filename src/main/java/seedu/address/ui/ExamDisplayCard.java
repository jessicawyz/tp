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

    private final Person person;
    private final Exam exam;

    public ExamDisplayCard(Person person, Exam exam) {
        super(FXML);
        this.person = person;
        this.exam = exam;
        personNameLabel.setText("Name: " + person.getName().fullName);
        personIdLabel.setText("ID: " + person.getUniqueId().toString());
        examNameLabel.setText("Exam: " + exam.getExamName());
        examDateLabel.setText("Date: " + exam.getDate().toString());
        exam.getExamTime().ifPresent(time -> examTimeLabel.setText("Time: " + time.toString()));
        System.out.println("HIHIHI");
    }
}
