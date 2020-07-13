package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class notice extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView Noticetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Noticetxt=findViewById(R.id.NoticeText);



        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Notice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    String stringToConvert = String.valueOf(dataSnapshot.getValue());


                    Noticetxt.setText(stringToConvert);

                }catch (Exception e){
                    Log.i(" Error Exception", "onDataNotice: ");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
