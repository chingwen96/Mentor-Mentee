package com.example.asus.cs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class appointActivity extends AppCompatActivity {
    public static final String MENTEE2_NAME = "menteename";
    public static final String MONTH = "month";
    public static final String MENTOR3_NAME = "mentorname";

         TextView lecName;
        ListView listViewMentee;
        List<Mentee> menteeList;
    private Button addevent;
    DatabaseReference databaseReference,databases;

        public GregorianCalendar cal_month, cal_month_copy;
        private HwAdapter hwAdapter;
        private TextView tv_month;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_appoint);

            lecName = (TextView)findViewById(R.id.mentorName);
            listViewMentee = (ListView)findViewById(R.id.stuList);
        //    addevent = (Button)findViewById(R.id.addevent);

            Intent intent1 = getIntent();
            final String mentorname= intent1.getStringExtra(Main2Activity.MENTORS_NAME);

            HomeCollection.date_collection_arr=new ArrayList<HomeCollection>();
            HomeCollection.date_collection_arr.add( new HomeCollection("2018-12-08" ,"Haha","CAT300","this is class"));
            HomeCollection.date_collection_arr.add( new HomeCollection("2018-12-09" ,"ABC","Talk","this is talk"));

            databaseReference = FirebaseDatabase.getInstance().getReference("Mentee").child(mentorname);

            lecName.setText(mentorname);

            menteeList = new ArrayList<>();

            listViewMentee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Mentee mentee = menteeList.get(position);
                    Intent intent1 = new Intent(appointActivity.this,MeetingActivity.class);
                    intent1.putExtra(MENTEE2_NAME,mentee.getName());
                 //   intent.putExtra(MONTH,month);
                    startActivity(intent1);
                }
            });

            cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
            cal_month_copy = (GregorianCalendar) cal_month.clone();
            hwAdapter = new HwAdapter(this, cal_month,AppointDetails.date_collection_arr);

            tv_month = (TextView) findViewById(R.id.tv_month);
            tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));

         /*   addevent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(appointActivity.this,addEventActivity.class);
                    intent.putExtra(MENTOR3_NAME,mentorname);
                    startActivity(intent);
                }
            });*/

            ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cal_month.get(GregorianCalendar.MONTH) == 10&&cal_month.get(GregorianCalendar.YEAR)==2018) {
                        //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                        Toast.makeText(appointActivity.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        setPreviousMonth();
                        refreshCalendar();
                    }


                }
            });
            ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cal_month.get(GregorianCalendar.MONTH) == 1&&cal_month.get(GregorianCalendar.YEAR)==2019) {
                        //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                        Toast.makeText(appointActivity.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        setNextMonth();
                        refreshCalendar();
                    }
                }
            });
            GridView gridview = (GridView) findViewById(R.id.gv_calendar);
            gridview.setAdapter(hwAdapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    String selectedGridDate = HwAdapter.day_string.get(position);
                    ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, appointActivity.this);
                }

            });
        }


    @Override
    protected  void onStart(){
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menteeList.clear();
                for(DataSnapshot commentSnapshot:dataSnapshot.getChildren()){
                    Mentee mentee = commentSnapshot.getValue(Mentee.class);
                    menteeList.add(mentee);
                }
                MenteeList2 adapter = new MenteeList2(appointActivity.this,menteeList);
                listViewMentee.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

        protected void setNextMonth() {
            if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
                cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
            } else {
                cal_month.set(GregorianCalendar.MONTH,
                        cal_month.get(GregorianCalendar.MONTH) + 1);
            }
        }

        protected void setPreviousMonth() {
            if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
                cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
            } else {
                cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
            }
        }

        public void refreshCalendar() {
            hwAdapter.refreshDays();
            hwAdapter.notifyDataSetChanged();
            tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
        }
    }
