package com.kath.sortapp.data.local.sqlite;

import android.provider.BaseColumns;

/**
 * Created by katherine on 15/10/17.
 */

public final class SortPersistanceContract {

    public SortPersistanceContract() {
    }

    public static abstract class SortEntry implements BaseColumns {
        public static final String TABLE_NAME = "sort";
        public static final String COLUMN_ID ="id";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_LAST_TOUCH = "last_touch";
    }
}
