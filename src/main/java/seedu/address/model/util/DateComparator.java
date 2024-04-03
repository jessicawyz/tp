package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Comparator for comparing dates with a reference date.
 */
public class DateComparator implements Comparator<LocalDate> {
    private final LocalDate referenceDate;

    public DateComparator(LocalDate referenceDate) {
        this.referenceDate = referenceDate;
    }

    @Override
    public int compare(LocalDate date1, LocalDate date2) {
        long daysSinceRefDate1 = date1.toEpochDay() - referenceDate.toEpochDay();
        long daysSinceRefDate2 = date2.toEpochDay() - referenceDate.toEpochDay();
        return Long.compare(daysSinceRefDate1, daysSinceRefDate2);
    }
}
