package com.dwikailham.beritaharian;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dwikailham.beritaharian.databases.SqliteDatabaseHelper;

@SuppressWarnings("ALL")
public class InsertPostActivity extends AppCompatActivity {
    SqliteDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post);
        db = new SqliteDatabaseHelper(this);

        final EditText eTjudul = findViewById(R.id.editText_judul);
        final EditText eTperusahaan = findViewById(R.id.editText_perusahaan);
        final EditText eTpersyaratan = findViewById(R.id.editText_persyaratan);
//        final EditText eTgaji = findViewById(R.id.editText_gaji);
        final EditText eTtanggal = findViewById(R.id.editText_tanggal);
        final Spinner color = findViewById(R.id.sp_color);
        color.setPrompt("Pilih Thumbnail");
        Button bPost = findViewById(R.id.button_post);

        bPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

//                Toast.makeText(view.getContext(),icon,Toast.LENGTH_LONG).show();
                boolean status = db.insertDataPosting(eTjudul.getText().toString(), eTperusahaan.getText().toString(), eTpersyaratan.getText().toString(),
                         eTtanggal.getText().toString(), icon);
                if(status){

                    Intent intent = new Intent(view.getContext(), PostingActivity.class);
                    startActivity(intent);
                    finish();
                    String message = "Post berhasil dibuat!";
                    Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
                }else{
                    String message = "Post gagal dibuat!";
                    Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
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
