package com.example.asus.cs;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class AnnounceList extends ArrayAdapter<Announces> {

    private Activity context;
    private List<Announces> announceList;

    public AnnounceList(Activity context, List<Announces> announceList) {
        super(context, R.layout.comment_view,announceList);
        this.context = context;
        this.announceList = announceList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewAnnounce = inflater.inflate(R.layout.list_layout,null,true);

        TextView announces = (TextView)listViewAnnounce.findViewById(R.id.annoutext);
        TextView name = (TextView)listViewAnnounce.findViewById(R.id.nameView);
        TextView date = (TextView)listViewAnnounce.findViewById(R.id.dateView);

        Announces announce = announceList.get(position);
        announces.setText(announce.getAnnounce());
        name.setText(announce.getAnnName());
        date.setText(announce.getDate());

        return listViewAnnounce;
    }
}
