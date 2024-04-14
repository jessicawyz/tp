package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Window to show the details of a student.
 */
public class StudentDetailsWindow extends UiPart<Stage> {
    public static final String STUDENT_DETAILS_MESSAGE = "Student details for %s";

    private static final Logger logger = LogsCenter.getLogger(StudentDetailsWindow.class);
    private static final String FXML = "StudentDetailsWindow.fxml";


    @FXML
    private Button copyButton;

    @FXML
    private Label detailsMessage;

    /**
     * Creates a new StudentDetailsWindow.
     *
     * @param root Stage to use as the root of the SummaryStatsWindow.
     */
    public StudentDetailsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SummaryStatsWindow.
     */
    public StudentDetailsWindow() {
        this(new Stage());
    }

    /**
     * Shows the student logs window.
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
    public void show(String content) {
        logger.fine("Showing Logs of Lessons.");
        getRoot().show();
        getRoot().centerOnScreen();
        updateDetailsContent(content);
    }

    private void updateDetailsContent(String content) {
        detailsMessage.setText(content);
    }

    /**
     * Returns true if the Student details window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Student details window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Student details window.
     */
    public void focus(String content) {
        getRoot().requestFocus();
        updateDetailsContent(content);
    }
}
