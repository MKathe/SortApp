package com.kath.sortapp.presentation.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kath.sortapp.R;
import com.kath.sortapp.core.BaseActivity;
import com.kath.sortapp.presentation.utils.ActivityUtils;

/**
 * Created by katherine on 14/10/17.
 */

public class Activity1 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);


        SortFragment fragment = (SortFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);
        if (fragment == null) {
            fragment = SortFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }

    }
}
