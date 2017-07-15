package com.dhiyaulhaqza.rmenggalwaras.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dhiyaulhaqza.rmenggalwaras.R;
import com.dhiyaulhaqza.rmenggalwaras.model.RumahSakit;

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
        setTitle("Tambah Data");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Realm.init(getApplicationContext());

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
        String pasien = etPasien.getText().toString().trim();
        String dokter = selectedDokter;
        String keterangan = etKeterangan.getText().toString().trim();

        if (!(pasien.equals("") || dokter.equals("") || keterangan.equals(""))) {

            RumahSakit rs = new RumahSakit(pasien, dokter, keterangan);
            commitToRealm(rs);
            finish();
        } else {
            Toast.makeText(this, "Semua data wajib diisi !", Toast.LENGTH_SHORT).show();
        }
    }

    private void commitToRealm(RumahSakit rs) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(rs);
        realm.commitTransaction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
