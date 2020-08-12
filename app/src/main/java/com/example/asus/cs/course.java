package com.example.asus.cs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;


public class course extends AppCompatActivity {

    Button btnAdd, btnFinish,btnStat;
    Spinner spinner,spinner1;
    DatabaseReference database,database2,database3;
    EditText name;
    ArrayList<String > arrayList1;
    ArrayAdapter<String> adapter1;
    Academic stu;
    int size;
    String Ucode ="U";
    ListView listViewCourse1;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        name = (EditText)findViewById(R.id.editName);
        btnAdd = findViewById(R.id.btnAdd);
        btnStat = findViewById(R.id.btnStat);
        btnFinish = findViewById(R.id.btnFinish);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        final data data1 = new data ();
        stu = new Academic();

     //   FilterMenuLayout layout1 = (FilterMenuLayout) findViewById(R.id.filter_menu1);
       // attachMenu1(layout1);


        database = FirebaseDatabase.getInstance().getReference().child("Subject").child(data1.getData());
        database2 = FirebaseDatabase.getInstance().getReference().child("LMT_100");
        database3 = FirebaseDatabase.getInstance().getReference().child("LSP_300");
        arrayList1 = new ArrayList<>();
        adapter1 = new ArrayAdapter<>(this, R.layout.list_layout2, R.id.text2, arrayList1);


       btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCourse();
                saveCourse1();

                }});
        btnFinish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course.this,listview.class);
                startActivity(intent);
            }
        });

        btnStat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(data1.getData().equals("sue")){
                Intent intent = new Intent(course.this,bar_chart.class);
                startActivity(intent);
                }
                else
                {
                    Toast.makeText(course.this, "No Statistics", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

/*    private FilterMenu attachMenu1(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.ic_add_black_24dp)
                .addItem(R.drawable.ic_arrow_upward_black_24dp)
                .attach(layout)
                .withListener(listener)
                .build();
    }

    FilterMenu.OnMenuChangeListener listener = new FilterMenu.OnMenuChangeListener() {
        @Override
        public void onMenuItemClick(View view, int position) {
            //Toast.makeText(MainActivity.this, "Touched position " + position, Toast.LENGTH_SHORT).show();
            if(position==0){
                Toast.makeText(course.this, "Registered Success " + position, Toast.LENGTH_SHORT).show();
                saveCourse();
                saveCourse1();
            }else if(position==1){
                Toast.makeText(course.this, "Check " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(course.this,listview.class);
                startActivity(intent);
            }
        }

        @Override
        public void onMenuCollapse() {

        }

        @Override
        public void onMenuExpand() {

        }
    };*/

    private void saveCourse()
        {
            String courseID = spinner.getSelectedItem().toString();
            String code = spinner1.getSelectedItem().toString();
            data data1 = new data();

            final Academic user1 = new Academic(courseID,code);
            if(courseID.equals("CPT112 Discrete Structures")||courseID.equals("CPT113 Programming Methodology and Data Structures") || courseID.equals("CPT111 Principles of Programming")||courseID.equals("CPT114 Logic and Applications")
                    || courseID.equals("CPT115 Mathematical Methods for Computer Science") || courseID.equals("CST131 Computer Organisation") || courseID.equals("CAT200 Integrated Software Development Workshop") || courseID.equals("CMT221 Database Organisation and Design")
                    || courseID.equals("CMT222 Systems Analysis and Design") ||courseID.equals("CMT223 Information Systems Theory and Management")||courseID.equals("CMT224 Multimedia Systems")||courseID.equals("CPT211 Programming Language Concepts and Paradigms")||courseID.equals("CPT212 Design and Analysis of Algorithms")
                    ||courseID.equals("CPT243 Software Requirements Analysis and Modelling")||courseID.equals("CPT244 Artificial Intelligence")||courseID.equals("CST231 Data Communications and Networks")||courseID.equals("CST232 Operating Systems")||courseID.equals("CST233 Information Security and Assurance")||courseID.equals("CST234 Network Programming")
                    ||courseID.equals("CAT300 Group Innovation Project")||courseID.equals("CAT301 Research Methods and Special Topic Study")||courseID.equals("CAT302/CAT303 Industrial Training/Undergraduate Research Training")||courseID.equals("CMT321 Management and Engineering of Databases")||courseID.equals("CMT322 Web Engineering and Technologies")||courseID.equals("CMT324 Computer Graphics and Visual Computing")||courseID.equals("CPT341 Software Design and Architecture")||courseID.equals("CPT342 Knowledge Management and Engineering")
                    ||courseID.equals("CPT343 Software Project Management, Process and Evolution")||courseID.equals("CPT344 Computer Vision and Image Processing")||courseID.equals("CPT346 Natural Language Processing")||courseID.equals("CST331 Principles of Parallel and Distributed Programming")||courseID.equals("CST332 Internet Protocols, Architecture and Routing")||courseID.equals("CST333 Distributed and Grid Computing")||courseID.equals("CST334 Network Monitoring and Security")||courseID.equals("CAT400/CAT401 Undergraduate Major Project/Undergraduate Research Project")||courseID.equals("CAT402 Professional and Technopreneurship Development")||courseID.equals("CMT421 E-Business Strategy, Architecture and Design")||courseID.equals("CMT422 Multimedia Information Systems and Management")
                    ||courseID.equals("CMT423 Decision Support Systems and Business Intelligence")||courseID.equals("CMT424 Animation and Virtual Reality")||courseID.equals("CPT441 Software Quality Assurance and Testing")||courseID.equals("CPT443 Automata Theory and Formal Languages")||courseID.equals("CPT444 Intelligent Health Informatics")||courseID.equals("CST431 Systems Security and Protection")||courseID.equals("CST432 Microprocessors and Embedded Systems")||courseID.equals("CST433 Advanced Computer Architecture")||courseID.equals("CST434 Wireless Network and Mobile Computing")){
                    if(code.equals("U")){
                        Toast.makeText(this,"Wrong Course Code", Toast.LENGTH_LONG).show();}
                    else{database.child(courseID).runTransaction(new Transaction.Handler() {
                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {
                            if (mutableData.getValue() == null) {
                                mutableData.setValue(user1);
                                return Transaction.success(mutableData);
                            } else {
                                return Transaction.abort();
                            }
                        }

                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                        }
                    });}
            }else {
                if(code.equals("U")){
                database.child(courseID).runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        if (mutableData.getValue() == null) {
                            mutableData.setValue(user1);
                            return Transaction.success(mutableData);
                        } else {
                            return Transaction.abort();
                        }
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                    }
                });}else{
                    Toast.makeText(this,"Wrong Course Code", Toast.LENGTH_LONG).show();
                }
            }
            Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show();
            }


    private void saveCourse1() {
        String courseID = spinner.getSelectedItem().toString();
        String code = spinner1.getSelectedItem().toString();
        data data1 = new data();

        Academic user1 = new Academic(courseID, code);

        if(code.equals("U")&& courseID.equals("LMT 100 Preparatory English")&&data.getData().equals("sue"))
        {database2.child(courseID+"1").setValue(user1);}
        else if(code.equals("U")&& courseID.equals("LMT 100 Preparatory English")&&data.getData().equals("ching"))
        {database2.child(courseID+"2").setValue(user1);}
        else if(code.equals("U")&& courseID.equals("LMT 100 Preparatory English")&&data.getData().equals("chia"))
        {database2.child(courseID+"3").setValue(user1);}

        if(code.equals("U")&& courseID.equals("LSP 300 Academic English")&&data.getData().equals("sue"))
        {database3.child(courseID+"1").setValue(user1);}
        else if(code.equals("U")&& courseID.equals("LSP 300 Academic English")&&data.getData().equals("ching"))
        {database3.child(courseID+"2").setValue(user1);}
        else if(code.equals("U")&& courseID.equals("LSP 300 Academic English")&&data.getData().equals("chia"))
        {database3.child(courseID+"3").setValue(user1);}


}

     }






