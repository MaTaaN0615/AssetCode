package com.example.angkanak.assetcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.angkanak.assetcode.model.QrDivices;

public class DetailAssetActivity extends AppCompatActivity {

    QrDivices divices;
    String tagnumber;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_asset);

        Intent intent= getIntent();
        tagnumber =  intent.getStringExtra("tagClick");
        divices = dbHelper.selectAllDataofAsset(tagnumber);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        String Detail_tag = divices.getTagNumber();
        String Detail_des = divices.getDescription();
        String Detail_lodepa = divices.getLocaDepartment();
        String Detail_costcen = divices.getCostCenter();
        String Detail_losec = divices.getLocaSection();
        String Detail_lomain = divices.getLocationMain();
        String Detail_date = divices.getDateInService();
        String Detail_unit = divices.getUnits();
        String Detail_curcost = divices.getCurrentCost();
        String Detail_netbook = divices.getNetBookValue();
        String Detail_boi = divices.getBoi();
        String Detail_boinum = divices.getBoiNumber();

        setIncomingIntent(Detail_tag, Detail_des, Detail_lodepa, Detail_costcen, Detail_losec,
                Detail_lomain, Detail_date, Detail_unit, Detail_curcost, Detail_netbook,
                Detail_boi, Detail_boinum);
    }

    private void setIncomingIntent(String detail_tag, String detail_des, String detail_lodepa, String detail_costcen,
                                   String detail_losec, String detail_lomain, String detail_date, String detail_unit,
                                   String detail_curcost, String detail_netbook, String detail_boi, String detail_boinum )
    {
        TextView tag = findViewById(R.id.textTagNumberDetail);
        tag.setText("Tag Number: " + detail_tag);

        TextView des = findViewById(R.id.textDescriptionDetail);
        des.setText("Description: " + detail_des);

        TextView lodepart = findViewById(R.id.textLocationDepartDetail);
        lodepart.setText("Location Department: " + detail_lodepa);

        TextView cc = findViewById(R.id.textCostCenterDetail);
        cc.setText("Cost center: " + detail_costcen);

        TextView sec = findViewById(R.id.textLocationSectionDetail);
        sec.setText("Location Section: " + detail_losec);

        TextView main = findViewById(R.id.textLocationMainDetail);
        main.setText("Location Main: " + detail_lomain);

        TextView date = findViewById(R.id.textDateinserviceDetail);
        date.setText("Date in service: " + detail_date);

        TextView unit = findViewById(R.id.textUnitsDetail);
        unit.setText("Units: " + detail_unit);

        TextView ccost = findViewById(R.id.textCurrentcostDetail);
        ccost.setText("Cost: " + detail_curcost);


        TextView net = findViewById(R.id.textNetbookvalueDetail);
        net.setText("Book value: " + detail_netbook);

        TextView boi = findViewById(R.id.textBoiDetail);
        boi.setText("BOI: " + detail_boi);

        TextView boinum = findViewById(R.id.textBoinumberDetail);
        boinum.setText("BOI Number: " + detail_boinum);
    }
}
