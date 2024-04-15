package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the list of all logs in the address book.
 */
public class LogList {
    private List<Log> logs;
    public LogList(List<Log> logs) {
        this.logs = logs;
    }

    public void addEntry(Log log) {
        logs.add(log);
    }

    public List<Log> getList() {
        if (this.logs != null) {
            return this.logs;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        int index = 1;
        StringBuilder builder = new StringBuilder();
        // Reverse log to show the newest log at the top of the list
        Collections.reverse(logs);
        for (Log log : logs) {
            String entry = String.format("%d\n%s\n", index, log.toString());
            builder.append(entry);
            index++;
        }
        if (logs.isEmpty()) {
            builder.append("No logs yet!");
        }
        assert builder.length() > 0 : "Log window content should not be empty";
        // Reverse the logs back to their original order to accommodate new logs
        Collections.reverse(logs);
        return builder.toString();
    }
}
