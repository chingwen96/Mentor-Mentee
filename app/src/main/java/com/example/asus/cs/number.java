package com.example.asus.cs;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class number extends AppCompatActivity {

    TextView textView4;
    TextView textView5;
    DatabaseReference database;


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.number);
            data data1 = new data ();
            int number = getIntent().getIntExtra("COUNT",0);
            int number1 = getIntent().getIntExtra("COUNT1",0);


            int num = 10-number;
            int num1 = 10-number1;
            textView4 = findViewById(R.id.textView4);
            textView5 = findViewById(R.id.textView5);

            textView4.setText("Number of Subject Taken(U Code): "+"\n"+"1. LMT 100 Preparatory English: "+ number+"\n"+"2. LSP 300 Academic English: "+number1);
            textView5.setText("Number of Subject Left(U Code): "+"\n"+"1. LMT 100 Preparatory English: "+num+"\n"+"2. LSP 300 Academic English: "+num1);
            data1.setNum(number1);
    }
}
