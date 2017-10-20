package com.kath.sortapp.presentation.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kath.sortapp.Injection;
import com.kath.sortapp.R;
import com.kath.sortapp.core.BaseActivity;
import com.kath.sortapp.core.BaseFragment;
import com.kath.sortapp.data.entities.ElementEntity;
import com.kath.sortapp.data.local.sqlite.SortDbHelper;
import com.kath.sortapp.presentation.secondactivity.Activity2;
import com.kath.sortapp.presentation.sort.listener.ElementItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by katherine on 14/10/17.
 */

public class SortFragment extends BaseFragment implements SortContract.View{

    //View
    private RecyclerView mRecyclerView;
    private LinearLayout mLinearLayout;

    private SortContract.Presenter mPresenter;

    //Recycler
    private SortAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    //Entity
    private int count;

    private SortDbHelper mDbHelper;

    private boolean first_load = true;
    private List<ElementEntity> last_list;

    public SortFragment() {
        // Requires empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.LoadListElements(first_load);
    }

    public static SortFragment newInstance() {
        return new SortFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SortPresenter(this, getContext(), Injection.provideTasksRepository(getActivity()));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = root.findViewById(R.id.rv_list);
        mLinearLayout = root.findViewById(R.id.noList);
        mDbHelper = new SortDbHelper(getActivity());
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SortAdapter(new ArrayList<ElementEntity>() , getContext(), (ElementItem) mPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getElementsSuccessful(List<ElementEntity> list) {
        first_load = false;
        mAdapter.setItems(list);
        if (list !=null){
            mLinearLayout.setVisibility((list.size()>0) ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void clickElement(ElementEntity elementEntity) {
        updateElement(elementEntity);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ElementEntity",elementEntity);
        next(getActivity(), bundle, Activity2.class,false);

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    public void updateElement(ElementEntity elementEntity ){
        String last_touch = getTouch();
        int newCount = getNewCount(elementEntity.getCount());
        //Toast.makeText(getContext(), String.valueOf(elementEntity.getCount()+1), Toast.LENGTH_SHORT).show();
        //int newCount = 0;
        mPresenter.updateElement(elementEntity.getId(), newCount,last_touch);
    }

    public int getNewCount(int count){
        return count+1;
    }

    public String getTouch(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        return strDate;
    }

    @Override
    public void setPresenter(SortContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;

    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
    }

    @Override
    public void showMessage(String message) {
        ((BaseActivity) getActivity()).showMessage(message);
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity) getActivity()).showMessageError(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
