package com.example.android.habittrackingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by joe on 04/09/2016.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "tracker.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_MEALS + " TEXT, "
                + HabitContract.HabitEntry.COLUMN_ACTIVITY + " TEXT, "
                + HabitContract.HabitEntry.COLUMN_SLEEP + " INTEGER);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
        Log.v(LOG_TAG, "db name");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onUpdate(SQLiteDatabase db) {
        String SQL_UPDATE_HABITS_TABLE = "UPDATE " + HabitContract.HabitEntry.TABLE_NAME + " SET "
                + HabitContract.HabitEntry.COLUMN_SLEEP + " = " + 7 + " WHERE "
                + HabitContract.HabitEntry._ID + " = " + 1 + ";";

        db.execSQL(SQL_UPDATE_HABITS_TABLE);
        Log.v(LOG_TAG, "db updated");
    }


    public void onDelete(SQLiteDatabase db) {
        String SQL_DELETE_HABITS_TABLE = "DROP TABLE " + HabitContract.HabitEntry.TABLE_NAME;

        db.execSQL(SQL_DELETE_HABITS_TABLE);
        Log.v(LOG_TAG, "db deleted");
    }

}
