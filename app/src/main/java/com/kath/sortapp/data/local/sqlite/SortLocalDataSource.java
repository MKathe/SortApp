package com.kath.sortapp.data.local.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.kath.sortapp.data.entities.ElementEntity;
import com.kath.sortapp.data.local.SortDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katherine on 15/10/17.
 */

public class SortLocalDataSource implements SortDataSource {

    private static SortLocalDataSource INSTANCE;

    private SortDbHelper mDbHelper;

    // Prevent direct instantiation.
    private SortLocalDataSource(@NonNull Context context) {
        mDbHelper = new SortDbHelper(context);
    }

    public static SortLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SortLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getELements(@NonNull LoadElementsCallback callback) {
        List<ElementEntity> elementEntities = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                SortPersistanceContract.SortEntry.COLUMN_ID,
                SortPersistanceContract.SortEntry.COLUMN_COLOR,
                SortPersistanceContract.SortEntry.COLUMN_COUNT,
                SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH
        };

        Cursor c = db.query(
                SortPersistanceContract.SortEntry.TABLE_NAME,
                projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String itemId = c.getString(c.getColumnIndexOrThrow(SortPersistanceContract.SortEntry.COLUMN_ID));
                String color = c.getString(c.getColumnIndexOrThrow(SortPersistanceContract.SortEntry.COLUMN_COLOR));
                int count = c.getInt(c.getColumnIndexOrThrow(SortPersistanceContract.SortEntry.COLUMN_COUNT));
                String last_touch = c.getString(c.getColumnIndexOrThrow(SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH));


                ElementEntity elementEntity = new ElementEntity(itemId, color, count, last_touch);
                elementEntities.add(elementEntity);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (elementEntities.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onElementsLoaded(elementEntities);
        }
    }

    @Override
    public void saveElement(@NonNull ElementEntity elementEntity) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SortPersistanceContract.SortEntry.COLUMN_ID, elementEntity.getId());
        values.put(SortPersistanceContract.SortEntry.COLUMN_COLOR, elementEntity.getColor());
        values.put(SortPersistanceContract.SortEntry.COLUMN_COUNT, 0);
        values.put(SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH, elementEntity.getTouch());

        db.insert(SortPersistanceContract.SortEntry.TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void refreshElements(List<ElementEntity> list) {

    }

    @Override
    public void updateElements(String id, int count, String last_touch) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Valores
        ContentValues values = new ContentValues();

        // Valores nuevos
        values.put(SortPersistanceContract.SortEntry.COLUMN_COUNT, count);
        values.put(SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH, last_touch);

        // WHERE
        String selection = SortPersistanceContract.SortEntry.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {id};

        // Actualizar
        db.update(
                SortPersistanceContract.SortEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    @Override
    public void deleteAllElements() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(SortPersistanceContract.SortEntry.TABLE_NAME, null, null);

        db.close();
    }
}
