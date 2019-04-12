package com.dwikailham.beritaharian;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dwikailham.beritaharian.databases.SqliteDatabaseHelper;
import com.dwikailham.beritaharian.models.Session;
import com.dwikailham.beritaharian.models.Settings;

public class MainActivity extends AppCompatActivity{
    public Settings settings;
    public Session session;
    SqliteDatabaseHelper db;

    private void moveActivity() {
        if(session.isLogin()){
            Intent intent = new Intent(this, PostingActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        db = new SqliteDatabaseHelper(this);

        settings = new Settings(this);
        session = new Session(settings);

        moveActivity();

        final EditText usernameText = findViewById(R.id.text_username);
        final EditText passwordText = findViewById(R.id.text_password);
        Button loginButton = findViewById(R.id.button_login);
        TextView linkSignUp = findViewById(R.id.link_signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                onLoginClicked(view, username, password);
            }
        });

        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLinkRegisterClicked();
            }
        });
    }

    public void onLoginClicked(View view, String username, String password) {
        String message = "Authentication failed";
        Cursor res = db.getUserData(username,password);
        if(res.getCount() > 0) {
            res.moveToFirst();
            message = "Welcome "+res.getString(res.getColumnIndex("NAMA"));
            session.setUser(res.getString(res.getColumnIndex("ID")));
            res.close();
        }
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
//        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        moveActivity();
    }


    public void onLinkRegisterClicked() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }



}
