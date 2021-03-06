package com.example.jualgame.activity;

import android.R.layout;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jualgame.R;
import com.example.jualgame.helper.DataHelp;

public class DaftarItemActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView1;
    Menu menu;
    protected Cursor cursor;
    DataHelp dbcenter;
    public static DaftarItemActivity m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itm);

        m = this;
        dbcenter = new DataHelp(this);

        RefreshList();
        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbInfoMbl);
        toolbar.setTitle("Informasi Daftar Item");
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

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM gameitm", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }
        ListView1 = findViewById(R.id.listView1);
        ListView1.setAdapter(new ArrayAdapter(this, layout.simple_list_item_1, daftar));
        ListView1.setSelected(true);
        ListView1.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent i = new Intent(DaftarItemActivity.this, DetailItemActivity.class);
                i.putExtra("item", selection);
                startActivity(i);
            }
        });

        ((ArrayAdapter) ListView1.getAdapter()).notifyDataSetInvalidated();

    }
}