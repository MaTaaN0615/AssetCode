package com.example.angkanak.assetcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angkanak.assetcode.adapter.AssetAdapter;
import com.example.angkanak.assetcode.adapter.ReportAdapter;

public class ReportActivity extends AppCompatActivity {


    Intent intent;
    private RecyclerView recyclerViewReportShow;
    private ReportAdapter mAdapter;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    String cCostcenter, cDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        final TextView txtCost_show = (TextView)findViewById(R.id.txtReportCostcenter);
        intent = getIntent();
        final String txt_get_cost = intent.getStringExtra("Report_CostDepart");
        txtCost_show.setText(txt_get_cost);

        String[] cc = txt_get_cost.split(":");
        cCostcenter = cc[0];
        cDepartment = cc[1];

        final TextView txtLocation_show = (TextView)findViewById(R.id.txtReportSection);
        intent = getIntent();
        final String txt_get_sec = intent.getStringExtra("Report_section");
        txtLocation_show.setText(txt_get_sec);


        recyclerViewReportShow = (RecyclerView)findViewById(R.id.recyclerReportShow);
        recyclerViewReportShow.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReportAdapter(this,dbHelper.reportGetAssetOfCostcentercheck(cCostcenter,txt_get_sec));
        recyclerViewReportShow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReportShow.setAdapter(mAdapter);

    }
}
