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
}
