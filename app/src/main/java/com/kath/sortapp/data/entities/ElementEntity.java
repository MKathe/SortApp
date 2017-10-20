package com.kath.sortapp.data.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.kath.sortapp.data.local.sqlite.SortPersistanceContract;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by katherine on 14/10/17.
 */

public class ElementEntity implements Serializable, Comparable<ElementEntity> {

    private String id;
    private String color;
    private int count;
    private String last_touch;

    public ElementEntity(String id, String color, int count, String last_touch) {
        this.id = id;
        this.color = color;
        this.count = count;
        this.last_touch = last_touch;
    }

    public ElementEntity(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(SortPersistanceContract.SortEntry._ID));
        color = cursor.getString(cursor.getColumnIndex(SortPersistanceContract.SortEntry.COLUMN_COLOR));
        count = cursor.getInt(cursor.getColumnIndex(SortPersistanceContract.SortEntry.COLUMN_COUNT));
        last_touch = cursor.getString(cursor.getColumnIndex(SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH));
    }


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(SortPersistanceContract.SortEntry._ID, id);
        values.put(SortPersistanceContract.SortEntry.COLUMN_COLOR, color);
        values.put(SortPersistanceContract.SortEntry.COLUMN_COUNT, count);
        values.put(SortPersistanceContract.SortEntry.COLUMN_LAST_TOUCH, last_touch);
        return values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTouch() {
        return last_touch;
    }

    public void setTouch(String last_touch) {
        this.last_touch = last_touch;
    }

    @Override
    public int compareTo(@NonNull ElementEntity elementEntity) {
        if (elementEntity.count  < count) {
            return -1;
        }
        if (elementEntity.count > count) {
            return 1;
        }
        return 0;
    }

    public Date getDate(){
        Date tempDate = null;
        SimpleDateFormat parseDateFromServer= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tempDate = parseDateFromServer.parse(getTouch());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tempDate;

    }

}
