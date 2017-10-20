package com.kath.sortapp.presentation.sort;

import com.kath.sortapp.core.BasePresenter;
import com.kath.sortapp.core.BaseView;
import com.kath.sortapp.data.entities.ElementEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katherine on 14/10/17.
 */

public interface SortContract {
    interface View extends BaseView<Presenter> {

        void getElementsSuccessful(List<ElementEntity> list);

        void clickElement(ElementEntity elementEntity);

        void reloadList();

        boolean isActive();



    }

    interface Presenter extends BasePresenter {

        void LoadListElements(boolean type);

        void updateElement(String id, int count, String last_touch);

    }
}

