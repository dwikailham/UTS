package com.dwikailham.beritaharian;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dwikailham.beritaharian.databases.SqliteDatabaseHelper;
import com.dwikailham.beritaharian.models.Posting;

@SuppressWarnings("ALL")
public class UpdatePostActivity extends AppCompatActivity {
    private Posting data;
    SqliteDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);
        db = new SqliteDatabaseHelper(this);

        final EditText eTjudul = findViewById(R.id.editText_judul);
        final EditText eTdeskripsi = findViewById(R.id.editText_perusahaan);
        final EditText eTpersyaratan = findViewById(R.id.editText_persyaratan);
//        final EditText eTgaji = findViewById(R.id.editText_gaji);
        final EditText eTtanggal = findViewById(R.id.editText_tanggal);
        final Spinner color = findViewById(R.id.sp_color);
        final TextView msg = findViewById(R.id.text_message);
        Button bUpdate = findViewById(R.id.button_update);

        Intent intent = getIntent();
        data = (Posting) intent.getSerializableExtra("DATA_POSTING");

        eTjudul.setText(data.getJudul());
        eTdeskripsi.setText(data.getPerusahaan());
        eTpersyaratan.setText(data.getPersyaratan());
//        eTgaji.setText(String.valueOf(data.getGaji()));
        eTtanggal.setText(data.getTanggal());
        int pos = 0;
        if(data.getGambar().equalsIgnoreCase("logo_berita")){
            pos = 0;
        }else if(data.getGambar().equalsIgnoreCase("icon_blue")){
            pos = 1;
        }else if(data.getGambar().equalsIgnoreCase("icon_white")){
            pos = 2;
        }else if(data.getGambar().equalsIgnoreCase("areama")){
            pos = 3;
        }else if(data.getGambar().equalsIgnoreCase("icon_black")){
            pos = 4;
        }

        color.setSelection(pos);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String icon = color.getSelectedItem().toString();
                if(icon.equalsIgnoreCase("berita")){
                    icon = "logo_berita";
                }else if(icon.equalsIgnoreCase("blue")){
                    icon = "icon_blue";
                }else if(icon.equalsIgnoreCase("arema")){
                    icon = "areama";
                }else if(icon.equalsIgnoreCase("white")){
                    icon = "icon_white";
                }else if(icon.equalsIgnoreCase("black")){
                    icon = "icon_black";
                }
                boolean status = db.updateDataPosting(data.getId(),eTjudul.getText().toString(), eTdeskripsi.getText().toString(), eTpersyaratan.getText().toString(),
                        eTtanggal.getText().toString(), icon);
                if(status){
//                    String message = "Data berhasil diupdate! Klik untuk kembali ke Detail Post.";
//                    msg.setText(message);
//                    msg.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(v.getContext(), DetailPostingActivity.class);
//                            startActivity(intent);
//                            finish();
//                            String message = "Data berhasil diupdate!";
//                            Toast.makeText(v.getContext(),message,Toast.LENGTH_LONG).show();
//                        }
//                    });
                    Cursor res = db.getPostingData(data.getId());
                    res.moveToFirst();
//                    String id = res.getString(res.getColumnIndex("ID"));
//                    String judul = res.getString(res.getColumnIndex("JUDUL"));
//                    String perusahaan = res.getString(res.getColumnIndex("DESKRIPSI"));
//                    String persyaratan = res.getString(res.getColumnIndex("PERSYARATAN"));
//                    int gaji = res.getInt(res.getColumnIndex("GAJI"));
//                    String tanggal = res.getString(res.getColumnIndex("TANGGAL"));
//                    String gambar = res.getString(res.getColumnIndex("GAMBAR"));
//
//                    data = new Posting(id,judul,perusahaan,persyaratan,gaji,tanggal,gambar);

                    Intent intent = new Intent(v.getContext(), PostingActivity.class);
//                    intent.putExtra("DATA_POSTING",data);
                    startActivity(intent);
                    finish();
                    String message = "Data berhasil diupdate!";
                    Toast.makeText(v.getContext(),message,Toast.LENGTH_LONG).show();
                }else{
                    String message = "Data gagal diupdate!";
                    Snackbar.make(v,message,Snackbar.LENGTH_SHORT).show();
                    msg.setText(message);
                }
                hideKeyboard();
            }
        });
    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
