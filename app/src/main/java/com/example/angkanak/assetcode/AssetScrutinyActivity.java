package com.example.angkanak.assetcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.angkanak.assetcode.UHF.UHFMainActivity;
import com.example.angkanak.assetcode.fragment.UHFReadTagFragment;

public class AssetScrutinyActivity extends AppCompatActivity {

    Button btn2Dpage, btnuhfpage;
    CheckAsset checkAsset = null;
    Intent intent, intent1, intent2;
    String userCheck = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_scrutiny);

        Intent getuser = getIntent();
        userCheck = getuser.getStringExtra("user");

        final TextView txtCost_show = (TextView)findViewById(R.id.txtCostScan);
        intent = getIntent();
        final String txt_get_cost = intent.getStringExtra("cost");
//        checkAsset.setCostCenterPresent(txt_get_cost);
        txtCost_show.setText(txt_get_cost);
 
        final TextView txtLocation_show = (TextView)findViewById(R.id.txtLocaScan);
        intent= getIntent();
        final String txt_get_loca = intent.getStringExtra("Loca");
//        checkAsset.setLocationSectionPresent(txt_get_loca);
        txtLocation_show.setText(txt_get_loca);

        btn2Dpage = (Button)findViewById(R.id.btn2DBarcode);
        btnuhfpage = (Button)findViewById(R.id.btnUHFRfid);
        


        btn2Dpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AssetScrutinyActivity.this, Check2DBarcodeActivity.class);
                intent.putExtra("cost", ""+txt_get_cost+"");
                intent.putExtra("Loca", ""+txt_get_loca+"");

                intent.putExtra("user", ""+userCheck+"");

                startActivity(intent);

            }
        });

        btnuhfpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AssetScrutinyActivity.this, UHFMainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
