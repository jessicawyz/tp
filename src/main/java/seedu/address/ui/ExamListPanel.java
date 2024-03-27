package seedu.address.ui;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
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
    private final List<Person> persons;

    @FXML
    private ListView<Pair<Person, Exam>> examListView;

    public ExamListPanel(ObservableList<Person> personList) {
        super(FXML);
        this.persons = new ArrayList<>(personList);
        initializeListView();
    }

    private void initializeListView() {
        List<Pair<Person, Exam>> personExamPairs = new ArrayList<>();
        // Collect pairs of (Person, Exam)
        for (Person person : persons) {
            for (Exam exam : person.getExams()) {
                Pair<Person, Exam> pair = new Pair<>(person, exam);
                personExamPairs.add(pair);
            }
        }

        // Sort the pairs based on the exam date, with the closest date to the current date appearing first
        Collections.sort(personExamPairs, (pair1, pair2) -> {
            LocalDate currentDate = LocalDate.now();
            LocalDate examDate1 = pair1.getValue().getExamDate();
            LocalDate examDate2 = pair2.getValue().getExamDate();
            long daysDifference1 = Math.abs(ChronoUnit.DAYS.between(currentDate, examDate1));
            long daysDifference2 = Math.abs(ChronoUnit.DAYS.between(currentDate, examDate2));
            return Long.compare(daysDifference1, daysDifference2);
        });

        // Set up the examListView with the sorted list of pairs
        ObservableList<Pair<Person, Exam>> observablePairs = FXCollections.observableArrayList(personExamPairs);
        examListView.setItems(observablePairs);
        examListView.setCellFactory(listView -> new ExamListViewCell());
    }

    /**
     * Custom ListCell that displays the graphics of an exam using an ExamDisplayCard.
     */
    public class ExamListViewCell extends ListCell<Pair<Person, Exam>> {
        @Override
        protected void updateItem(Pair<Person, Exam> pair, boolean empty) {
            super.updateItem(pair, empty);
            System.out.println("NOOOO nul");
            if (empty || pair == null) {
                System.out.println("hiiii nul");
                setGraphic(null);
                setText(null);
            } else {
                // Display each exam using ExamDisplayCard
                setGraphic(new ExamDisplayCard(pair.getKey(), pair.getValue()).getRoot());
            }
        }
    }
}
