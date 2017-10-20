package com.kath.sortapp.presentation.sort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kath.sortapp.R;
import com.kath.sortapp.data.entities.ElementEntity;
import com.kath.sortapp.presentation.sort.listener.ElementItem;
import com.kath.sortapp.presentation.sort.listener.OnClickListListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katherine on 14/10/17.
 */

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> implements OnClickListListener {



    private List<ElementEntity> list;
    private ElementEntity item;
    private Context context;
    private ElementItem elementItem;


    public SortAdapter(List<ElementEntity> list, Context context, ElementItem schedulesItem) {
        this.list = list;
        //setStatus();
        this.context = context;
        this.elementItem = schedulesItem;
    }


    public SortAdapter(ElementEntity item, Context context, ElementItem elementItem) {
        this.list = new ArrayList<>();
        this.item = item;
        this.context = context;
        this.elementItem = elementItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_element, parent, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ElementEntity elementEntity = list.get(position);
        if (elementEntity == null) {
            return;
        }
        holder.tv_text.setText(String.valueOf(elementEntity.getCount()));
        if (elementEntity.getColor().equals("ROJO")){
            holder.item_element.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
        if (elementEntity.getColor().equals("AZUL")){
            holder.item_element.setBackgroundColor(context.getResources().getColor(R.color.blue_facebook));
        }

        if (elementEntity.getColor().equals("VERDE")){
            holder.item_element.setBackgroundColor(context.getResources().getColor(R.color.green));
        }


    }
    public void setItems(List<ElementEntity> elementEntities) {
        /*//this.list.clear();
        if (elementEntity != null) {
            this.list.add(elementEntity);
        }
        notifyDataSetChanged();*/
        list = elementEntities;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(int position) {

        ElementEntity elementEntity = list.get(position);
        elementItem.clickItem(elementEntity);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout item_element;
        private TextView tv_text;

        private OnClickListListener onClickListListener;

        ViewHolder(View itemView, OnClickListListener onClickListListener) {
            super(itemView);
            this.onClickListListener = onClickListListener;
            this.itemView.setOnClickListener(this);

            item_element = itemView.findViewById(R.id.item_element);
            tv_text = itemView.findViewById(R.id.tv_text);
        }

        @Override
        public void onClick(View v) {
            onClickListListener.onClick(getAdapterPosition());
        }
    }
}
