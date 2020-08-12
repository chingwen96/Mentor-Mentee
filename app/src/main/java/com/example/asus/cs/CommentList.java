package com.example.asus.cs;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentList extends ArrayAdapter<Comments> {

    private Activity context;
    private List<Comments> commentsList;

    public CommentList(Activity context, List<Comments> commentsList) {
        super(context, R.layout.comment_view,commentsList);
        this.context = context;
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listView = inflater.inflate(R.layout.comment_view,null,true);

        TextView comment = (TextView)listView.findViewById(R.id.commetext);
        TextView name = (TextView)listView.findViewById(R.id.nameView);
        TextView date = (TextView)listView.findViewById(R.id.dateView);

        Comments comments = commentsList.get(position);
        comment.setText(comments.getComment());
        name.setText(comments.getName());
        date.setText(comments.getDate());

        return listView;
    }
}
