package com.example.hostelapp.testTab;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hostelapp.Details_Activity;
import com.example.hostelapp.R;
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

//01

public class BreakFastFragment extends Fragment {

    private static final String TAG = "BreakFastFragment";



    public static final String BREAKFAST = "breakfast";
    public static final String LUNCH = "lunch";
    public static final String DINNER = "dinner";


    public LinkedHashMap<String,List<String> > br = new LinkedHashMap<>();
    public LinkedHashMap<String,List<String> > lu = new LinkedHashMap<>();
    public LinkedHashMap<String,List<String> > di = new LinkedHashMap<>();

    DatabaseReference databaseReference;


    String dateNow;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");






    public LinkedHashMap<String, List<String>> breakFastList = new LinkedHashMap<>();

    CustomAdapter adapter;

    public BreakFastFragment() {
        // Required empty public constructor
    }
    public BreakFastFragment( LinkedHashMap<String, List<String>> br) {
//        breakFastList = br;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_01, container, false);
        Date date = new Date();
        dateNow=dateFormat.format(date);
        View rootView=inflater.inflate(R.layout.fragment_01,container,false);

        ListView lv= (ListView) rootView.findViewById(R.id.breakfatListView);

        testFetch01();

        SystemClock.sleep(5000);

        Log.d(TAG, "onCreateView " + breakFastList.size() );
        CustomAdapter adapter=new CustomAdapter(breakFastList, this.getActivity());
        try {
            lv.setAdapter(adapter);
        }catch (Exception  e){
            Log.d(TAG, "onCreateView:catch " + e.getMessage());
        }

        return rootView;
    }




    public void testFetch01() {
        databaseReference = FirebaseDatabase.getInstance().getReference("MealStudentList/"+dateNow);

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

}