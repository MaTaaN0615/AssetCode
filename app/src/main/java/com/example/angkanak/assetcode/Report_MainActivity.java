package com.example.angkanak.assetcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Report_MainActivity extends AppCompatActivity {

    Button btnReport1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__main);

        btnReport1 = (Button)findViewById(R.id.btnReport1);
        btnReport1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReport = new Intent(Report_MainActivity.this, Report_CostcenterActivity.class);
                startActivity(intentReport);
            }
        });
    }
}
