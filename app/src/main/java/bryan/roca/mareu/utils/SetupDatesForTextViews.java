package bryan.roca.mareu.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SetupDatesForTextViews {
    private String dayOfMonthBegin;
    private String monthOfYearBegin;
    private String year;
    private String hourBegin;
    private String minutesBegin;
    private String dayOfMonthEnd;
    private String monthOfYearEnd;
    private String hourEnd;
    private String minutesEnd;

    public void setup() {
        // Formatters
        DateTimeFormatter dateTimeFormatterDay = DateTimeFormat.forPattern("dd");
        DateTimeFormatter dateTimeFormatterMonth = DateTimeFormat.forPattern("MM");
        DateTimeFormatter dateTimeFormatterYear = DateTimeFormat.forPattern("yyyy");
        DateTimeFormatter dateTimeFormatterHour = DateTimeFormat.forPattern("HH");
        DateTimeFormatter dateTimeFormatterMinutes= DateTimeFormat.forPattern("mm");

        // Dates
        DateTime dateTimeBegin = DateTime.now();
        DateTime dateTimeEnd = dateTimeBegin.plusHours(1);

        // Fetching begin datas
        dayOfMonthBegin = dateTimeBegin.toString(dateTimeFormatterDay);
        monthOfYearBegin = dateTimeBegin.toString(dateTimeFormatterMonth);
        year = dateTimeBegin.toString(dateTimeFormatterYear);
        hourBegin = dateTimeBegin.toString(dateTimeFormatterHour);
        minutesBegin = dateTimeBegin.toString(dateTimeFormatterMinutes);

        // Fetching end datas
        dayOfMonthEnd = dateTimeEnd.toString(dateTimeFormatterDay);
        monthOfYearEnd = dateTimeEnd.toString(dateTimeFormatterMonth);
        hourEnd = dateTimeEnd.toString(dateTimeFormatterHour);
        minutesEnd = dateTimeEnd.toString(dateTimeFormatterMinutes);
    }

    public String getDayOfMonthBegin() {
        return dayOfMonthBegin;
    }

    public String getMonthOfYearBegin() {
        return monthOfYearBegin;
    }

    public String getYear() {
        return year;
    }

    public String getHourBegin() {
        return hourBegin;
    }

    public String getMinutesBegin() {
        return minutesBegin;
    }

    public String getDayOfMonthEnd() {
        return dayOfMonthEnd;
    }

    public String getMonthOfYearEnd() {
        return monthOfYearEnd;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public String getMinutesEnd() {
        return minutesEnd;
    }
}
