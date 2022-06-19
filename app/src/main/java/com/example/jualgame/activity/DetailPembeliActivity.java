package com.example.jualgame.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jualgame.R;
import com.example.jualgame.helper.DataHelp;

import java.util.Objects;

public class DetailPembeliActivity extends AppCompatActivity {

    String sNama, Semail, sHP, sMerk, sHarga;
    int iLama;
    double dTotal;

    protected Cursor cursor;
    DataHelp dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembelian);

        dbHelper = new DataHelp(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from pembeli, gameitm, beli where pembeli.nama = beli.nama AND gameitm.item = beli.item AND pembeli.nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            sNama = cursor.getString(0);
            Semail = cursor.getString(1);
            sHP = cursor.getString(2);
            sMerk = cursor.getString(3);
            sHarga = cursor.getString(4);
            iLama = cursor.getInt(8);
            dTotal = cursor.getDouble(9);
        }

        TextView tvNama = findViewById(R.id.HNama);
        TextView tvAlamat = findViewById(R.id.HAlamat);
        TextView tvHP = findViewById(R.id.HTelp);

        TextView tvMerk = findViewById(R.id.Hgame);
        TextView tvHarga = findViewById(R.id.HHarga);


        tvNama.setText("     " + sNama);
        tvAlamat.setText("     " + Semail);
        tvHP.setText("     " + sHP);

        tvMerk.setText("     " + sMerk);
        tvHarga.setText("     Rp. " + sHarga);


        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailPembeli);
        toolbar.setTitle("Detail PembeliActivity");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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