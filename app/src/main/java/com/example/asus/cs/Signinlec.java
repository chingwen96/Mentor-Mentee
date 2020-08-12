package com.example.asus.cs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Signinlec extends AppCompatActivity {
    public static final String MENTOR_NAME = "mentorname";

    EditText name,password,id;
    Button signin;
    String pass;
    TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin = (Button)findViewById(R.id.signIn);
        name = (EditText)findViewById(R.id.editName);
        password=(EditText)findViewById(R.id.editPassword);

        //Firebase
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference dat = database.getReference("Lecturer");


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nam = name.getText().toString();
                pass = password.getText().toString();

                dat.child(nam).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User lec = dataSnapshot.getValue(User.class);
                        if (lec != null) {
                            if (pass.equals(lec.getPassword())) {
                                Toast.makeText(Signinlec.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(Signinlec.this,Main2Activity.class);
                               intent1.putExtra(MENTOR_NAME, lec.getName());
                               startActivity(intent1);

                            } else {
                                Toast.makeText(Signinlec.this, "Password Wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Signinlec.this, "Username Not Exist", Toast.LENGTH_LONG).show();
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
