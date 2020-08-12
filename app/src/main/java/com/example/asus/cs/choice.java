package com.example.asus.cs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class choice extends AppCompatActivity {

        Button button1,button2, button3;
        User user = new User();
        data data1 = new data();
        String Name;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.student_choice);

            button1 = (Button)findViewById(R.id.button1);
            button2 = (Button)findViewById(R.id.button2);
            button3 = (Button)findViewById(R.id.button3);
            if(data.getLec_name().equals("abc")&&data.getLec_pass().equals("123")){

                button1.setText("Ong Chia Teng");
                button2.setText("Liew Sue Hui");
                button3.setText("Ang Ching Wen");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Intent intent = new Intent(choice.this,listview.class);
                        data1.setData("chia");
                        startActivity(intent);
                }
            });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(choice.this,listview.class);
                        data1.setData("sue");
                        startActivity(intent);
                      }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(choice.this,listview.class);
                        data1.setData("ching");
                        startActivity(intent);
                    }
                });
            }
            else{
                button1.setText("Not Assigned Yet");
                button2.setText("Not Assigned Yet");
                button3.setText("Not Assigned Yet");
                Toast.makeText(choice.this, "Not Assigned Yet", Toast.LENGTH_LONG).show();
            }

        }
    }
