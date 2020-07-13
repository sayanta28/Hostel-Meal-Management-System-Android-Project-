package com.example.hostelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String str="DATA";
        Button admin= findViewById(R.id.admin);
        Button stduent= findViewById(R.id.student);



        admin.setOnClickListener(this);
        stduent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String str;
        switch (v.getId())
        {

            case R.id.admin: {
                Intent login = new Intent(MainActivity.this, loginAct.class);
                str = "Admin";
                login.putExtra("User", str);
                startActivity(login);
                break;
            }
            case R.id.student:
            {
                Intent login = new Intent(MainActivity.this, loginAct.class);
                str="Student";
                login.putExtra("User",str);
                startActivity(login);
                break;
            }


        }

    }
}
