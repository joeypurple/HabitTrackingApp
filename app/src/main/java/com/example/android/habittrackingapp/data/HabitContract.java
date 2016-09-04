package com.example.android.habittrackingapp.data;

import android.provider.BaseColumns;

/**
 * Created by joe on 04/09/2016.
 */
public class HabitContract {

    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MEALS = "meals";
        public static final String COLUMN_ACTIVITY = "activity";
        public static final String COLUMN_SLEEP = "sleep";


    }
}
