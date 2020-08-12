package com.example.asus.cs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import static com.example.asus.cs.SigninActivity.MENTOR_NAME;
import static com.example.asus.cs.SigninActivity.MENTEE_NAME;

public class MenuStuActivity extends AppCompatActivity {
    public static final String MENTEES_NAME = "menteename";
    public static final String MENTORS_NAME = "mentorname";
    TextView calendar,announcement,mentor,academic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menustu);
        final data data1 = new data();
       // final data user1 = new data();
        calendar = (TextView)findViewById(R.id.calendarView);
        announcement = (TextView)findViewById(R.id.AnnView);
        mentor = (TextView)findViewById(R.id.mentorName);
        academic= (TextView)findViewById(R.id.acaV);

        Intent intent1 = getIntent();
        final String menteename= intent1.getStringExtra(SigninActivity.MENTEE_NAME);
        final String mentorname = intent1.getStringExtra(SigninActivity.MENTOR_NAME);

        mentor.setText(mentorname);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuStuActivity.this,StuAppointActivity.class);
                intent.putExtra(MENTORS_NAME,mentorname);
                intent.putExtra(MENTEES_NAME,menteename);
                startActivity(intent);
            }
        });

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuStuActivity.this, StuListAnnouActivity.class);
                intent.putExtra(MENTEES_NAME,menteename);
                startActivity(intent);
            }
        });

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuStuActivity.this, course.class);
                startActivity(intent);

            }
        });

    }
}
