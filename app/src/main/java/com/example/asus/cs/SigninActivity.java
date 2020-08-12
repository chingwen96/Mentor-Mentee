package com.example.asus.cs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity {
    public static final String MENTEE_NAME = "menteename";
    public static final String MENTOR_NAME = "mentorname";

    EditText name,password,id;
    Button signin,change;
    String pass;
    data data1 = new data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin = (Button)findViewById(R.id.signIn);
        name = (EditText)findViewById(R.id.editName);
        password=(EditText)findViewById(R.id.editPassword);

        //Firebase
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference data = database.getReference("Student");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String nam = name.getText().toString();
                 pass = password.getText().toString();

                    data.child(nam).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            if (user != null) {
                                if (pass.equals(user.getPassword())) {
                                    Toast.makeText(SigninActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SigninActivity.this,MenuStuActivity.class);
                                    data1.setData(user.getName().toString());
                                    intent.putExtra(MENTEE_NAME, user.getName());
                                    intent.putExtra(MENTOR_NAME,user.getMentorName());
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(SigninActivity.this, "Password Wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(SigninActivity.this, "Username Not Exist", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
            }
        });
    }
}