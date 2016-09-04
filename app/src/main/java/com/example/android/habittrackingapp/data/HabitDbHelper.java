package com.example.android.habittrackingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;


/**
 * Created by joe on 04/09/2016.
 */
public class HabitDbHelper extends SQLiteOpenHelper {
    private HabitDbHelper mDbHelper;
    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "tracker.db";
    private static final int DATABASE_VERSION = 1;

    private EditText mMealsEditText;
    private EditText mActivityEditText;
    private EditText mSleepEditText;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    private Cursor displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_MEALS,
                HabitContract.HabitEntry.COLUMN_ACTIVITY,
                HabitContract.HabitEntry.COLUMN_SLEEP
        };

        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        //Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
        int mealsColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_MEALS);
        int activityColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_ACTIVITY);
        int sleepColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_SLEEP);

        //Iterate through all the returned rows in the cursor
        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idColumnIndex);
            String currentMeal = cursor.getString(mealsColumnIndex);
            String currentActivity = cursor.getString(activityColumnIndex);
            int currentSleepHours = cursor.getInt(sleepColumnIndex);

        }
        // Always close the cursor when you're done reading from it. This releases all its
        // resources and makes it invalid.
        cursor.close();

        return cursor;
    }

    private void insertHabit() {
        String mealString = mMealsEditText.getText().toString().trim();
        String activityString = mActivityEditText.getText().toString().trim();
        String sleepString = mSleepEditText.getText().toString().trim();
        int sleepHours = Integer.parseInt(sleepString);


        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_MEALS, mealString);
        values.put(HabitContract.HabitEntry.COLUMN_ACTIVITY, activityString);
        values.put(HabitContract.HabitEntry.COLUMN_SLEEP, sleepHours);

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.v("DBhelper", "New Row ID: " + newRowId);


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
                + HabitContract.HabitEntry.COLUMN_SLEEP + " = " + mSleepEditText + " WHERE "
                + HabitContract.HabitEntry._ID + " = " + 1 + ";";

        db.execSQL(SQL_UPDATE_HABITS_TABLE);
        Log.v(LOG_TAG, "db updated");
    }


    public void deleteDatabase(SQLiteDatabase db) {
        String SQL_DELETE_HABITS_TABLE = "DROP TABLE " + HabitContract.HabitEntry.TABLE_NAME;

        db.execSQL(SQL_DELETE_HABITS_TABLE);
        Log.v(LOG_TAG, "db deleted");
    }

}
