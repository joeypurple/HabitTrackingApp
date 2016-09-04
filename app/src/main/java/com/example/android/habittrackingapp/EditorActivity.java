package com.example.android.habittrackingapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittrackingapp.data.HabitContract;
import com.example.android.habittrackingapp.data.HabitDbHelper;

/**
 * Created by joe on 04/09/2016.
 */
public class EditorActivity extends AppCompatActivity {
    private EditText mMealsEditText;
    private EditText mActivityEditText;
    private EditText mSleepEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

    }

    private void insertHabit() {
        String mealString = mMealsEditText.getText().toString().trim();
        String activityString = mActivityEditText.getText().toString().trim();
        String sleepString = mSleepEditText.getText().toString().trim();
        int sleepHours = Integer.parseInt(sleepString);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_MEALS, mealString);
        values.put(HabitContract.HabitEntry.COLUMN_ACTIVITY, activityString);
        values.put(HabitContract.HabitEntry.COLUMN_SLEEP, sleepHours);

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved with row ID: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}

