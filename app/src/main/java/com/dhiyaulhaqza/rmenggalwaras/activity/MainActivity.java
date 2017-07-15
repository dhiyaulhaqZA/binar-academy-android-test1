package com.dhiyaulhaqza.rmenggalwaras.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dhiyaulhaqza.rmenggalwaras.R;
import com.dhiyaulhaqza.rmenggalwaras.adapter.RumahSakitAdapter;
import com.dhiyaulhaqza.rmenggalwaras.model.RumahSakit;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvEmptyView;
    private FloatingActionButton fab;
    private RecyclerView.Adapter adapter;
    private List<RumahSakit> rumahSakitList = new ArrayList<>();
    private Realm myRealm;
    private RealmResults<RumahSakit> rumahSakits;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        setupView();
        setupRealm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData(rumahSakits);
    }

    private void updateData(List<RumahSakit> rumahSakits) {
        rumahSakitList.clear();
        rumahSakitList.addAll(rumahSakits);
        adapter.notifyDataSetChanged();
        if (rumahSakitList.size() > 0) {
            tvEmptyView.setVisibility(View.GONE);
        }
    }

    private void setupRealm() {
        myRealm = Realm.getDefaultInstance();
        rumahSakits = myRealm.where(RumahSakit.class)
                .findAll();


        rumahSakits.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<RumahSakit>>() {
            @Override
            public void onChange(RealmResults<RumahSakit> collection, OrderedCollectionChangeSet changeSet) {
                rumahSakitList.clear();
                rumahSakitList.addAll(collection);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupView() {
        tvEmptyView = (TextView) findViewById(R.id.tv_empty_view);
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
