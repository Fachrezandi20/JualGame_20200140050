package com.example.jualgame.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jualgame.R;
import com.example.jualgame.helper.DataHelp;

import java.util.List;

public class BeliItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nama, email, no_hp, jml;
    Button selesai;

    String sNama, sEmail, sNo, sItem, sJml;
    int iLama, iHarga;
    double dTotal;

    private Spinner spinner;
    DataHelp dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli);

        dbHelper = new DataHelp(this);

        spinner = findViewById(R.id.spinner);
        selesai = findViewById(R.id.selesaiHitung);
        nama = findViewById(R.id.eTNama);
        email = findViewById(R.id.eTEmail);
        no_hp = findViewById(R.id.eTHP);
        jml = findViewById(R.id.eTitem);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNama = nama.getText().toString();
                sEmail = email.getText().toString();
                sNo = no_hp.getText().toString();
                sJml = jml.getText().toString();
                if (sNama.isEmpty() || sEmail.isEmpty() || sNo.isEmpty() || sJml.isEmpty()) {
                    Toast.makeText(BeliItemActivity.this, "(*) tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (sItem.equals("Dota#1")) {
                    iHarga = 400000;
                } else if (sItem.equals("Dota2")) {
                    iHarga = 400000;
                } else if (sItem.equals("Dota#3")) {
                    iHarga = 400000;
                } else if (sItem.equals("Dota#4")) {
                        iHarga = 1500000;
                } else if (sItem.equals("Csgo#1")) {
                    iHarga = 450000;
                } else if (sItem.equals("Csgo#2")) {
                    iHarga = 500000;
                } else if (sItem.equals("Csgo#3")) {
                    iHarga = 550000;
                } else if (sItem.equals("ML#1")) {
                    iHarga = 550000;
                } else if (sItem.equals("ML#2")) {
                    iHarga = 700000;
                }

                iLama = Integer.parseInt(sJml);
                dTotal = iHarga ;

                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO pembeli (nama, email, no_hp) VALUES ('" +
                        sNama + "','" +
                        sEmail + "','" +
                        sNo + "');");
                dbH.execSQL("INSERT INTO beli (item, nama, nmitm, total) VALUES ('" +
                        sItem + "','" +
                        sNama + "','" +
                        iLama + "','" +
                        dTotal + "');");
                PembeliActivity.m.RefreshList();
                finish();

            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbSewaMobl);
        toolbar.setTitle("Beli Item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadSpinnerData() {
        DataHelp db = new DataHelp(getApplicationContext());
        List<String> categories = db.getAllCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sItem = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}