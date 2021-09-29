package com.example.isafetybots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.isafetybots.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class DetailedReportsActivity extends AppCompatActivity {
    private GraphView graphView,graphView2,graphView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_reports);

        graphView = (GraphView) findViewById(R.id.graphView);
        graphView2 = (GraphView) findViewById(R.id.graphView2);
        graphView3 = (GraphView) findViewById(R.id.graphView3);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 75),
                new DataPoint(1, 85),
                new DataPoint(2, 83),
                new DataPoint(3, 77),
                new DataPoint(4, 89)
        });
        graphView.addSeries(series);
        graphView2.addSeries(series);
        graphView3.addSeries(series);
    }
}