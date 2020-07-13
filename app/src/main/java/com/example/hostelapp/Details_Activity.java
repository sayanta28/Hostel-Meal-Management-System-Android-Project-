package com.example.hostelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Details_Activity extends AppCompatActivity{
    private ListView listview;


    public static final String BREAKFAST = "breakfast";
    public static final String LUNCH = "lunch";
    public static final String DINNER = "dinner";


    public LinkedHashMap<String,List<String> > br = new LinkedHashMap<>();
    public LinkedHashMap<String,List<String> > lu = new LinkedHashMap<>();
    public LinkedHashMap<String,List<String> > di = new LinkedHashMap<>();

    DatabaseReference databaseReference;
    ArrayList<String> arrayList= new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    private static final String TAG = "Details_Activity";


    public Details_Activity(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        String dateNow;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateNow=dateFormat.format(date);

        databaseReference = FirebaseDatabase.getInstance().getReference("MealStudentList/"+dateNow);

        listview= findViewById(R.id.listview);
        arrayAdapter= new ArrayAdapter<String>(this,R.layout.detail_view_meal_model,arrayList);

//        testFetch01();


        Log.d(TAG, "onCreate: " + arrayList.size());
        listview.setAdapter(arrayAdapter);


    }


    public void testFetch01() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                fetch();
            }
        }).start();

    }



    private void fetch() {



        final List<String> brNameAndID = new ArrayList<>();
        final List<String> luNameAndID = new ArrayList<>();
        final List<String> diNameAndID = new ArrayList<>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();

                    for (String key : dataMap.keySet()){

                        Object data = dataMap.get(key);

                        try{
                            HashMap<String, Object> userData = (HashMap<String, Object>) data;

                            for (final  String x : userData.keySet()){
                                 final  String name = userData.get(x).toString();

                                String s = x + name;
//                                Log.d(TAG, "onDataChange: s" + s);

                                if(BREAKFAST.equalsIgnoreCase(key)){
                                    brNameAndID.add(s);
                                }else if(LUNCH.equalsIgnoreCase(key)){
                                    luNameAndID.add(s);
                                }
                                else if(DINNER.equalsIgnoreCase(key)){
                                    diNameAndID.add(s);
                                }

                            }
                        }catch (ClassCastException cce){

                        }
                    }

                    br.put(BREAKFAST, brNameAndID);
                    lu.put(LUNCH, luNameAndID);
                    di.put(DINNER, diNameAndID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Log.d(TAG, "fetch: br" + br.get(BREAKFAST).toString());
        Log.d(TAG, "fetch: lu" + lu.get(LUNCH).toString());
        Log.d(TAG, "fetch: di" + di.get(DINNER).toString());
    }

    public LinkedHashMap<String,List<String> > getBreakFastList(){
        return br;
    }
    public LinkedHashMap<String,List<String> > getLunchList(){
        return lu;
    }
    public LinkedHashMap<String,List<String> > getDinnerList(){
        return di;
    }






}
