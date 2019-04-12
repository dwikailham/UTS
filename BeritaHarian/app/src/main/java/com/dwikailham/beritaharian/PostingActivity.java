package com.dwikailham.beritaharian;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dwikailham.beritaharian.adapters.PostingAdapter;
import com.dwikailham.beritaharian.databases.SqliteDatabaseHelper;
import com.dwikailham.beritaharian.models.Constant;
import com.dwikailham.beritaharian.models.Posting;
import com.dwikailham.beritaharian.models.Session;
import com.dwikailham.beritaharian.models.Settings;

import java.util.ArrayList;
import java.util.List;

public class PostingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostingAdapter adapter;
    public Session session;
    public Settings settings;
    private List<Posting> postings = new ArrayList<>();
    SqliteDatabaseHelper db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_show_list:
                displayAsList();
                session.setLayout(Constant.LAYOUT_MODE_LIST);
                //Toast.makeText(getActivity(), "Layout session Set : "+Constant.LAYOUT_MODE_LIST, Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_show_grid:
                displayAsGrid();
                session.setLayout(Constant.LAYOUT_MODE_GRID);
                //Toast.makeText(getActivity(), "Layout session Set : "+Constant.LAYOUT_MODE_GRID, Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_logout:
                onLogoutClick();
                return true;

            case R.id.action_insert:
                Intent intent = new Intent(this,InsertPostActivity.class);
                startActivity(intent);
                return true;
        }
        return onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);
        settings = new Settings(this);
        session = new Session(settings);
        db = new SqliteDatabaseHelper(this);
        // Untuk Mengecek isi session dari layout
        // Toast.makeText(getActivity(), Integer.toString(settings.preferences.getInt(Constant.LAYOUT_MODE,0)), Toast.LENGTH_LONG).show();
        // Untuk Mengecek isi session dari user
        //Toast.makeText(getActivity(), session.getUser(), Toast.LENGTH_LONG).show();

        recyclerView = findViewById(R.id.rv_notes);

        Cursor res = db.getAllDataPosting();
//        String message = Integer.toString(res.getCount());
//        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
        if (res.moveToFirst()){
            do{
                String id = res.getString(res.getColumnIndex("ID"));
                String judul = res.getString(res.getColumnIndex("JUDUL"));
                String deskripsi = res.getString(res.getColumnIndex("PERUSAHAAN"));
                String persyaratan = res.getString(res.getColumnIndex("PERSYARATAN"));
//                int gaji = res.getInt(res.getColumnIndex("GAJI"));
                String tanggal = res.getString(res.getColumnIndex("TANGGAL"));
                String gambar = res.getString(res.getColumnIndex("GAMBAR"));
                postings.add(new Posting(id,judul,deskripsi,persyaratan,tanggal,gambar));
            }while(res.moveToNext());
        }

//        Cursor cur = db.getUserDataById(session.getUser());
//        cur.moveToFirst();
//        String name = "Welcome "+cur.getString(cur.getColumnIndex("NAMA"));
//        Toast.makeText(this,name,Toast.LENGTH_LONG).show();

        adapter = new PostingAdapter(this, postings);
        recyclerView.setAdapter(adapter);
        if(session.getLayout() == Constant.LAYOUT_MODE_LIST)
            displayAsList();
        else
            displayAsGrid();
    }

    private void displayAsList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setLayout(Constant.LAYOUT_MODE_LIST);
    }

    private void displayAsGrid() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setLayout(Constant.LAYOUT_MODE_GRID);
    }

    public void onLogoutClick() {
        session.doLogout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
