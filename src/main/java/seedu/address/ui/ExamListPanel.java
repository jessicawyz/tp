package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.AllExamsList;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of exams.
 */
public class ExamListPanel extends UiPart<Region> {

    private static final String FXML = "ExamListPanel.fxml";
    private final ObservableList<Exam> exams;
    private final ObservableList<Person> personList;

    @FXML
    private ListView<Exam> examListView;

    /**
     * Creates a {@code ExamListPanel} with the given {@code ObservableList}.
     */
    public ExamListPanel(ObservableList<Person> personList) {
        super(FXML);
        this.personList = personList;
        this.exams = FXCollections.observableArrayList();
        initializeExams();
        initializeListView();
    }

    private void initializeExams() {
        for (Person person : personList) {
            for (Exam exam : person.getExams()) {
                AllExamsList.addExamToList(exam);
            }
        }
    }

    private void initializeListView() {
        examListView.setItems(AllExamsList.getExams());
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

