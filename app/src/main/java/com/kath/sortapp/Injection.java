package com.kath.sortapp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kath.sortapp.data.local.SortRepository;
import com.kath.sortapp.data.local.sqlite.SortLocalDataSource;

/**
 * Created by katherine on 18/10/17.
 */

public class Injection {

    public static SortRepository provideTasksRepository(@NonNull Context context) {
        return SortRepository.getInstance(SortLocalDataSource.getInstance(context));
    }
}
