package com.example.asus.cs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListAnnouActivity extends AppCompatActivity {

        public static final String ANN_KEY = "announcekey";
    public static final String ANN_NAME = "announcename";
        public static final String ANN_ANNOUNCE = "announce";
    public static final String MENTEE2_NAME = "name";
    public static final String MENTORS2_NAME = "names";

    ListView listViewAnnounce;
    List<Announces> announceList;
    DatabaseReference databaseAnnounce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annou_list);

        Intent intent1 = getIntent();
        final String names= intent1.getStringExtra(AnnounceActivity.MENTOR2_NAME);
        Intent intent = getIntent();
        final String names1= intent1.getStringExtra(MenuStuActivity.MENTEES_NAME);

        listViewAnnounce = (ListView)findViewById(R.id.list);
      databaseAnnounce = FirebaseDatabase.getInstance().getReference("Announcements");
        announceList = new ArrayList<>();

       listViewAnnounce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Announces user = announceList.get(position);

               Intent intent = new Intent(ListAnnouActivity.this,CommentActivity.class);
              intent.putExtra(ANN_KEY, user.getKey());
               intent.putExtra(ANN_NAME, user.getAnnName());
               intent.putExtra(ANN_ANNOUNCE, user.getAnnounce());
              intent.putExtra(MENTORS2_NAME,names);
              //intent.putExtra(MENTEE2_NAME,names1);
               startActivity(intent);
           }
       });

    }

    @Override
    protected  void onStart(){
        super.onStart();

        databaseAnnounce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                announceList.clear();
                for(DataSnapshot commentSnapshot:dataSnapshot.getChildren()){
                    Announces announce = commentSnapshot.getValue(Announces.class);
                    announceList.add(announce);
                }
                AnnounceList adapter = new AnnounceList(ListAnnouActivity.this,announceList);
                listViewAnnounce.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
