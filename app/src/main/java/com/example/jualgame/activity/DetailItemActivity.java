package com.example.jualgame.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jualgame.R;
import com.example.jualgame.helper.DataHelp;

public class DetailItemActivity extends AppCompatActivity {

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelp dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelp(this);
        Intent intent = getIntent();

        String merk = terima.getString("item");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from gameitm where item = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Dota#1")) {
            sGambar = "dotaimmo";
        } else if (sMerk.equals("Dota#2")) {
            sGambar = "dotaimmo2";
        } else if (sMerk.equals("Dota#3")) {
            sGambar = "dotaimmo3";
        } else if (sMerk.equals("Dota#4")) {
            sGambar = "dotaarcana";
        } else if (sMerk.equals("Csgo#1")) {
            sGambar = "csgoe";
        } else if (sMerk.equals("Csgo#2")) {
            sGambar = "csgoq";
        } else if (sMerk.equals("Csgo#3")) {
            sGambar = "csgoi";
        } else if (sMerk.equals("ML#1")) {
            sGambar = "mlepic";
        } else if (sMerk.equals("ML#2")) {
            sGambar = "mllegend";
        }

        ImageView ivGambar = findViewById(R.id.ivItem);
        TextView tvMerk = findViewById(R.id.JItem);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailItm);
        toolbar.setTitle("Detail Item");
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

}
