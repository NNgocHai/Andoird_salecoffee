package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class FragmentAdminReport extends Fragment {
    View v;
    private BarChart barChart;
    Context context;
    Spinner spinner;



    public FragmentAdminReport() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.admin_report_fragment,container,false);


         context = container.getContext();
        barChart = v.findViewById(R.id.barchart);
        //setupbarchartday(barChart);
        spinner = v.findViewById(R.id.spinner);
        List<String> times=new ArrayList<String>();
        times.add("Day");
        times.add("Month");
        times.add("Year");


        ArrayAdapter<String> Adaptername = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, times);
        Adaptername.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(Adaptername);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int index = spinner.getSelectedItemPosition();
                if(index>=0)
                {
                    if ( index==0) {

                        setupbarchartday(barChart);
                    }else if(index==1){
                        setupbarchartmonth(barChart);
                    }else if(index==2){
                        setupbarchartyear(barChart);
                    }

                    else{
                        setupbarchartday(barChart);
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                setupbarchartday(barChart);
            }
        });





        return v;

    }
    private void setupbarchartday(BarChart barChart){
        ArrayList<Integer> bar_graph_values = DatabaseSelectHelper.getreportbyday1(context);
        ArrayList<String> bar_graph_names  = DatabaseSelectHelper.getreportbyday(context);


        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for(int i=0 ; i <bar_graph_values.size();i++) {
            barEntries.add(new BarEntry(i, bar_graph_values.get(i)));
        }
        BarDataSet barDataSet =new BarDataSet(barEntries,"Day Sales");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        Description description = new Description();
        description.setText("days");
        barChart.setDescription(description);

        barDataSet.setValueTextSize(16f);
        BarData barData =new BarData(barDataSet);

        barChart.setData(barData);

        XAxis xAxis =barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(bar_graph_names));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(bar_graph_names.size());
        //xAxis.setLabelRotationAngle(270);

        //barChart.setFitBars(true);
        //barChart.animateY(2000);
        barChart.invalidate();

    }
    private void setupbarchartmonth(BarChart barChart){
        ArrayList<Integer> bar_graph_values = DatabaseSelectHelper.getreportbymonth1(context);
        ArrayList<String> bar_graph_names  = DatabaseSelectHelper.getreportbymonth(context);


        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for(int i=0 ; i <bar_graph_values.size();i++) {
            barEntries.add(new BarEntry(i, bar_graph_values.get(i)));
        }
        BarDataSet barDataSet =new BarDataSet(barEntries,"Month Sales");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        Description description = new Description();
        description.setText("Months");
        barChart.setDescription(description);

        barDataSet.setValueTextSize(16f);
        BarData barData =new BarData(barDataSet);

        barChart.setData(barData);

        XAxis xAxis =barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(bar_graph_names));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(bar_graph_names.size());
        //xAxis.setLabelRotationAngle(270);

        //barChart.setFitBars(true);
        //barChart.animateY(2000);
        barChart.invalidate();

    }
    private void setupbarchartyear(BarChart barChart){
        ArrayList<Integer> bar_graph_values = DatabaseSelectHelper.getreportbyyear1(context);
        ArrayList<String> bar_graph_names  = DatabaseSelectHelper.getreportbyyear(context);


        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for(int i=0 ; i <bar_graph_values.size();i++) {
            barEntries.add(new BarEntry(i, bar_graph_values.get(i)));
        }
        BarDataSet barDataSet =new BarDataSet(barEntries,"Year Sales");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        Description description = new Description();
        description.setText("year");
        barChart.setDescription(description);

        barDataSet.setValueTextSize(16f);
        BarData barData =new BarData(barDataSet);

        barChart.setData(barData);

        XAxis xAxis =barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(bar_graph_names));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(bar_graph_names.size());
        //xAxis.setLabelRotationAngle(270);

        //barChart.setFitBars(true);
        //barChart.animateY(2000);
        barChart.invalidate();

    }

}
