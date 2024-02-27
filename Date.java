public class Date {

    private int day;
    private int month;
    private int year;

    private static int[] daysInMonth = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    public int differenceInDays(Date otherDate) {
        // Calculate the difference between two dates in days
        // Assuming year, month, and day are instance variables of the Date class
        int days1 = year * 365 + month * 30 + day;
        int days2 = otherDate.year * 365 + otherDate.month * 30 + otherDate.day;

        return Math.abs(days1 - days2);
    }

    public Date(int d, int m, int y) {
        if(m < 1 || m > 12)
            throw new IllegalArgumentException("Invalid month.");

        // Check if it is a leap year.
        if(d == 29 && m == 2) {
            // If Feb 29, check if it is a leap year.
            if(!(y % 4 == 0 && y % 100 != 0 && y % 400 == 0))
                throw new IllegalArgumentException("Not a leap year.");
        } else {
            if (d < 1 || d > daysInMonth[m - 1])
                throw new IllegalArgumentException("Invalid day.");
        }

        year = y;
        month = m;
        day = d;
    }

    public String toString() {
        return String.format("%02d/%02d/%d", day, month, year);
    }

    public boolean isBefore(Date otherDate) {
        if (this.year < otherDate.year)
            return true;
        if (this.year > otherDate.year)
            return false;

        if (this.month < otherDate.month)
            return true;
        if (this.month > otherDate.month)
            return false;

        return this.day < otherDate.day;
    }
}