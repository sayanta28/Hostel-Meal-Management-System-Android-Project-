package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Std_Profile extends AppCompatActivity {


    DatabaseReference databaseReference,demoRef,demoRefStd;

    TextView user,name,phone,brvalue,lnvalue,dnvalue,bill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std__profile);

        Intent intent= getIntent();
        String str=intent.getStringExtra("profile");

        String dateNow;

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateNow=dateFormat.format(date);
        
        user=findViewById(R.id.uid);
        name= findViewById(R.id.name);
        phone=findViewById(R.id.phn);
        brvalue=findViewById(R.id.breakfastvalue);
        lnvalue=findViewById(R.id.launchvalue);
        dnvalue=findViewById(R.id.dinnervalue);
        bill=findViewById(R.id.billvalue);
        databaseReference= FirebaseDatabase.getInstance().getReference();


        demoRef = databaseReference.child("Student");


        demoRef.child(str).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student data= new Student();
                data= dataSnapshot.getValue(Student.class);
                String id=data.getId();
                String name1=data.getName();
                String num=data.getNumber();
                user.setText("User Id: "+id);
                name.setText("Name: "+name1);
                phone.setText("Phone: "+num);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        demoRefStd =databaseReference.child("Student/"+str+"/Meal/"+month_name);

        demoRefStd.child("TotalMonthlyBill").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    String stringToConvert = String.valueOf(dataSnapshot.getValue());

                    bill.setText(stringToConvert);
                }catch(Exception e){
                    Log.i("Error", "onDataChange: ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
