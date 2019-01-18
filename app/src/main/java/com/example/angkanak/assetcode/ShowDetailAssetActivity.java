package com.example.angkanak.assetcode;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.angkanak.assetcode.model.CheckAsset;
import com.example.angkanak.assetcode.model.PresentLocation;
import com.example.angkanak.assetcode.model.QrDivices;

public class ShowDetailAssetActivity extends AppCompatActivity {

    QrDivices divices;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    SwipeButton assetInArea;

    Cursor cursor;
    String txt_get_locaSec;
    String splDepartment, splCostcenter;
    String userCheck, statusArea;
    String tagnumber;
    String statusDecline = null, statusDecline_detail;

    RadioButton radioButton;
    EditText editDecline;
    Button btnSaveProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_asset);

//        getIncomingIntent();
        Intent intent= getIntent();
         tagnumber =  intent.getStringExtra("tagClick");
        divices = dbHelper.selectAllDataofAsset(tagnumber);

        getIncomingIntent();
        assetInArea = (SwipeButton)findViewById(R.id.assetinarea);
        assetInArea.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

                Toast.makeText(ShowDetailAssetActivity.this, "Active : "+active, Toast.LENGTH_SHORT).show();

                if(active)
                {
                    insertCheckAssetButton();
                }
            }
        });

        radioButton = findViewById(R.id.radioDeline);
        editDecline = findViewById(R.id.editProblem);
        btnSaveProblem = findViewById(R.id.btnSaveProblem);
        btnSaveProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton.isChecked()){
                    statusDecline = "YES";
                }

                if (editDecline.getText().length() > 0){
                    statusDecline_detail = editDecline.getText().toString();
                }else {
                    statusDecline_detail = null;
                }

                dbHelper.UpdateStatusAsset(statusDecline, statusDecline_detail);

                editDecline.getText().clear();
                radioButton.setChecked(false);

                Toast.makeText(ShowDetailAssetActivity.this,"Save now",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertCheckAssetButton(){
        splCostcenter = ( (PresentLocation) this.getApplication()).getPreCostcenter();
        splDepartment = ( (PresentLocation) this.getApplication()).getPreDepartment();
        txt_get_locaSec = ( (PresentLocation) this.getApplication()).getPreSection();
        userCheck = ( (PresentLocation) this.getApplication()).getPreInspector();

        Boolean var;
        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();

//  เพิ่มการค้นหาโดยการอ้างอิงจาก tagnumber เพียงอย่างเดียวเพื่อค้นหาใน masterfile
        QrDivices divices = dbHelper.selectAllDataofAsset(tagnumber);

        cursor = database.query(dbHelper.TABLE_ASSET,
                new String[]{dbHelper.COL_index, dbHelper.COL_TAG_NUMBER, dbHelper.COL_DESCRIPTION, dbHelper.COL_LOCA_DEPARTMENT,
                        dbHelper.COL_COST_CENTER, dbHelper.COL_LOCA_SECTION, dbHelper.COL_LOCATION_MAIN, dbHelper.COL_DATE_IN_SERVICE,
                        dbHelper.COL_UNITS, dbHelper.COL_CURRENT_COST, dbHelper.COL_NET_BOOK_VALUE, dbHelper.COL_BOI, dbHelper.COL_BOI_NUMBER},
                dbHelper.COL_TAG_NUMBER + "=? and " + dbHelper.COL_LOCA_DEPARTMENT + "=? and " + dbHelper.COL_COST_CENTER + "=? and " + dbHelper.COL_LOCA_SECTION + "=?",
                new String[]{tagnumber,  splDepartment, splCostcenter, txt_get_locaSec},null,null,null, "1");
        if (cursor.getCount() == 0) {
            Toast.makeText(ShowDetailAssetActivity.this,"The device is not in this area. This device is in Coscenter :" + divices.getCostCenter() + " and Location Section :" + divices.getLocaSection() ,Toast.LENGTH_LONG).show();
            SoundManage.PlaySound(ShowDetailAssetActivity.this, SoundManage.SoundType.FAILURE);
            statusArea = "NO";
        }
        else {
            Toast.makeText(ShowDetailAssetActivity.this,"The device is in this area. ",Toast.LENGTH_SHORT).show();
            SoundManage.PlaySound(ShowDetailAssetActivity.this, SoundManage.SoundType.SUCCESS);
            statusArea = "YES";
        }
        cursor.close();

        dbHelper.close(); // Closing database connection

                /*DBRead.query(TABLE_ASSET,
                new String[]{COL_index, COL_TAG_NUMBER, COL_DESCRIPTION, COL_LOCA_DEPARTMENT,
                        COL_COST_CENTER, COL_LOCA_SECTION, COL_LOCATION_MAIN, COL_DATE_IN_SERVICE,
                        COL_UNITS, COL_CURRENT_COST, COL_NET_BOOK_VALUE, COL_BOI, COL_BOI_NUMBER},
                COL_COST_CENTER + "=? and " + COL_DATE_IN_SERVICE + "=? and " + COL_TAG_NUMBER + "=?",
                new String[]{asset.getCostCenter(), asset.getDateInService(), asset.getTagNumber()}, null, null, null, "1");*/

        var = dbHelper.InsertCheckAsset(new CheckAsset(divices.getTagNumber(), divices.getDescription(), divices.getCostCenter(),
                divices.getLocaDepartment(), divices.getLocaSection(), divices.getLocationMain(), splDepartment,
                splCostcenter, txt_get_locaSec, statusArea, userCheck));

        if (!var){
            SoundManage.PlaySound(ShowDetailAssetActivity.this,SoundManage.SoundType.FAILURE);
//            SoundManage.PlaySound(Check2DBarcodeActivity.this,SoundManage.SoundType.FAILURE);
        }
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
