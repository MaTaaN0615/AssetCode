package com.example.angkanak.assetcode;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;


public class Check2DBarcodeActivity extends AppCompatActivity {

    String TAG = "2DBarcodeActivity";
    String barCode = "";
    EditText data1;
    Button btn;
    ListView lvShoeAsset;
    Barcode2DWithSoft barcode2DWithSoft = null;
    String seldata = "UTF-8";
    HomeKeyEventBroadCastReceiver     receiver;

    CheckAsset checkAsset;
    String statusArea, statusDecline = null, statusDecline_detail;

    RadioButton radioButton;
    EditText editDecline;
    Button btnSaveProblem;

    private AssetAdapter assetAdapter;
    Cursor cursor;
//    DatabaseHelper db;

    String txt_get_cost, txt_get_locaSec;
    String splDepartment, splCostcenter;
    String userCheck;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    private RecyclerView recyclerView;
    private AssetAdapter mAdapter;
    ArrayList<Asset> assetArrayList = new ArrayList<>();

    TextView amountofSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check2_dbarcode);

        Intent intent = getIntent();
        txt_get_cost = intent.getStringExtra("cost");
//         checkAsset.setCostCenterPresent(txt_get_cost);
        String[] cc = txt_get_cost.split(":");
        splCostcenter = cc[0];
        splDepartment = cc[1];

        Intent intent1 = getIntent();
        txt_get_locaSec = intent1.getStringExtra("Loca");
//        checkAsset.setLocationSectionPresent(txt_get_locaSec);


        barcode2DWithSoft = Barcode2DWithSoft.getInstance();

        receiver = new HomeKeyEventBroadCastReceiver();
        registerReceiver(receiver, new IntentFilter("com.rscja.android.KEY_DOWN"));

        data1= (EditText) findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_assetCheck);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AssetAdapter(this,dbHelper.getDiviceOfSection(splCostcenter,txt_get_locaSec));
//        mAdapter = new AssetAdapter(this,assetArrayList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        List Asset of Locstion
//        cursor = db.getAssetListByLocationSection(txt_get_locaSec);
//        assetAdapter = new AssetAdapter(Check2DBarcodeActivity.this, cursor,0);
//        lvShoeAsset = (ListView) findViewById(R.id.lvShoeAsset);
//        lvShoeAsset.setAdapter(assetAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanBarcode();

                amountofSection = findViewById(R.id.amountAsset);
                int number = dbHelper.queryAssetofSection(splCostcenter, txt_get_locaSec);
                String num = String.valueOf(number);
                amountofSection.setText(num);


            }
        });

        new InitTask().execute();

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

                Toast.makeText(Check2DBarcodeActivity.this,"Save now",Toast.LENGTH_SHORT).show();
            }
        });

        amountofSection = findViewById(R.id.amountAsset);
        int number = dbHelper.queryAssetofSection(splCostcenter, txt_get_locaSec);
        String num = String.valueOf(number);
        amountofSection.setText(num);

    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
