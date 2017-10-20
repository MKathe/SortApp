package com.kath.sortapp.presentation.sort.listener;


import com.kath.sortapp.data.entities.ElementEntity;

/**
 * Created by katherine on 24/04/17.
 */

public interface ElementItem {

    void clickItem(ElementEntity elementEntity);

    void deleteItem(ElementEntity elementEntity, int position);
}
