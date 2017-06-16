package com.study.signupdata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 5/17/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.RecyclerViewHolder>{
  List<Member> listInsideRecyclerAdapter = new ArrayList<>();
    Context context;

    public RecyclerAdapter(Context applicationContext, List<Member> listInsideRecyclerAdapter) {
        this.listInsideRecyclerAdapter = listInsideRecyclerAdapter;
    }




    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Member mPosition = listInsideRecyclerAdapter.get(position);
        holder.Tx_id.setText(mPosition.getId());
        holder.Tx_name.setText(mPosition.getName());
        holder.Tx_address.setText(mPosition.getAddress());
    }

    @Override
    public int getItemCount() {
        return listInsideRecyclerAdapter.size();

    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView Tx_id;
        TextView Tx_name;
        TextView Tx_address;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            Tx_id = (TextView) itemView.findViewById(R.id.number);
            Tx_name = (TextView) itemView.findViewById(R.id.name);
            Tx_address = (TextView) itemView.findViewById(R.id.address);
        }
    }
}

