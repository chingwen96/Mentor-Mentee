package com.example.asus.cs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.cs.Signinlec.MENTOR_NAME;

public class Main2Activity extends AppCompatActivity {
    public static final String MENTORS_NAME = "mentorname";

    TextView calendar,announcement;
    EditText nam;
    Button add;
    DatabaseReference databaseReference;

    ListView listViewMentee;
    List<Mentee> menteeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        calendar = (TextView)findViewById(R.id.calendarView);
        announcement = (TextView)findViewById(R.id.AnnView);

        Intent intent1 = getIntent();
        final String mentorname= intent1.getStringExtra(Signinlec.MENTOR_NAME);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,appointActivity.class);
                intent.putExtra(MENTORS_NAME,mentorname);
                startActivity(intent);
            }
        });

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,AnnounceActivity.class);
                intent.putExtra(MENTORS_NAME,mentorname);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Mentee").child(mentorname);

        listViewMentee = (ListView)findViewById(R.id.listmentee);
        menteeList = new ArrayList<>();

        listViewMentee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mentee mentee = menteeList.get(position);
                Intent intent = new Intent(Main2Activity.this,ListAnnouActivity.class);
                startActivity(intent);
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
                MenteeList adapter = new MenteeList(Main2Activity.this,menteeList);
                listViewMentee.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
