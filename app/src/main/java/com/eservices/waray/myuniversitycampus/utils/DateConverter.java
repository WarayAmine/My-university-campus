package com.eservices.waray.myuniversitycampus.utils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

// the typeconverter used in the problem entity
public class DateConverter {


    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}