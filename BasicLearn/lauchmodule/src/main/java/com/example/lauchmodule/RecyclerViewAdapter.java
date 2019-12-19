package com.example.lauchmodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Activity> mList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List list){
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(mContext, mList.get(position).getClass());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mText;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.title);
        }
    }

}
