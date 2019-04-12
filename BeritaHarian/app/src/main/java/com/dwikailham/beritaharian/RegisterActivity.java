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
import android.widget.TextView;
import android.widget.Toast;

import com.dwikailham.beritaharian.databases.SqliteDatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    SqliteDatabaseHelper userTable;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nameText = findViewById(R.id.editText_name);
        final EditText usernameText = findViewById(R.id.editText_username);
        final EditText passwordText = findViewById(R.id.editText_password);
        Button registerButton = findViewById(R.id.button_register);
        final TextView linkLogin = findViewById(R.id.link_login);
        userTable = new SqliteDatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                if(userTable.insertData(name,username,password)) {
                    String message = "Buat Akun Sukses!";
//                    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
                    Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
//                    linkLogin.setText("Ketuk untuk kembali ke Login page");
                    Intent intent = new Intent(view.getContext(),MainActivity.class);
                    view.getContext().startActivity(intent);
                }else{
                    String message = "Buat Akun Gagal!";
                    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
                }
                hideKeyboard();
            }
        });

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
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
