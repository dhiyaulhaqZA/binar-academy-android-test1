package com.dhiyaulhaqza.rmenggalwaras.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.dhiyaulhaqza.rmenggalwaras.R;
import com.dhiyaulhaqza.rmenggalwaras.adapter.RumahSakitAdapter;
import com.dhiyaulhaqza.rmenggalwaras.model.RumahSakit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerView.Adapter adapter;
    private List<RumahSakit> rumahSakitList = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }

    private void setupView() {
        fab = (FloatingActionButton) findViewById(R.id.fab_main_add);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RumahSakitAdapter(rumahSakitList);
        recyclerView.setAdapter(adapter);
    }

    public void fabAdd(View view) {
        startActivity(new Intent(this, EditorActivity.class));
    }
}
