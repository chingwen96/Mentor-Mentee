package com.example.asus.cs;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class bar_chart extends AppCompatActivity {
    BarChart barChart;
    ArrayList<String> theDates;
    BarDataSet barDataSet;
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);

        barChart = findViewById(R.id.bargraph);

        //  ArrayList<BarEntry> barEntries = new ArrayList<>();
        // barEntries.add(new BarEntry(40f,0,"April"));
        //barEntries.add(new BarEntry(66f,1,"May"));

        BarDataSet barDataSet = new BarDataSet(getBarEntryArrayList(),"CGPA");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getBarData().setValueFormatter(new IValueFormatter() {
            @Override

            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getData().toString();
            }
        });
        barChart.animateY(3000);



    }

    public List<BarEntry> getBarEntryArrayList() {
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        barEntries.add(new BarEntry(1f, (float) 3.0, "131"));
        barEntries.add(new BarEntry(3f, (float) 3.3, "132"));
        barEntries.add(new BarEntry(5f, (float) 3.4, "141"));
        barEntries.add(new BarEntry(7, (float) 3.5, "142"));
        barEntries.add(new BarEntry(9f, (float) 3.9, "151"));
        barEntries.add(new BarEntry(11f, (float) 4.0, "152"));
        return barEntries;

    }}
