package io.dataglitter.PostService.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by reddys on 14/12/2017.
 */
public class Utilities {
    public static String convertCurrentDateTimeToString(String format, Date now){
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);
        return sdfDate.format(now);
    }
}
