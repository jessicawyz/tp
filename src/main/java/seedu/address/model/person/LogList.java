package seedu.address.model.person;

import java.util.List;

public class LogList {
    private List<Log> logs;
    public LogList(List<Log> logs) {
        this.logs = logs;
    }

    public void addEntry(Log log) {
        logs.add(log);
    }

    public List<Log> getList() {
        return this.logs;
    }

    @Override
    public String toString() {
        int index = 1;
        StringBuilder builder = new StringBuilder();
        for (Log log : logs) {
            String entry = String.format("%d\n%s\n", index, log.toString());
            builder.append(entry);
            index++;
        }
        if (logs.isEmpty()) {
            builder.append("No logs yet!");
        }
        return builder.toString();
    }
}
