package com.dwikailham.beritaharian;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dwikailham.beritaharian.databases.SqliteDatabaseHelper;
import com.dwikailham.beritaharian.models.Posting;

@SuppressWarnings("ALL")
public class DetailPostingActivity extends AppCompatActivity {
    private Posting data;
    SqliteDatabaseHelper db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_update:
                final Intent intent = new Intent(this, UpdatePostActivity.class);
                intent.putExtra("DATA_POSTING",data);
                startActivity(intent);
                return true;

            case R.id.action_delete:
                Snackbar.make(getWindow().getDecorView(),"Mau hapus Posting ini?",Snackbar.LENGTH_LONG)
                        .setAction("Konfirmasi", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(db.deleteDataPosting(data.getId()) > 0){
                                    String message = "Data berhasil diupdate!";
                                    Toast.makeText(v.getContext(),message,Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(v.getContext(), PostingActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        }).show();
                return true;

        }
        return onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_posting);
        db = new SqliteDatabaseHelper(this);
        Intent intent = getIntent();
        //String judul = intent.getStringExtra("JUDUL");
        data = (Posting) intent.getSerializableExtra("DATA_POSTING");
        TextView tJudul = findViewById(R.id.text_judul);
        TextView tDeskripsi = findViewById(R.id.text_deskripsi);
        TextView tPersyaratan = findViewById(R.id.text_persyaratan);
//        TextView tGaji = findViewById(R.id.text_gaji);
        TextView tTanggal = findViewById(R.id.text_tanggal);
        ImageView icon = findViewById(R.id.icon);

        tJudul.setText(data.getJudul());
        tDeskripsi.setText(data.getPerusahaan());
        tPersyaratan.setText(data.getPersyaratan());
//        tGaji.setText(String.valueOf(data.getGaji()));
        tTanggal.setText(data.getTanggal());
        int img = getResources().getIdentifier(data.getGambar(),"drawable",getPackageName());
        icon.setImageResource(img);
    }
}
