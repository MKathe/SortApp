package com.kath.sortapp.data.local;

import android.support.annotation.NonNull;

import com.kath.sortapp.data.entities.ElementEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by katherine on 13/10/17.
 */

public class SortRepository implements SortDataSource {

    private static SortRepository INSTANCE = null;

    private final SortDataSource mSortDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, ElementEntity> mElements;


    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private SortRepository(@NonNull SortDataSource sortDataSource) {
        this.mSortDataSource = sortDataSource;
    }

    public static SortRepository getInstance(SortDataSource sortDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new SortRepository(sortDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    private void refreshCache(List<ElementEntity> elementEntities) {
        if (mElements == null) {
            mElements = new LinkedHashMap<>();
        }
        mElements.clear();
        for (ElementEntity elementEntity : elementEntities) {
            mElements.put(elementEntity.getId(), elementEntity);
        }
        mCacheIsDirty = false;
    }

    private void ListELements(){
       ArrayList<ElementEntity> list = new ArrayList<>();
        list.add(new ElementEntity("1","ROJO",0,null));
        list.add(new ElementEntity("2","AZUL",0,null));
        list.add(new ElementEntity("3","VERDE",0,null));
        //list.add(new ElementEntity("4","ROJO",0,null));
        for (ElementEntity elementEntity : list) {
            mSortDataSource.saveElement(elementEntity);
        }

    }

    private void refreshLocalDataSource(List<ElementEntity> elementEntities) {
        mSortDataSource.deleteAllElements();
        for (ElementEntity elementEntity : elementEntities) {
            mSortDataSource.saveElement(elementEntity);
        }
    }

    @Override
    public void getELements(@NonNull final LoadElementsCallback callback) {
        mSortDataSource.getELements(new LoadElementsCallback() {
            @Override
            public void onElementsLoaded(List<ElementEntity> elementEntities) {

                refreshCache(elementEntities);
                callback.onElementsLoaded(elementEntities);
                //callback.onElementsLoaded(new ArrayList<>(mElements.values()));
            }

            @Override
            public void onDataNotAvailable() {
                ListELements();
                callback.onDataNotAvailable();
            }
        });
        // Respond immediately with cache if available and not dirty
      /*  if (mElements != null && !mCacheIsDirty) {
            callback.onElementsLoaded(new ArrayList<>(mElements.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
        } else {*/
            // Query the local storage if available. If not, query the network.

        //}
    }

    @Override
    public void saveElement(@NonNull ElementEntity elementEntity) {
        mSortDataSource.saveElement(elementEntity);
    }

    @Override
    public void refreshElements(List<ElementEntity> list) {
        //mCacheIsDirty = true;
        refreshLocalDataSource(list);
    }

    @Override
    public void updateElements(String id, int count, String last_touch) {
        mSortDataSource.updateElements(id,count,last_touch);
    }

    @Override
    public void deleteAllElements() {
        mSortDataSource.deleteAllElements();

        if (mElements == null) {
            mElements = new LinkedHashMap<>();
        }
        mElements.clear();
    }
}
