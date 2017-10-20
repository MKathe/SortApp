package com.kath.sortapp.presentation.secondactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.kath.sortapp.R;
import com.kath.sortapp.core.BaseActivity;
import com.kath.sortapp.presentation.utils.ActivityUtils;


/**
 * Created by katherine on 14/10/17.
 */

public class Activity2 extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        SecondFragment fragment = (SecondFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);
        if (fragment == null) {
            fragment = SecondFragment.newInstance(getIntent().getExtras());

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }
    }
}
