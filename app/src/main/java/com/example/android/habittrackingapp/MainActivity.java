package com.example.android.habittrackingapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittrackingapp.data.HabitContract;
import com.example.android.habittrackingapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbHelper = new HabitDbHelper(this);
    }

    private void displayDatabaseInfo() {
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
    }


    private void insertHabit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_MEALS, "Pancakes and fruit salad");
        values.put(HabitContract.HabitEntry.COLUMN_ACTIVITY, "1hr cycling");
        values.put(HabitContract.HabitEntry.COLUMN_SLEEP, 6);

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "New Row ID: " + newRowId);
    }

}
