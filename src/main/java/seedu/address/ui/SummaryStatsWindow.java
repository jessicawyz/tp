package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a help page
 */
public class SummaryStatsWindow extends UiPart<Stage> {

    public static final String SUMMARYSTATS_MESSAGE_COUNT = "The Total Student Count is";

    public static final String SUMMARYSTATS_MESSAGE_OWING = "The Total Tuition fee owings by Students is $";

    public static final String SUMMARYSTATS_MESSAGE_EXAM = "The number of Exams in the upcoming month is :";

    private static final Logger logger = LogsCenter.getLogger(SummaryStatsWindow.class);
    private static final String FXML = "SummaryStatsWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label summaryMessageCountLabel;

    @FXML
    private Label summaryMessageOwingsLabel;

    @FXML
    private Label summaryMessageUpcomingExamCountLabel;

    private Logic logic;
    private int totalPerson;
    private int upcomingMonthExams;

    private double totalOwings;

    /**
     * Creates a new SummaryStatsWindow.
     *
     * @param root Stage to use as the root of the SummaryStatsWindow.
     */
    public SummaryStatsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SummaryStatsWindow.
     */
    public SummaryStatsWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
    }

    /**
     * Shows the SummaryStatsWindow window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing summary stats of students.");
        updateSummaryStats();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the SummaryStatsWindow window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the SummaryStatsWindow window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the SummaryStatsWindow window.
     */
    public void focus() {
        updateSummaryStats();
        getRoot().requestFocus();
    }

    private void updateTotalCountofPersons() {
        totalPerson = logic.getTotalPersons();
        assert totalPerson < 0 : "Can't have a negative amount of Persons";
        String output = String.format(SUMMARYSTATS_MESSAGE_COUNT + " %d", totalPerson);
        summaryMessageCountLabel.setText(output);
    }

    private void updateTotalOwingsofPersons() {
        totalOwings = logic.getTotalOwings();
        assert totalOwings < 0 : "Total Owings cannot be negative";
        String output = String.format(SUMMARYSTATS_MESSAGE_OWING + "%f ", totalOwings);
        summaryMessageOwingsLabel.setText(output);
    }

    private void updateUpcomingMonthExams() {
        upcomingMonthExams = logic.getUpcomingMonthExamCount();
        assert upcomingMonthExams < 0 : "Cannot have a negative upcoming Month Exams";
        String output = String.format(SUMMARYSTATS_MESSAGE_EXAM + " %d", upcomingMonthExams);
        summaryMessageUpcomingExamCountLabel.setText(output);
    }

    private void updateSummaryStats() {
        updateTotalCountofPersons();
        updateTotalOwingsofPersons();
        updateUpcomingMonthExams();
    }
}
