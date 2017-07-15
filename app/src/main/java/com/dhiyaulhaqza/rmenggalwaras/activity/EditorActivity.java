package com.dhiyaulhaqza.rmenggalwaras.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.dhiyaulhaqza.rmenggalwaras.R;

import io.realm.Realm;

public class EditorActivity extends AppCompatActivity {

    private Realm realm;
    private EditText etPasien;
    private Spinner spDokter;
    private EditText etKeterangan;
    private FloatingActionButton fabSave;
    private String selectedDokter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        setupView();
    }

    private void setupView() {
        etPasien = (EditText) findViewById(R.id.et_editor_passien);
        etKeterangan = (EditText) findViewById(R.id.et_editor_keterangan);
        fabSave = (FloatingActionButton) findViewById(R.id.fab_editor_save);

        setupSpinner();
    }

    private void setupSpinner() {
        spDokter = (Spinner) findViewById(R.id.sp_editor_dokter);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.dokter_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spDokter.setAdapter(adapter);

        spDokter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDokter = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedDokter = getString(R.string.dokter_1);
            }
        });
    }

    public void fabSave(View view) {
        finish();
    }
}
