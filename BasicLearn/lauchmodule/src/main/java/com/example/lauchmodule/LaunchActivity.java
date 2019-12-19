package com.example.lauchmodule;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class LaunchActivity extends Activity {

    private static final String TAG = "LAUNCHACTIVITY";

    private List<Class<?>> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        RecyclerView mRecyclerView = findViewById(R.id.main_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this, VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        addItem();
    }

    private void addItem() {

    }
}
