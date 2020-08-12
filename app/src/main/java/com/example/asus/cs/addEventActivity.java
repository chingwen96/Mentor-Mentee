package com.example.asus.cs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addEventActivity extends AppCompatActivity {

    public Button saveEvent,cancelEvent;
    public EditText DateEvent,SubEvent, DescipEvent;
    DatabaseReference databaseAppointment,database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent1 = getIntent();
        final String name= intent1.getStringExtra(appointActivity.MENTOR3_NAME);
        databaseAppointment = FirebaseDatabase.getInstance().getReference("Appointment");

        saveEvent = (Button)findViewById(R.id.saveevent);
        cancelEvent = (Button)findViewById(R.id.cancelevent);
        DateEvent = (EditText) findViewById(R.id.dateevent);
        SubEvent = (EditText) findViewById(R.id.subevent);
        DescipEvent = (EditText) findViewById(R.id.descripevent);
        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEventCalendar();
            }
        });

        cancelEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addEventActivity.this,appointActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveEventCalendar(){
        String date = DateEvent.getText().toString().trim();
        String subject = SubEvent.getText().toString().trim();
        String descrption = DescipEvent.getText().toString().trim();

        if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(subject) && !TextUtils.isEmpty(descrption)) {
            AppointDetails usecalendar = new AppointDetails(date,subject,descrption);
            databaseAppointment.child(date).setValue(usecalendar);
            Toast.makeText(this, "Event saved", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(this,"Unable save event.",Toast.LENGTH_LONG).show();
            DateEvent.setText("");
            SubEvent.setText("");
            DescipEvent.setText("");
        }
    }

}
