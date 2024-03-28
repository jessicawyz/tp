package seedu.address.ui;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Person;

public class ExamListPanel extends UiPart<Region> {

    private static final String FXML = "ExamListPanel.fxml";
    private final ObservableList<Exam> exams;

    @FXML
    private ListView<Exam> examListView;

    public ExamListPanel(ObservableList<Exam> exams) {
        super(FXML);
        this.exams = exams;
        initializeListView();
    }

    private void initializeListView() {
        examListView.setItems(exams);
        examListView.setCellFactory(listView -> new ExamListViewCell());
    }

    /**
     * Custom ListCell that displays the graphics of an exam using an ExamDisplayCard.
     */
    public class ExamListViewCell extends ListCell<Exam> {
        @Override
        protected void updateItem(Exam exam, boolean empty) {
            super.updateItem(exam, empty);

            if (empty || exam == null) {
                setGraphic(null);
                setText(null);
            } else {
                // Display each exam using ExamDisplayCard
                setGraphic(new ExamDisplayCard(exam).getRoot());
            }
        }
    }
}
