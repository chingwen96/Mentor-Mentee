package com.example.asus.cs;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listview extends AppCompatActivity {

    ListView listViewCourse;
    TextView textView;
    Button btnCheck;
    ArrayList<String > arrayList;
    DatabaseReference database,database1,database3;
    ArrayAdapter<String> adapter;
    Academic stu;
    int size,size1;
    Spinner spinner1,spinner;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_view);
        data data1 = new data();
        stu = new Academic();

        btnCheck=findViewById(R.id.btnCheck);
        spinner = findViewById(R.id.spinner);
        listViewCourse = findViewById(R.id.list);
        spinner1 = findViewById(R.id.spinner1);
        database = FirebaseDatabase.getInstance().getReference().child("Subject").child(data1.getData());
        database1 = FirebaseDatabase.getInstance().getReference().child("LMT_100");
        database3 = FirebaseDatabase.getInstance().getReference().child("LSP_300");
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.list_layout3, R.id.subjtext, arrayList);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot announceSnapshot:dataSnapshot.getChildren())
                {

                    stu = announceSnapshot.getValue(Academic.class);

                    assert stu != null;
                    Academic stu1= new Academic(stu.getCourseID(),stu.getCode());

                    String title = dataSnapshot.child(stu.getCourseID().toString()).child("grade").getValue(String.class);

                    arrayList.add("Course: "+ stu.getCourseID().toString()+"\n"+"Course Code: "+stu.getCode()+"\n"+"Grade: "+ title);

                }

                listViewCourse.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    size = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                size1 = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listview.this,number.class);
                intent.putExtra("COUNT", size);
                intent.putExtra("COUNT1", size1);
                //intent.putExtra("ANY_CODE", stu.getCode());
                startActivity(intent);

            }});

        /*listViewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

    }
}