package utils;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static Date addDate(Date current, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
