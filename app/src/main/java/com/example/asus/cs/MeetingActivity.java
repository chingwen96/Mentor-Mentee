package com.example.asus.cs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.jar.Attributes;

public class MeetingActivity extends AppCompatActivity {

    Button recordBtn;
    TextView nameMentee,meeting,txt;
    DatabaseReference databases;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        recordBtn = (Button)findViewById(R.id.btnRecord);
        nameMentee = (TextView)findViewById(R.id.menteeNam);
        meeting = (TextView)findViewById(R.id.meet);
        txt = (TextView)findViewById(R.id.text);

        Intent intent = getIntent();
        final String username= intent.getStringExtra(appointActivity.MENTEE2_NAME);
      //  final String month = intent.getStringExtra(appointActivity.MONTH);

        databases = FirebaseDatabase.getInstance().getReference("Meeting").child(username);

        nameMentee.setText(username);

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordMeeting();
            }
        });

        databases.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Map<String,Object> map = (Map<String,Object>) snapshot.getValue();
                    Object times = map.get("times");
                    int num = Integer.parseInt(String.valueOf(times));
                    sum += num;

                 //   meeting.setText(String.valueOf(sum));
                }
                if(sum<2)
                {
                    txt.setText("Your meeting times is" +" "+ sum +".");
                    meeting.setText("Minimum 2 times of meeting is required in a month.");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void recordMeeting(){

        num = num + 1;
        int times= num ;

        String nam = nameMentee.getText().toString();

        if(!TextUtils.isEmpty(nam)) {
            String key = databases.push().getKey();
            MeetingTimes meetingTimes = new MeetingTimes(key,nam,times);
            databases.child(key).setValue(meetingTimes);
            Toast.makeText(this, "Meeting is recorded.", Toast.LENGTH_LONG).show();
        }
    }
}
