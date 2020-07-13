package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginAct extends AppCompatActivity {
    DatabaseReference databaseReference,demoRef;
    String admin_id,admin_pass,std_id,std_pass;
    private EditText user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView= findViewById(R.id.usernameid);

        admin_id="admin";
        admin_pass="123456";

        user=(EditText)findViewById(R.id.user_id);
        pass=findViewById(R.id.password);
        Button forget=(Button) findViewById(R.id.fpassbtn);

        final Intent intent= getIntent();
        String str=intent.getStringExtra("User");
        textView.setText(str);
        // There is no logic for button
        //str
        String straut="Student";
        Button button= findViewById(R.id.logbtn);
        if(straut.equals(str)) {

            button.setOnClickListener(new View.OnClickListener() {
                @Override


                public void onClick(View v) {

                    final String usr,pswd;
                    usr=user.getText().toString().trim();
                    pswd=pass.getText().toString().trim();


                    databaseReference= FirebaseDatabase.getInstance().getReference();
                    demoRef = databaseReference.child("Student");


                    demoRef.child(usr).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Student data= new Student();
                            data= dataSnapshot.getValue(Student.class);

                            std_id= data.getId().toString().trim();
                            std_pass= data.getPass().toString().trim();

                            //Toast.makeText(loginAct.this,usr,Toast.LENGTH_SHORT).show();
                           // Toast.makeText(loginAct.this,std_id,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(loginAct.this,std_pass,Toast.LENGTH_SHORT).show();

                           // pname.setText(name);

                            if(usr.equals(std_id) && pswd.equals(std_pass))
                            {
                                Toast.makeText(loginAct.this,"Successfully login",Toast.LENGTH_SHORT).show();
                                Intent logintnt=new Intent(loginAct.this,StdHomePage.class);
//                        logintnt.putExtra("nam","Reza");
//                        logintnt.putExtra("phn","01633613553");
//                        logintnt.putExtra("mail","rezabaiust@gmail.com");
                                String str=std_id;
                                logintnt.putExtra("ID",str);
                                startActivity(logintnt);

                            }
                            else
                            {
                                if(!usr.equals(std_id) && !pswd.equals(std_pass))
                                    Toast.makeText(loginAct.this, "Both are wrong", Toast.LENGTH_SHORT).show();
                                else if (!usr.equals(std_id))
                                {
                                    Toast.makeText(loginAct.this,"user id wrong", Toast.LENGTH_SHORT).show();
                                    user.requestFocus();
                                    return;
                                }


                                else
                                {

                                    Toast.makeText(loginAct.this,"Password wrong",Toast.LENGTH_SHORT).show();
                                    pass.requestFocus();
                                    return;
                                }
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(loginAct.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            });
        }
        else{
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   // adminlogin();
                    String usr,pswd;
                    usr=user.getText().toString().trim();
                    pswd=pass.getText().toString().trim();


                    //DEBUG URPOSE
                    //DELETE IT
                   // usr="admin";
                  //  pswd="123456";


                    if(usr.equals(admin_id) && pswd.equals(admin_pass))
                    {
                        Toast.makeText(loginAct.this,"Successfully login",Toast.LENGTH_SHORT).show();
                        Intent logintnt=new Intent(loginAct.this,admin_fromat.class);
                        startActivity(logintnt);
//                        logintnt.putExtra("nam","Reza");
//                        logintnt.putExtra("phn","01633613553");
//                        logintnt.putExtra("mail","rezabaiust@gmail.com");


                    }
                    else
                    {
                        if(!usr.equals(admin_id) && !pswd.equals(admin_pass))
                            Toast.makeText(loginAct.this, "Both are wrong", Toast.LENGTH_SHORT).show();
                        else if (!usr.equals(admin_id))
                        {
                            Toast.makeText(loginAct.this,"user id wrong", Toast.LENGTH_SHORT).show();
                            user.requestFocus();
                            return;
                        }


                        else
                        {

                            Toast.makeText(loginAct.this,"Password wrong",Toast.LENGTH_SHORT).show();
                            pass.requestFocus();
                            return;
                        }
                    }
                }



            });

            ActivityCompat.requestPermissions(loginAct.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);


        }

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    /*
                    Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:?subject=" + "Forgotten Password"+ "&body=" + "password:123123" + "&to=" + "sayanta04@gmail.com");
                    mailIntent.setData(data);
                    startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                   Toast.makeText(loginAct.this,"Mail Sent!",Toast.LENGTH_SHORT).show();
*/
/*
                    try {
                        GMailSender sender = new GMailSender("username@gmail.com", "password");
                        sender.sendMail("This is Subject",
                                "This is Body",
                                "user@gmail.com",
                                "user@yahoo.com");
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }

 */
                Toast.makeText(loginAct.this, "FORGET", Toast.LENGTH_SHORT).show();

                sendEmail();
/*
                    String messageToSend = "this is a message";
                    String number = "8801515675348";

                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, messageToSend, null,null);
                    Toast.makeText(loginAct.this,"Done!!",Toast.LENGTH_SHORT).show();*/
            }
        });

    }













    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sayanta04@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Forget PAssword");
        intent.putExtra(Intent.EXTRA_TEXT,"password:123123");
        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent,"Select Email Apps"));
    }
}
