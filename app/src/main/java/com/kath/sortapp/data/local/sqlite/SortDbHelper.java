package com.kath.sortapp.data.local.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by katherine on 15/10/17.
 */

public class SortDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "sort.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SortPersistanceContract.SortEntry.TABLE_NAME + " (" +
                    SortPersistanceContract.SortEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    SortPersistanceContract.SortEntry.COLUMN_ID + TEXT_TYPE + COMMA_SEP +
                    SortPersistanceContract.SortEntry.COLUMN_COLOR + TEXT_TYPE + COMMA_SEP +
                    SortPersistanceContract.SortEntry.COLUMN_COUNT + INTEGER_TYPE + COMMA_SEP +
                    SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH + TEXT_TYPE +
                    " )";



    public SortDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cars");

        //Se crea la nueva versión de la tabla
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }




}
