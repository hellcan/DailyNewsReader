package com.news.reader.newsreader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fengchengding on 17/10/12.
 */

public class DateUtils {
    String s;

    public DateUtils(){
    }

    public String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         s = sdf.format(date);

        return s;
    }
}
