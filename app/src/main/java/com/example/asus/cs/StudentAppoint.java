package com.example.asus.cs;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.List;

import static android.media.CamcorderProfile.get;

public class StudentAppoint extends ArrayAdapter<AppointDetails> {

    private Activity context;
    private List<AppointDetails> appointDetailsList;

    public StudentAppoint(Activity context, List<AppointDetails> appointDetailsList) {
        super(context, R.layout.dialog_inform);
        this.context = context;
        this.appointDetailsList = appointDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewAppoint = inflater.inflate(R.layout.row_addapt,null,true);

        TextView tvTitle = (TextView) listViewAppoint.findViewById(R.id.tv_name);
        TextView tvSubject = (TextView) listViewAppoint.findViewById(R.id.tv_type);
        TextView tvDuedate = (TextView) listViewAppoint.findViewById(R.id.tv_desc);
        TextView tvDescription = (TextView) listViewAppoint.findViewById(R.id.tv_class);

        AppointDetails appointDetails = (AppointDetails) appointDetailsList;get(position);
        tvDuedate.setText(appointDetails.getDate());
        tvSubject.setText(appointDetails.getsubject());
        tvDescription.setText(appointDetails.getdescription());

        return listViewAppoint;
    }
}
