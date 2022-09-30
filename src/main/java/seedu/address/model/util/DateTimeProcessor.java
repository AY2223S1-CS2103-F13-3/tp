package seedu.address.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;

/**
 * Checks whether the format of a due is in valid date-time format
 * in yyyy-MM-dd HHmm
 */
public class DateTimeProcessor {

    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter timeFormatter;

    /**
     * Constructor for a new DateValidator object
     *
     * @param dateFormatter determines the accepted date&time format of the input string
     */
    public DateTimeProcessor(DateTimeFormatter dateFormatter, DateTimeFormatter timeFormatter) {
        this.dateFormatter = dateFormatter;
        this.timeFormatter = timeFormatter;
    }

    /**
     * Checks if the input string matches the accepted date format of the formatter
     *
     * @return Whether date format is valid
     */
    private boolean isDateValid(String dateStr) {
        try {
            this.dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the input string matches the accepted time format of the formatter
     *
     * @return Whether time format is valid
     */
    private boolean isTimeValid(String timeStr) {
        try {
            this.timeFormatter.parse(timeStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * converts input date&time from yyyy-MM-dd HHmm to MONTH dd yyyy hh:mm aa format
     *
     * @param dateAndTime date and time of meeting
     * @return date and time in MONTH dd yyyy hh:mm aa format
     */
    public String processDateTime(String dateAndTime) throws ParseException {
        String[] tempStringArray = dateAndTime.strip().split("\\s+", 2);

        String dueDate = tempStringArray[0];
        String dueTime = tempStringArray.length < 2 ? "" : tempStringArray[1];

        if (!isDateValid(dueDate)) {
            throw new ParseException("Meeting date is not in yyyy-MM-dd format", 0);
        }

        LocalDate inputDue = LocalDate.parse(dueDate);
        String year = String.valueOf(inputDue.getYear());
        String month = String.valueOf(inputDue.getMonth());
        String date = String.valueOf(inputDue.getDayOfMonth());

        //time pattern of input date in 24 hour format -- HH for 24h, hh for 12h
        DateFormat inputTimeFormat = new SimpleDateFormat("HHmm");
        inputTimeFormat.setLenient(false);

        //Date/time pattern of desired output date
        DateFormat outputTimeFormat = new SimpleDateFormat("hh:mm aa"); // aa for AM/ PM

        if (Objects.equals(dueTime, "")) {
            return month + " " + date + " " + year;

        } else if (!isTimeValid(dueTime)) {
            throw new ParseException("Meeting date is not in HHmm format", 0);
        }

        Date inputTime = inputTimeFormat.parse(dueTime);
        String outputTime = outputTimeFormat.format(inputTime);
        return month + " " + date + " " + year + " " + outputTime;
    }

    // for debugging
    /*public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US)
            .withResolverStyle(ResolverStyle.SMART);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.US)
            .withResolverStyle(ResolverStyle.SMART);
        DateTimeProcessor validator = new DateTimeProcessor(dateFormatter, timeFormatter);
        try {
            validator.processDateTime("asdasd");
        } catch (ParseException e) {
            System.out.println(e);
        }
    }*/

}
