package utils;

import exception.InvalidDateException;
import exception.InvalidDateFormatException;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    private final static String dateRegix = "^\\d{4}/\\d{2}/\\d{2}$";
    private static Calendar calendar = Calendar.getInstance();

    public static Date addDate(Date current, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date stringToDate(String date) {
        String[] strings = date.split("/");
        int[] dateInInt = new int[3];
        for (int i = 0; i < 3; i++) {
            dateInInt[i] = Integer.parseInt(strings[i]);
        }
        Date currentDate = calendar.getTime();
        calendar.set(dateInInt[0], dateInInt[1] - 1, dateInInt[2]);
        return calendar.getTime();
    }

    public static void checkInputFormat(String date) throws InvalidDateFormatException, InvalidDateException {
        if (!date.matches(dateRegix)) {
            throw new InvalidDateFormatException("Please follow the input format");
        }
        Date dateInDate = DateHelper.stringToDate(date);
        if (dateInDate.compareTo(Calendar.getInstance().getTime()) < 0) {
            throw new InvalidDateException("Please enter a date later than current date");
        }
        try {
            DateHelper.calendar.setTime(dateInDate);
            DateHelper.calendar.getTime();
        } catch (Exception ex) {
            throw new InvalidDateFormatException("Please enter a valid date.");
        }
    }
}