/*
        if (barcode2DWithSoft != null) {
            new InitTask().execute();
        }*/
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        if(barcode2DWithSoft!=null){
            barcode2DWithSoft.stopScan();
            barcode2DWithSoft.close();
        }
        super.onDestroy();
        //android.os.Process.killProcess(Process.myPid());
    }


    public Barcode2DWithSoft.ScanCallback  ScanBack= new Barcode2DWithSoft.ScanCallback() {
        @Override
        public void onScanComplete(int i, int length, byte[] bytes) {
            if (length < 1) {
                if (length == -1) {
                    data1.setText("Scan cancel");
                } else if (length == 0) {
                    data1.setText("Scan TimeOut");
                } else {
                    Log.i(TAG, "Scan fail");
                }
            } else {
//                SoundManage.PlaySound(Check2DBarcodeActivity.this, SoundManage.SoundType.SUCCESS);
                barCode = "";


                //  String res = new String(dd,"gb2312");
                try {
                    Log.i("UTF-8", seldata);
                    barCode = new String(bytes, 0, length, seldata);
                    zt();
                } catch (UnsupportedEncodingException ex) {
                }

                data1.setText(barCode);
//                String fullTagBar = barCode;

//               call function to compare location of divice
                compareAssetCheck(barCode);

            }
        }

    };

    public void compareAssetCheck(String readBarcode){
        Boolean var;
        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();

        Intent getuser = getIntent();
        userCheck = getuser.getStringExtra("user");

        //                sub string for save to db
        String[] tokens = readBarcode.split(":");
//  เพิ่มการค้นหาโดยการอ้างอิงจาก tagnumber เพียงอย่างเดียวเพื่อค้นหาใน masterfile
        QrDivices divices = dbHelper.selectAllDataofAsset(tokens[0]);

        cursor = database.query(dbHelper.TABLE_ASSET,
                new String[]{dbHelper.COL_index, dbHelper.COL_TAG_NUMBER, dbHelper.COL_DESCRIPTION, dbHelper.COL_LOCA_DEPARTMENT,
                        dbHelper.COL_COST_CENTER, dbHelper.COL_LOCA_SECTION, dbHelper.COL_LOCATION_MAIN, dbHelper.COL_DATE_IN_SERVICE,
                        dbHelper.COL_UNITS, dbHelper.COL_CURRENT_COST, dbHelper.COL_NET_BOOK_VALUE, dbHelper.COL_BOI, dbHelper.COL_BOI_NUMBER},
                dbHelper.COL_TAG_NUMBER + "=? and " + dbHelper.COL_LOCA_DEPARTMENT + "=? and " + dbHelper.COL_COST_CENTER + "=? and " + dbHelper.COL_LOCA_SECTION + "=?",
                        new String[]{tokens[0],  splDepartment, splCostcenter, txt_get_locaSec},null,null,null, "1");
        if (cursor.getCount() == 0) {
            Toast.makeText(Check2DBarcodeActivity.this,"The device is not in this area. This device is in Coscenter :" + divices.getCostCenter() + " and Location Section :" + divices.getLocaSection() ,Toast.LENGTH_LONG).show();
            SoundManage.PlaySound(Check2DBarcodeActivity.this,SoundManage.SoundType.FAILURE);
            statusArea = "NO";
        }
        else {
            Toast.makeText(Check2DBarcodeActivity.this,"The device is in this area. ",Toast.LENGTH_SHORT).show();
            SoundManage.PlaySound(Check2DBarcodeActivity.this,SoundManage.SoundType.SUCCESS);
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
            SoundManage.PlaySound(Check2DBarcodeActivity.this,SoundManage.SoundType.FAILURE);
            SoundManage.PlaySound(Check2DBarcodeActivity.this,SoundManage.SoundType.FAILURE);
        }

        amountofSection = findViewById(R.id.amountAsset);
        int number = dbHelper.queryAssetofSection(splCostcenter, txt_get_locaSec);
        String num = String.valueOf(number);
        amountofSection.setText(num);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AssetAdapter(this,dbHelper.getDiviceOfSection(splCostcenter,txt_get_locaSec));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    void zt() {
        Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }

    private void ScanBarcode(){
        if(barcode2DWithSoft!=null) {
            Log.i(TAG,"ScanBarcode");

            barcode2DWithSoft.scan();
            barcode2DWithSoft.setScanCallback(ScanBack);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==139 || keyCode==66){
            if(event.getRepeatCount()==0) {
                ScanBarcode();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==139){
            if(event.getRepeatCount()==0) {
                barcode2DWithSoft.stopScan();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub

            boolean reuslt=false;
            if(barcode2DWithSoft!=null) {
                reuslt=  barcode2DWithSoft.open(Check2DBarcodeActivity.this);
                Log.i(TAG,"open="+reuslt);

            }
            return reuslt;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result){
//                barcode2DWithSoft.setParameter(324, 1);
//                barcode2DWithSoft.setParameter(300, 0); // Snapshot Aiming
//                barcode2DWithSoft.setParameter(361, 0); // Image Capture Illumination

                // interleaved 2 of 5
                barcode2DWithSoft.setParameter(6, 1);
                barcode2DWithSoft.setParameter(22, 0);
                barcode2DWithSoft.setParameter(23, 55);
                barcode2DWithSoft.setParameter(402, 1);
                Toast.makeText(Check2DBarcodeActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Check2DBarcodeActivity.this,"fail",Toast.LENGTH_SHORT).show();
            }
            mypDialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            mypDialog = new ProgressDialog(Check2DBarcodeActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("init...");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }

    }

    class HomeKeyEventBroadCastReceiver extends BroadcastReceiver {

        static final String SYSTEM_REASON = "reason";
        static final String SYSTEM_HOME_KEY = "homekey";//home key
        static final String SYSTEM_RECENT_APPS = "recentapps";//long home key

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.rscja.android.KEY_DOWN")) {
                int reason = intent.getIntExtra("Keycode",0);
                //getStringExtra
                boolean long1 = intent.getBooleanExtra("Pressed",false);
                // home key???
                if(reason==280 || reason==66){

                    ScanBarcode();
                }
                // Toast.makeText(getApplicationContext(), "home key="+reason+",long1="+long1, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
