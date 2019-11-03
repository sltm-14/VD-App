package com.example.graph_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = (LineChart) findViewById(R.id.line_chart);

        float yValues[] = {10,20,30,00,40};
        String xValues[] = {"First","Second","Third","Lalalala","Shalala"};

        drawLineChart(yValues,xValues);

    }

    private void drawLineChart(float yValues[], String[] xValues){


        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        lineEntries.add(new Entry(0, 6));
        lineEntries.add(new Entry(1, 9));
        lineEntries.add(new Entry(2, 4));
        lineEntries.add(new Entry(3, 8));
        lineEntries.add(new Entry(4, 12));
        lineEntries.add(new Entry(5, 10));

        LineDataSet lineDataSet1 = new LineDataSet(lineEntries, "Line1");
        lineDataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet1.setColors(Color.GREEN);
        lineDataSet1.setLineWidth(3);
        lineDataSet1.setHighlightEnabled(true);
        lineDataSet1.setCircleColor(Color.YELLOW);
        lineDataSet1.setCircleRadius(6);
        lineDataSet1.setCircleHoleRadius(3);
        lineDataSet1.setDrawHighlightIndicators(true);
        lineDataSet1.setHighLightColor(Color.RED);
        lineDataSet1.setValueTextSize(10);
        lineDataSet1.setValueTextColor(Color.BLACK);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        ArrayList<Entry> lineEntries2 = new ArrayList<Entry>();
        lineEntries2.add(new Entry(0, 8));
        lineEntries2.add(new Entry(1, 11));
        lineEntries2.add(new Entry(2, 6));
        lineEntries2.add(new Entry(3, 10));
        lineEntries2.add(new Entry(4, 14));
        lineEntries2.add(new Entry(5, 12));

        LineDataSet lineDataSet2 = new LineDataSet(lineEntries2, "Line2");
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet2.setColors(Color.RED);
        lineDataSet2.setLineWidth(3);
        lineDataSet2.setHighlightEnabled(true);
        lineDataSet2.setCircleColor(Color.GREEN);
        lineDataSet2.setCircleRadius(6);
        lineDataSet2.setCircleHoleRadius(3);
        lineDataSet2.setDrawHighlightIndicators(true);
        lineDataSet2.setHighLightColor(Color.RED);
        lineDataSet2.setValueTextSize(10);
        lineDataSet2.setValueTextColor(Color.BLACK);
        lineDataSet2.enableDashedLine(8,16,4);
        lineDataSet2.setMode(LineDataSet.Mode.LINEAR);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.animateY(1000);
        lineChart.getDescription().setText("Line Comparison Chart");

        Legend legend = lineChart.getLegend();
        legend.setStackSpace(5);
        legend.setTextColor(Color.BLACK);

        // xAxis customization
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);

        lineEntries = new ArrayList<Entry>();
        lineEntries.add(new Entry(0, 10));
        lineEntries.add(new Entry(1, 11));
        lineEntries.add(new Entry(2, 12));
        lineEntries.add(new Entry(3, 14));
        lineEntries.add(new Entry(4, 18));
        lineEntries.add(new Entry(5, 31));

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Line");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.rgb(154, 21, 142));
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(2);
        lineDataSet.setCircleHoleRadius(2);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setValueTextColor(Color.WHITE);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.animateY(1000);
        lineChart.setData(lineData);

    }

}
