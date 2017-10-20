package com.kath.sortapp.presentation.secondactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kath.sortapp.R;
import com.kath.sortapp.core.BaseFragment;
import com.kath.sortapp.data.entities.ElementEntity;

/**
 * Created by katherine on 15/10/17.
 */

public class SecondFragment extends BaseFragment {
    //View
    private RelativeLayout background;

    private ElementEntity elementEntity;


    public SecondFragment() {
        // Requires empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    public static SecondFragment newInstance(Bundle bundle) {
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second, container, false);
        background = root.findViewById(R.id.background);
        elementEntity = (ElementEntity) getArguments().getSerializable("ElementEntity");

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (elementEntity!=null){

            if (elementEntity.getColor().equals("ROJO")){
                background.setBackgroundColor(getContext().getResources().getColor(R.color.red));
            }
            if (elementEntity.getColor().equals("AZUL")){
                background.setBackgroundColor(getContext().getResources().getColor(R.color.blue_facebook));
            }
            if (elementEntity.getColor().equals("VERDE")){
                background.setBackgroundColor(getContext().getResources().getColor(R.color.green));
            }
        }

    }
}
