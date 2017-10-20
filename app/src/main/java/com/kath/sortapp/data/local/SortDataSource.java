package com.kath.sortapp.data.local;

import android.support.annotation.NonNull;

import com.kath.sortapp.data.entities.ElementEntity;
import java.util.List;

/**
 * Created by katherine on 15/10/17.
 */

public interface SortDataSource {



    interface LoadElementsCallback {

        void onElementsLoaded(List<ElementEntity> elementEntities);

        void onDataNotAvailable();
    }


    void getELements(@NonNull LoadElementsCallback callback);

    void saveElement (@NonNull ElementEntity elementEntity);

    void refreshElements(List<ElementEntity> list);

    void updateElements(String id, int count, String last_touch);

    void deleteAllElements();

}
