package com.example.angkanak.assetcode;

/*Created when 18-JAN-2019
 * BY K.ANGKANA*/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angkanak.assetcode.adapter.AssetAdapter;
import com.example.angkanak.assetcode.adapter.ReportAdapter;

public class ReportActivity extends AppCompatActivity {

    Intent intent;
    private RecyclerView recyclerViewReportShow;
    private ReportAdapter mAdapter;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    String cCostcenter, cDepartment, cSection;
    Button btnassetNOTFOUNDCheck, btnassetFOUNDYESCheck, btnassetFOUNDNOCheck, btnmainmenuReportactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        final TextView txtCost_show = findViewById(R.id.txtReportCostcenter);
        intent = getIntent();
        final String txt_get_cost = intent.getStringExtra("Report_CostDepart");
        txtCost_show.setText(txt_get_cost);

        String[] cc = txt_get_cost.split(":");
        cCostcenter = cc[0];
        cDepartment = cc[1];

        final TextView txtLocation_show = findViewById(R.id.txtReportSection);
        intent = getIntent();
        final String txt_get_sec = intent.getStringExtra("Report_section");
        cSection = txt_get_sec;
        txtLocation_show.setText(txt_get_sec);

        final TextView txtAllassetCheck = findViewById(R.id.txtAllassetCheck);
        final int AllassetCheck = dbHelper.countReportAllAssetCheck(cCostcenter,txt_get_sec);
        String numAllassetCheck = String.valueOf(AllassetCheck);
        txtAllassetCheck.setText(numAllassetCheck);

        final TextView txtAssetNotfoundCheck = findViewById(R.id.txtassetNOTFOUNDCheck);
        final int AssetNotfoundCheck = dbHelper.countReportAssetNotfoundCheck(cCostcenter,txt_get_sec);
        String numAssetNotfoundCheck = String.valueOf(AssetNotfoundCheck);
        txtAssetNotfoundCheck.setText(numAssetNotfoundCheck);

        final TextView txtAssetFoundyesCheck = findViewById(R.id.txtassetFOUNDYESCheck);
        final int AssetFoundyesCheck = dbHelper.countReportAssetFoundyesCheck(cCostcenter,txt_get_sec);
        String numAssetFoundyesCheck= String.valueOf(AssetFoundyesCheck);
        txtAssetFoundyesCheck.setText(numAssetFoundyesCheck);

        final TextView txtAssetFoundnoCheck = findViewById(R.id.txtassetFOUNDNOCheck);
        final int AssetFoundnoCheck = dbHelper.countReportAssetFoundnoCheck(cCostcenter,txt_get_sec);
        String numAssetFoundnoCheck = String.valueOf(AssetFoundnoCheck);
        txtAssetFoundnoCheck.setText(numAssetFoundnoCheck);


        recyclerViewReportShow = findViewById(R.id.recyclerReportShow);
        recyclerViewReportShow.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReportAdapter(this,dbHelper.reportGetAssetOfCostcentercheck(cCostcenter,txt_get_sec));
        recyclerViewReportShow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReportShow.setAdapter(mAdapter);

        btnassetNOTFOUNDCheck = findViewById(R.id.btnassetNOTFOUNDCheck);
        btnassetNOTFOUNDCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctiondetailReportAssetNotfoundCheck();
            }
        });

        btnassetFOUNDYESCheck = findViewById(R.id.btnassetFOUNDYESCheck);
        btnassetFOUNDYESCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctiondetailReportAssetFoundyesCheck();
            }
        });


        btnassetFOUNDNOCheck = findViewById(R.id.btnassetFOUNDNOCheck);
        btnassetFOUNDNOCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctiondetailReportAssetFoundnoCheck();
            }
        });

        btnmainmenuReportactivity= findViewById(R.id.btnmainmenuReportactivity);
        btnmainmenuReportactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public void FunctiondetailReportAssetNotfoundCheck(){
        recyclerViewReportShow = findViewById(R.id.recyclerReportShow);
        recyclerViewReportShow.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReportAdapter(this,dbHelper.detailReportAssetNotfoundCheck(cCostcenter,cSection));
        recyclerViewReportShow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReportShow.setAdapter(mAdapter);
    }

    public void FunctiondetailReportAssetFoundyesCheck(){
        recyclerViewReportShow = findViewById(R.id.recyclerReportShow);
        recyclerViewReportShow.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReportAdapter(this,dbHelper.detailReportAssetFoundyesCheck(cCostcenter,cSection));
        recyclerViewReportShow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReportShow.setAdapter(mAdapter);
    }

    public void FunctiondetailReportAssetFoundnoCheck(){
        recyclerViewReportShow = findViewById(R.id.recyclerReportShow);
        recyclerViewReportShow.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReportAdapter(this,dbHelper.detailReportAssetFoundnoCheck(cCostcenter,cSection));
        recyclerViewReportShow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReportShow.setAdapter(mAdapter);
    }
}
