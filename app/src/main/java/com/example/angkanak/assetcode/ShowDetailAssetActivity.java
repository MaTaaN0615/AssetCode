package com.example.angkanak.assetcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDetailAssetActivity extends AppCompatActivity {

    QrDivices divices;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_asset);

//        getIncomingIntent();
        Intent intent= getIntent();
        String tagnumber =  intent.getStringExtra("tagClick");
        divices = dbHelper.selectAllDataofAsset(tagnumber);

        getIncomingIntent();
//        int positions = 10;
                //Integer.parseInt(getIntent().getStringExtra("positionClick"));
//        if (positions > 0){
//            TextView tag = findViewById(R.id.textTagNumber);
//            tag.setText("Tag Number: " + "5410110615");
//        }
    }


    private void getIncomingIntent(){
//        if(getIntent().hasExtra("Detail_tag")
//                && getIntent().hasExtra("Detail_des")
//                && getIntent().hasExtra("Detail_lodepa")
//                && getIntent().hasExtra("Detail_costcen")
//                && getIntent().hasExtra("Detail_losec")
//                && getIntent().hasExtra("Detail_lomain")
//                && getIntent().hasExtra("Detail_date")
//                && getIntent().hasExtra("Detail_unit")
//                && getIntent().hasExtra("Detail_curcost")
//                && getIntent().hasExtra("Detail_netbook")
//                && getIntent().hasExtra("Detail_boi")
//                && getIntent().hasExtra("Detail_boinum")
//                ){

//            String Detail_tag = getIntent().getStringExtra("Detail_tag");
////            String Detail_des = getIntent().getStringExtra("Detail_des");
////            String Detail_lodepa = getIntent().getStringExtra("Detail_lodepa");
////            String Detail_costcen = getIntent().getStringExtra("Detail_costcen");
////            String Detail_losec = getIntent().getStringExtra("Detail_losec");
////            String Detail_lomain = getIntent().getStringExtra("Detail_lomain");
////            String Detail_date = getIntent().getStringExtra("Detail_date");
////            String Detail_unit = getIntent().getStringExtra("Detail_unit");
////            String Detail_curcost = getIntent().getStringExtra("Detail_curcost");
////            String Detail_netbook = getIntent().getStringExtra("Detail_netbook");
////            String Detail_boi = getIntent().getStringExtra("Detail_boi");
////            String Detail_boinum = getIntent().getStringExtra("Detail_boinum");

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
//        }
    }

    private void setIncomingIntent(String detail_tag, String detail_des, String detail_lodepa, String detail_costcen,
                                   String detail_losec, String detail_lomain, String detail_date, String detail_unit,
                                   String detail_curcost, String detail_netbook, String detail_boi, String detail_boinum ){


        TextView tag = findViewById(R.id.textTagNumber);
        tag.setText("Tag Number: " + detail_tag);

        TextView des = findViewById(R.id.textDescription);
        des.setText("Description: " + detail_des);

        TextView lodepart = findViewById(R.id.textLocationDepart);
        lodepart.setText("Location Department: " + detail_lodepa);

        TextView cc = findViewById(R.id.textCostCenter);
        cc.setText("Cost center: " + detail_costcen);

        TextView sec = findViewById(R.id.textLocationSection);
        sec.setText("Location Section: " + detail_losec);

        TextView main = findViewById(R.id.textLocationMain);
        main.setText("Location Main: " + detail_lomain);

        TextView date = findViewById(R.id.textDateinservice);
        date.setText("Date in service: " + detail_date);

        TextView unit = findViewById(R.id.textUnits);
        unit.setText("Units: " + detail_unit);

        TextView ccost = findViewById(R.id.textCurrentcost);
        ccost.setText("Cost: " + detail_curcost);


        TextView net = findViewById(R.id.textNetbookvalue);
        net.setText("Book value: " + detail_netbook);

        TextView boi = findViewById(R.id.textBoi);
        boi.setText("Description: " + detail_boi);

        TextView boinum = findViewById(R.id.textBoinumber);
        boinum.setText("Description: " + detail_boinum);

    }
}
