package com.example.hostelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class std_reg_seat extends AppCompatActivity {
    EditText id, pass, name, number;
    Button reg;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_reg_seat);
        FirebaseApp.initializeApp(this);

        databaseReference= FirebaseDatabase.getInstance().getReference("Student");

        id=findViewById(R.id.stdid);
        pass=findViewById(R.id.pass);
        name=findViewById(R.id.name);
        number=findViewById(R.id.phone);
        reg=findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentregistration();
            }

            public void studentregistration() {

                String ids=id.getText().toString().trim();
                String passs=pass.getText().toString().trim();
                String names=name.getText().toString();
                String phns=number.getText().toString().trim();

                if(ids.isEmpty())
                {
                    id.setError("Enter an Student ID");
                    id.requestFocus();

                    return;
                }


                //checking the validity of the password
                if(passs.isEmpty())
                {
                    pass.setError("Enter a password");
                    pass.requestFocus();
                    return;
                }
                if(passs.length()<6)
                {
                    pass.setError("Minimum length of a passwordis 6");
                    pass.requestFocus();
                    return;
                }
                if(names.isEmpty())
                {
                    name.setError("Enter an Student Name");
                    name.requestFocus();
                    return;
                }
                if(phns.isEmpty())
                {
                    number.setError("Enter a phone number");
                    number.requestFocus();
                    return;
                }
                if(phns.length()<11 || phns.length()>11 )
                {
                    number.setError("Length of a number 11");
                    number.requestFocus();
                    return;
                }

                String key= ids;
                Student reg= new Student(ids,names,passs,phns);
                databaseReference.child(key).setValue(reg);
                Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_LONG).show();
                id.setText("");
                pass.setText("");
                name.setText("");
                number.setText("");
            }

        });
    }
}
