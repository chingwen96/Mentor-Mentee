package com.example.asus.cs;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    FloatingActionButton fab;
    EditText comment;
    DatabaseReference database;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    ListView listViewComment;
    List<Comments> commentsList;

    TextView name,announ,comName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        name = (TextView)findViewById(R.id.textNameView);
        comName = (TextView)findViewById(R.id.comNameView);
        announ = (TextView)findViewById(R.id.textAnnView);
        comment = (EditText)findViewById(R.id.commentText2);


        Intent intent = getIntent();
        String username= intent.getStringExtra(ListAnnouActivity.ANN_NAME);
        String key= intent.getStringExtra(ListAnnouActivity.ANN_KEY);
        String announce= intent.getStringExtra(ListAnnouActivity.ANN_ANNOUNCE);
      String logname= intent.getStringExtra(ListAnnouActivity.MENTORS2_NAME);
        //String logname= intent.getStringExtra(ListAnnouActivity.MENTEE2_NAME);

        announ.setText(announce);
        name.setText(username);
        comName .setText(logname);

        database = FirebaseDatabase.getInstance().getReference("Comments").child(key);

       listViewComment = (ListView)findViewById(R.id.listComment);
        commentsList = new ArrayList<>();

        fab = (FloatingActionButton)findViewById(R.id.send);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendComments();
            }
        });
    }

   @Override
    protected  void onStart(){
        super.onStart();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentsList.clear();
                for(DataSnapshot commentSnapshot:dataSnapshot.getChildren()){
                    Comments comments = commentSnapshot.getValue(Comments.class);
                    commentsList.add(comments);
                }
                CommentList adapter = new CommentList(CommentActivity.this,commentsList);
                listViewComment.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sendComments(){

        calendar=Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
       String date = simpleDateFormat.format(calendar.getTime());
        String com = comment.getText().toString().trim();
        String nam = comName.getText().toString();

        if(!TextUtils.isEmpty(com)) {
            String key = database.push().getKey();
                Comments comment = new Comments(key,com,nam,date);
                database.child(key).setValue(comment);
                Toast.makeText(this, "Sending......", Toast.LENGTH_LONG).show();
            }
        else{
            Toast.makeText(this,"Please enter a comment.",Toast.LENGTH_LONG).show();
        }
    }
}

