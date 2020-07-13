package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StdHomePage extends AppCompatActivity {
    private static final String TAG = "StdHomePage";
    CheckBox BreakFast,Lunch,Dinner;
    Button mealconf,Routine,Profile,Notice,Logout;
    TextView Submitted;
    String name;

    DatabaseReference databaseReference,demoRefStd,demoRefAdm,demoRefStdBill,demoRefAdmBill,demoRefStdTMeal,DemoListShow,demoRefStdCost;

    TextView code;
    int countB=0,countL=0,countD=0,totalM=0,bf=0,ln=0,dn=0,totalD=0;

    int TotalRawDataMeal;

    public int getTotalRawDataMeal() {
        return TotalRawDataMeal;
    }

    public void setTotalRawDataMeal(final int totalRawDataMeal) {
        Log.d(TAG, "setTotalRawDataMeal: " + totalRawDataMeal);
        TotalRawDataMeal = totalRawDataMeal;
    }


    public static int TotalCheck=0;



    int cost=0;
    int costB=30,costL=55,costD=45;
    int MonthlyBill=0; //All Students Total Monthly Bill



    int bill;
    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    String dateNow;
    //Intent intent= getIntent();
   // String str=intent.getStringExtra("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_home_page);

        MyAsyncTask myAsyncTask;



        code=findViewById(R.id.code);
        Intent intent= getIntent();
        final String str=intent.getStringExtra("ID");
        code.setText("Profile ID :"+str);

        // Getting System Date

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateNow=dateFormat.format(date);




        //Find Path


        databaseReference= FirebaseDatabase.getInstance().getReference();
        DemoListShow= databaseReference.child("MealStudentList/"+dateNow);
        demoRefStd = databaseReference.child("Student/"+str+"/Meal/"+month_name+"/"+dateNow);
        demoRefStdBill=databaseReference.child("Student/"+str+"/Meal/"+month_name);
        demoRefStdCost =databaseReference.child("Student/"+str+"/Meal/"+month_name);

        demoRefStdTMeal= databaseReference.child("Student/"+str+"/Meal/"+month_name+"/"+dateNow);

        demoRefAdm= databaseReference.child("Meal/"+month_name+"/"+dateNow);
        demoRefAdmBill=databaseReference.child("Meal/"+month_name);


        //Start
        myAsyncTask=new MyAsyncTask();


        myAsyncTask.execute(TotalRawDataMeal);



       /// demorefadm();

       /// demorefadmbill();

       /// demorefstdtmeal();


//Stop

        BreakFast=findViewById(R.id.breakfast);
        Lunch=findViewById(R.id.lunch);
        Dinner=findViewById(R.id.dinner);
        mealconf=findViewById(R.id.mealcm);
        Routine= findViewById(R.id.routine);
        Profile=findViewById(R.id.probtn);
        Notice=findViewById(R.id.notice);
        Logout=findViewById(R.id.log);
        Submitted=findViewById(R.id.submit);

        //mealconf.setVisibility(View.INVISIBLE);

      //  Toast.makeText(StdHomePage.this, "Total Meal " + TotalCheck, Toast.LENGTH_SHORT).show();



            //CHECKBOXLISTER()
        checkboxlisten();


           // Toast.makeText(StdHomePage.this, "\nI am here", Toast.LENGTH_SHORT).show();




        //totalM=totalM+bf+ln+dn;

        mealconf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalM=bf+ln+dn+totalD; // 0+1+1+3=5
                MonthlyBill=MonthlyBill+cost; //100+130=230

                bf=bf+countB; //0+1=1
                ln=ln+countL; //1+1=2
                dn=dn+countD; //1+1=2

              //  Toast.makeText(StdHomePage.this, "Accurate Meal " +totalM, Toast.LENGTH_SHORT).show();


                demoRefAdm.child("breakfast").setValue(bf); //1
                demoRefAdm.child("lunch").setValue(ln); // 2
                demoRefAdm.child("dinner").setValue(dn); //2
                demoRefAdmBill.child("TotalMonthlyBill").setValue(MonthlyBill); //2307

                demoRefStd.child("breakfast").setValue(countB); //1
                demoRefStd.child("lunch").setValue(countL); //1
                demoRefStd.child("dinner").setValue(countD); //1
                demoRefStd.child("Total").setValue(totalD); //3
                cost=cost+getBill();
                demoRefStdBill.child("TotalMonthlyBill").setValue(cost); //130

                if(countB>0){
                    DemoListShow.child("BreakFast/"+str+"/").setValue(name);
                }
                if(countD>0){
                    DemoListShow.child("Dinner/"+str+"/").setValue(name);
                }
                if(countL>0){
                    DemoListShow.child("Lunch/"+str+"/").setValue(name);
                }






               // demoRef.child("Time").setValue(dateNow);


                Toast.makeText(StdHomePage.this,"Meal submitted. Total Cost: "+cost+"\nTotal Meal: "+totalD ,Toast.LENGTH_SHORT).show();
                mealconf.setVisibility(View.GONE);

            }
        });


        Routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stdhm=new Intent(StdHomePage.this,mealroutine.class);
                startActivity(stdhm);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pro=new Intent(StdHomePage.this,Std_Profile.class);
                pro.putExtra("profile",str);
                startActivity(pro);
            }
        });

        Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notice=new Intent(StdHomePage.this,notice.class);
                startActivity(notice);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // progressBar.setP


                Intent logoutintent = new Intent(StdHomePage.this, MainActivity.class);
                logoutintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutintent);
                finish();

              //  finishActivity(0);
              //  onBackPressed();
               // System.exit(0);



            }
        });




    }

    public void checkboxlisten()
    {
        BreakFast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    countB++; //0+1=1
                    cost = cost + costB; //0+30=30
                    totalD = totalD + countB; //0+1=1
                    //  totalM=totalM+countB;

                    Toast.makeText(StdHomePage.this, "Breakfast Checked and cost is " + cost + "\nTotal :" + totalD, Toast.LENGTH_SHORT).show();

                } else {
                    //   bf=bf-countB;
                    totalD = totalD - countB; //1-1=0
                    countB = countB - 1; //1-1=0
                    cost = cost - costB; //30-30=0

                    Toast.makeText(StdHomePage.this, "Breakfast Unchecked and cost is \n" + cost + "\nTotal :" + totalD, Toast.LENGTH_SHORT).show();

                }
            }
        });

        Lunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    countL++; //0+1=1
                    cost = cost + costL; //0+55=55 || 30+55=85
                    totalD = totalD + countL; // 0+1=1 || 1+1=2

                    //   totalM=totalM+countL+ln;
                    Toast.makeText(StdHomePage.this, "Lunch Checked and cost is " + cost + "\nTotal :" + totalD, Toast.LENGTH_SHORT).show();

                } else {
                    //  ln=ln-countL;
                    totalD = totalD - countL; //1-1=0 || 2-1=1
                    countL--; //1-1=0
                    cost = cost - costL; //85-55=30 || 0+55=55


                    Toast.makeText(StdHomePage.this, "Lunch Unchecked and cost is " + cost + "\nTotal :" + totalD, Toast.LENGTH_SHORT).show();

                }

            }
        });


        Dinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    countD++; //0+1=1
                    cost = cost + costD; //85+45=130
                    totalD = totalD + countD; //2+1=3
                    //   totalM=totalM+countD;

                    Toast.makeText(StdHomePage.this, "Dinner Checked and cost is " + cost + "\nTotal :" + totalD, Toast.LENGTH_SHORT).show();

                } else {
                    //    dn=dn-countD;
                    totalD = totalD - countD; //3-1=2
                    countD--; //1-1=0
                    cost = cost - costD; //130-45=85

                    Toast.makeText(StdHomePage.this, "Dinner Unchecked and cost is " + cost + "\nTotal :" + totalD, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void demodata()
    {
        demoRefStdTMeal.child("Total").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String stringToConvert = String.valueOf(dataSnapshot.getValue());
                    Long convertedLong = Long.parseLong(stringToConvert);

                    //TotalRawDataMeal= convertedLong.intValue();
                    setTotalRawDataMeal(convertedLong.intValue());
                    int x = getTotalRawDataMeal();
                    //Toast.makeText(StdHomePage.this, " TotalRawData=" + TotalRawDataMeal, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(StdHomePage.this, " TotalRawData= " + x, Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    //TotalRawDataMeal=0;
                    Log.d(TAG, "onDataChange: ");
                    //setTotalRawDataMeal(0);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG, "demodata Func()" + getTotalRawDataMeal());
    }
    public void demorefstdtmeal()
    {
        demoRefStdTMeal.child("Total").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(TAG, "onDataChange: is THAT " + dataSnapshot.getValue());
                try {
                    //  Log.d(TAG, "onDataChange: Before");

                    Log.d(TAG, "onDataChange: After");
//

                    String stringToConvert = String.valueOf(dataSnapshot.getValue());
                    Long convertedLong = Long.parseLong(stringToConvert);

                    TotalCheck= convertedLong.intValue();

                    //

                }catch (Exception e){

                    Log.d(TAG, "onDataChange: " + e.getMessage());

                    //  Toast.makeText(StdHomePage.this, "Get Exception Meal", Toast.LENGTH_SHORT).show();
                    TotalCheck=0;

                   // Toast.makeText(StdHomePage.this, "Exception " + TotalCheck, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void demorefadmbill()
    {
        demoRefAdmBill.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminmodelclass data= new adminmodelclass();
                try {
                    data= dataSnapshot.getValue(adminmodelclass.class);
                    MonthlyBill=data.getTotalMonthlyBill(); //100
                }catch (Exception e){
                    Log.i("Error:p2","onDataChange:");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void demorefadm(){
        demoRefAdm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminmodelclass data= new adminmodelclass();

                try {
                    data= dataSnapshot.getValue(adminmodelclass.class);
                    //TotalRawDataMeal=data.getTotal(); //2
                    bf=data.getBreakfast(); // 0
                    ln=data.getLunch(); //1
                    dn=data.getDinner(); //1


                }catch (Exception e){
                    Log.i(" Error:p", "onDataChange: ");
                }


                totalM=bf+ln+dn;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private class MyAsyncTask extends AsyncTask<Integer,Integer,Integer>
    {


        @Override
        protected Integer doInBackground(Integer... integers) {
            int meal;
            //meal=demodata();
            demodata();

            demorefadm();
            demorefadmbill();
            GetCurrentCost();
            demorefstdtmeal();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         //   Log.d(TAG, "doInBackground: " + getTotalRawDataMeal());
            //integers[0]=meal;
            integers[0]=getTotalRawDataMeal();
          //  Log.d(TAG, "doInBackground Meal" + getTotalRawDataMeal());
            return integers[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TotalRawDataMeal=-1;
            //demodata();
            //Toast.makeText(StdHomePage.this, " onPreExecute" , Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //demoRefAdm.addListenerForSingleValueEvent();
            //demodata();
           // Toast.makeText(StdHomePage.this, " onProgressUpdate done" , Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if(integer.intValue()<=0){
                mealconf.setVisibility(View.VISIBLE);


//                Log.i("BiLL","tk:"+bill);

                //Please do






               // mealconf.setVisibility(View.VISIBLE);
               // Toast.makeText(StdHomePage.this, " YEsssss" , Toast.LENGTH_SHORT).show();

            }
            else{
              //  Toast.makeText(StdHomePage.this, " TotalRawData=" + integer, Toast.LENGTH_SHORT).show();
                Submitted.setVisibility(View.VISIBLE);
                BreakFast.setVisibility(View.INVISIBLE);
                Lunch.setVisibility(View.INVISIBLE);
                Dinner.setVisibility(View.INVISIBLE);


                //Toast.makeText(StdHomePage.this, " Noooo" , Toast.LENGTH_SHORT).show();
            }



        }
    }

    public  void  GetCurrentCost(){
        //demoRefStdCost =databaseReference.child("Student/"+str+"/Meal/"+month_name);
        // final int a;

        demoRefStdCost.child("TotalMonthlyBill").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    String stringToConvert = String.valueOf(dataSnapshot.getValue());

                    Long convertedLong = Long.parseLong(stringToConvert);


                    int bill= convertedLong.intValue();

                    Log.d(TAG, "onDataChange: " + bill);

                    setBill(bill);




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

