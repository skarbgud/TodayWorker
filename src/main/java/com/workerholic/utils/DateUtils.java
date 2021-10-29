package com.workerholic.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	
	public static String getDatetimeString(Date datetime)
	{
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime ldt = LocalDateTime.ofInstant(datetime.toInstant(), ZoneId.systemDefault());
        String datetimeString = sdf.format(ldt);

        return datetimeString;
	}

}
