package com.example.asus.cs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AnnounceActivity extends AppCompatActivity {
    public static final String MENTOR2_NAME = "nam";


    EditText announce;
    Button sav,vie;
    TextView username;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    DatabaseReference databaseAnnounce,database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        username = (TextView)findViewById(R.id.nameView);

        Intent intent1 = getIntent();
       final String name= intent1.getStringExtra(Main2Activity.MENTORS_NAME);

      username.setText(name);
       databaseAnnounce = FirebaseDatabase.getInstance().getReference("Announcements");

        announce = (EditText)findViewById(R.id.announceText);
       sav = (Button)findViewById(R.id.save);
       vie =(Button)findViewById(R.id.view);

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnnouncement();
            }
        });

        vie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnounceActivity.this,ListAnnouActivity.class);
                intent.putExtra(MENTOR2_NAME,name);
                startActivity(intent);

            }
        });

    }

    private void saveAnnouncement(){
        String name = username.getText().toString().trim();
        String announ = announce.getText().toString().trim();

        calendar= Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date = simpleDateFormat.format(calendar.getTime());

            if (!TextUtils.isEmpty(announ)) {
                String key = databaseAnnounce.push().getKey();
                Announces useannounce = new Announces(key,name,announ,date);
              //  Comments comments = new Comments(name);
                databaseAnnounce.child(key).setValue(useannounce);
             //   database.child(name).setValue(comments);
                Toast.makeText(this, "Announcement saved", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"You should make an announcement.",Toast.LENGTH_LONG).show();
            }
        }

    }

