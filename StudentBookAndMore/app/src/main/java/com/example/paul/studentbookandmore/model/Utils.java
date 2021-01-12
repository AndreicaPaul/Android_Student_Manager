package com.example.paul.studentbookandmore.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String stringForDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        String dateAsString = format.format(date);
        return dateAsString;
    }


}
