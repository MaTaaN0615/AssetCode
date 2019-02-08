package com.example.angkanak.assetcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AssetLocationActivity extends AppCompatActivity {

    String txt_get_costCenter = null;
    String userCheck = null;

    ArrayList<String> arlLocaSection = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_location);

        Intent intent= getIntent();
        txt_get_costCenter = intent.getStringExtra("cost");
        Intent getuser = getIntent();
        userCheck = getuser.getStringExtra("user");

        String[] cc = txt_get_costCenter.split(":");


        switch (cc[0]){
            case "1001" :
                arlLocaSection.add("ส่วนกลาง");
                break;
            case "1002" :
                arlLocaSection.add("7240");
                break;
            case "1003" :
                arlLocaSection.add("0110");
                arlLocaSection.add("0220");
                arlLocaSection.add("0400");
                arlLocaSection.add("1110");
                arlLocaSection.add("1130");
                arlLocaSection.add("1220");
                arlLocaSection.add("1230");
                arlLocaSection.add("1261");
                arlLocaSection.add("1262");
                arlLocaSection.add("1263");
                arlLocaSection.add("2110");
                arlLocaSection.add("2113");
                arlLocaSection.add("4110");
                arlLocaSection.add("4114");
                arlLocaSection.add("4120");
                arlLocaSection.add("4161");
                arlLocaSection.add("5210");
                arlLocaSection.add("5221");
                arlLocaSection.add("5222");
                arlLocaSection.add("5223");
                arlLocaSection.add("5230");
                arlLocaSection.add("5240");
                arlLocaSection.add("5251");
                arlLocaSection.add("5252");
                arlLocaSection.add("5310");
                arlLocaSection.add("5331");
                arlLocaSection.add("5332");
                arlLocaSection.add("5340");
                arlLocaSection.add("5342");
                arlLocaSection.add("5410");
                arlLocaSection.add("5420");
                arlLocaSection.add("5430");
                arlLocaSection.add("5440");
                arlLocaSection.add("5452");
                arlLocaSection.add("5453");
                arlLocaSection.add("6410");
                arlLocaSection.add("7170");
                arlLocaSection.add("7240");
                arlLocaSection.add("7320");
                arlLocaSection.add("8111");
                arlLocaSection.add("8112");
                arlLocaSection.add("8140");
                arlLocaSection.add("8210");
                break;
            case "1007" :
                arlLocaSection.add("6410");
                break;
            case "1011" :
                arlLocaSection.add("7170");
                break;
            case "1013" :
                arlLocaSection.add("6311");
                break;
            case "2102" :
                arlLocaSection.add("5210");
                arlLocaSection.add("5240");
                break;
            case "2103" :
                arlLocaSection.add("5251");
                arlLocaSection.add("5252");
                break;
            case "2104" :
                arlLocaSection.add("5221");
                break;
            case "2105" :
                arlLocaSection.add("5252");
                break;
            case "2106" :
                arlLocaSection.add("5221");
                break;
            case "2107" :
                arlLocaSection.add("5221");
                break;
            case "2108" :
                arlLocaSection.add("5230");
                break;
            case "2109" :
                arlLocaSection.add("5221");
                break;
            case "2201" :
                arlLocaSection.add("5310");
                arlLocaSection.add("5320");
                break;
            case "2202" :
                arlLocaSection.add("1431");
                break;
            case "2203" :
                arlLocaSection.add("1431");
                break;
            case "2204" :
                arlLocaSection.add("1420");
                break;
            case "2205" :
                arlLocaSection.add("7320");
                break;
            case "3001" :
                arlLocaSection.add("6500");
                arlLocaSection.add("6510");
                arlLocaSection.add("6520");
                arlLocaSection.add("6530");
                arlLocaSection.add("6540");
                arlLocaSection.add("6550");
                arlLocaSection.add("6560");
                arlLocaSection.add("6570");
                break;
            case "4001" :
                arlLocaSection.add("1130");
                break;
            case "4002" :
                arlLocaSection.add("1110");
                arlLocaSection.add("1120");
                break;
            case "4004" :
                arlLocaSection.add("1110");
                break;
            case "4006" :
                arlLocaSection.add("1140");
                break;
            case "4007" :
                arlLocaSection.add("1140");
                break;
            case "5001" :
                arlLocaSection.add("1410");
                arlLocaSection.add("2110");
                arlLocaSection.add("2111");
                arlLocaSection.add("2112");
                arlLocaSection.add("2113");
                break;
            case "5131" :
                arlLocaSection.add("1220");
                arlLocaSection.add("1221");
                arlLocaSection.add("1222");
                arlLocaSection.add("1223");
                arlLocaSection.add("5410");
                break;
            case "5140" :
                arlLocaSection.add("1261");
                arlLocaSection.add("1263");
                arlLocaSection.add("3162");
                arlLocaSection.add("4161");
                arlLocaSection.add("4162");
                break;
            case "5311" :
                arlLocaSection.add("3110");
                arlLocaSection.add("3111");
                arlLocaSection.add("3112");
                arlLocaSection.add("3113");
                arlLocaSection.add("3114");
                arlLocaSection.add("5420");
                break;
            case "5411" :
                arlLocaSection.add("4110");
                arlLocaSection.add("4111");
                arlLocaSection.add("4112");
                arlLocaSection.add("4114");
                arlLocaSection.add("4115");
                arlLocaSection.add("4116");
                arlLocaSection.add("5410");
                break;
            case "5420" :
                arlLocaSection.add("4120");
                arlLocaSection.add("4121");
                arlLocaSection.add("4122");
                arlLocaSection.add("4123");
                arlLocaSection.add("4124");
                arlLocaSection.add("5410");
                break;
            case "5425" :
                arlLocaSection.add("4124");
                break;
            case "5700" :
                arlLocaSection.add("5223");
                break;
            case "5812" :
                arlLocaSection.add("1311");
                break;
            case "5901" :
                arlLocaSection.add("5410");
                arlLocaSection.add("5431");
                arlLocaSection.add("5440");
                arlLocaSection.add("5451");
                arlLocaSection.add("5452");
                arlLocaSection.add("5453");
                arlLocaSection.add("5454");
                break;
            case "5951" :
                arlLocaSection.add("5451");
                break;
            case "5952" :
                arlLocaSection.add("5451");
                break;
            case "5966" :
                arlLocaSection.add("5415");
                break;
            case "6100" :
                arlLocaSection.add("1510");
                arlLocaSection.add("1520");
                break;
            case "6200" :
                arlLocaSection.add("8140");
                break;
            case "6300" :
                arlLocaSection.add("0110");
                break;
            case "6400" :
                arlLocaSection.add("8111");
                arlLocaSection.add("8112");
                break;

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,arlLocaSection);

        final ListView listView = findViewById(R.id.listViewLocation);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showPage = new Intent(AssetLocationActivity.this,AssetScrutinyActivity.class);
                String  itemValue    = (String)listView.getItemAtPosition(i);
                showPage.putExtra("Loca",""+itemValue+"");

               /* Intent intent= getIntent();
                String txt_get_costCenter = intent.getStringExtra("cost");*/
                showPage.putExtra("cost", ""+txt_get_costCenter+"");

                showPage.putExtra("user", ""+userCheck+"");

                startActivity(showPage);
            }
        });
    }
}
