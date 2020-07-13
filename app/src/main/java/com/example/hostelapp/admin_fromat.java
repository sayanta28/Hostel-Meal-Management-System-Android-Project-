package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelapp.testTab.TabMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class admin_fromat extends AppCompatActivity {

    private static final String TAG = "admin_fromat";
    
    Button std_reg,notice,log;
    TextView brekfast,lunch,dinner,MonthlyBill;
    EditText noticetxt;
    DatabaseReference databaseReference,demoRef, demoRefBill;
    Button std_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_fromat);
        std_reg=findViewById(R.id.regstdbtn);
        notice=findViewById(R.id.noticebtn);
        noticetxt=findViewById(R.id.noticeabout);
        brekfast=findViewById(R.id.breakfastvalue);
        lunch=findViewById(R.id.launchvalue);
        dinner=findViewById(R.id.dinnervalue);
        MonthlyBill=findViewById(R.id.billvalue);
        log=findViewById(R.id.logout);
        std_info=findViewById(R.id.info);

        String dateNow;


        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateNow=dateFormat.format(date);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        demoRef = databaseReference.child("Meal/"+month_name+"/"+dateNow);
        demoRefBill= databaseReference.child("Meal/"+month_name);


        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminmodelclass data= new adminmodelclass();
                try {
                    data = dataSnapshot.getValue(adminmodelclass.class);
                    int bf = data.getBreakfast();
                    int ln = data.getLunch();
                    int dn = data.getDinner();

                    brekfast.setText("" + bf);
                    lunch.setText("" + ln);
                    dinner.setText("" + dn);
                }catch (Exception e){
                    Log.i("Error:P","onDatachange");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        demoRefBill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminmodelclass data=new adminmodelclass();
                data=dataSnapshot.getValue(adminmodelclass.class);
                int TotalMonthlyBill=data.getTotalMonthlyBill();
                MonthlyBill.setText(""+TotalMonthlyBill);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        databaseReference= FirebaseDatabase.getInstance().getReference("Notice");

        std_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomePage = new Intent(admin_fromat.this, std_reg_seat.class);
                startActivity(HomePage);
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=noticetxt.getText().toString();
                databaseReference.setValue(text);
                Toast.makeText(getApplicationContext(),"Sucessfully Posted",Toast.LENGTH_LONG).show();
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(admin_fromat.this, MainActivity.class);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(logout);

                //startActivity(logoutintent);
                //finish();

                finish();

            }
        });

        std_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "onClick: INFO");
                Toast.makeText(getApplicationContext(),"Under Construction",Toast.LENGTH_LONG).show();
/*
                Intent info= new Intent(admin_fromat.this, TabMainActivity.class);
//                Intent info= new Intent(admin_fromat.this,Details_Activity.class);
                startActivity(info);

 */
            }
        });

    }
}
