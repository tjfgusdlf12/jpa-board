package jpaTestBoard.jpaboard.Common.Utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getDateNow(){

        return new Date(Calendar.getInstance().getTimeInMillis());
    }
}
