package com.example.asus.cs;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class MenteeList extends ArrayAdapter<Mentee> {

    private Activity context;
    private List<Mentee> menteeList;

    public MenteeList(Activity context, List<Mentee> menteeList) {
        super(context, R.layout.activity_main2,menteeList);
        this.context = context;
        this.menteeList = menteeList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewMentee = inflater.inflate(R.layout.main2_list,null,true);

        TextView name = (TextView)listViewMentee.findViewById(R.id.nameView);

        Mentee mentee = menteeList.get(position);
        name.setText(mentee.getName());


        return listViewMentee;
    }
}
