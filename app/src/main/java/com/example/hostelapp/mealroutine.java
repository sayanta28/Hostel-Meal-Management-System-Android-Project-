package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mealroutine extends AppCompatActivity {

    //DatabaseReference databaseReference,demoRef;
    TextView br,lu,dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealroutine);

        br=findViewById(R.id.bf);
        lu=findViewById(R.id.ln);
        dr=findViewById(R.id.dn);

    }
}
