package com.example.angkanak.assetcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SFIASSET.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_USER = "userlogin";
    public static final String COL_id = "_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMPLOYEE_CODE = "empcode";
    public static final String COL_FULLNAME = "fullname";

    public static final String TABLE_ASSET = "assetforuser";
    public static final String COL_index = "id";
    public static final String COL_TAG_NUMBER = "tag_number";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_LOCA_DEPARTMENT = "loca_department";
    public static final String COL_COST_CENTER = "cost_center";
    public static final String COL_LOCA_SECTION = "loca_section";
    public static final String COL_LOCATION_MAIN = "location_main";
    public static final String COL_DATE_IN_SERVICE = "date_in_service";
    public static final String COL_UNITS = "units";
    public static final String COL_CURRENT_COST = "current_cost";
    public static final String COL_NET_BOOK_VALUE = "net_book_value";
    public static final String COL_BOI = "boi";
    public static final String COL_BOI_NUMBER = "boi_number";

//    public static final String COL_TAG_CARD_ID = "tag_card_id";

//    ---------****-------------------------

    public static final String TABLE_CHECKASSET = "checkasset";
    public static final String COL_ASSET_ID = "chas_id";
    public static final String COL_ASSET_TAGNUMBER = "chas_tagnumber";
    public static final String COL_ASSET_DESCRIPTION = "chas_description";
    public static final String COL_ASSET_COSTCENTER = "chas_costcenter";
    public static final String COL_ASSET_DEPARTMENT = "chas_department";
    public static final String COL_ASSET_LOCASECTION = "chas_loca_section";
    public static final String COL_ASSET_LOCATIONMAIN = "chas_locationmain";
    public static final String COL_PRESENT_DEPARTMENT = "present_department";
    public static final String COL_PRESENT_COSTCENTER = "present_coscenter";
    public static final String COL_PRESENT_LOCASECTION = "present_locasection";
    public static final String COL_TIMESTAMP = "timestamp";
    public static final String COL_ASSET_DECLINE = "chas_decline";
    public static final String COL_ASSET_DECLINE_DETAIL = "chas_decline_detail";
    public static final String COL_ASSET_INAERA = "chas_inarea";
    public static final String COL_ASSET_INSPECTOR = "chas_inspector";



    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
//        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COL_USERNAME + " VARCHAR, " +
//                COL_PASSWORD + " VARCHAR, " +
//                COL_EMPLOYEE_CODE + "VARCHAR, " +
//                COL_FULLNAME + "VARCHAR);");

        String creatSql = "CREATE TABLE  " + TABLE_USER + "(" +
                COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERNAME + " TEXT NOT NULL," +
                COL_PASSWORD + " TEXT," +
                COL_EMPLOYEE_CODE + " TEXT," +
                COL_FULLNAME + " TEXT );";
        db.execSQL(creatSql);

        db.execSQL("INSERT INTO " + TABLE_USER + " (" + COL_USERNAME + ", " + COL_PASSWORD + "," + COL_EMPLOYEE_CODE + ", " + COL_FULLNAME + ") " +
                "VALUES ('Admin', '123456', '000000', 'ADMIN ADMINISTER');");
        db.execSQL("INSERT INTO " + TABLE_USER + " (" + COL_USERNAME + ", " + COL_PASSWORD + "," + COL_EMPLOYEE_CODE + ", " + COL_FULLNAME + ") " +
                "VALUES ('NADATE', '987654', '000001', 'NADATE KUKIMIYA');");
        db.execSQL("INSERT INTO " + TABLE_USER + " (" + COL_USERNAME + ", " + COL_PASSWORD + "," + COL_EMPLOYEE_CODE + ", " + COL_FULLNAME + ") " +
                "VALUES ('YaYa ', '555555', '000002', 'YAYA URATSAYA');");

//        --------------------CREATE CHECK ASSET------------------------------

        String creatTableCheckAsset = "CREATE TABLE  " + TABLE_CHECKASSET + "(" +
                COL_ASSET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_ASSET_TAGNUMBER + " TEXT NOT NULL," +
                COL_ASSET_DESCRIPTION + " TEXT," +
                COL_ASSET_COSTCENTER + " TEXT NOT NULL," +
                COL_ASSET_DEPARTMENT + " TEXT," +
                COL_ASSET_LOCASECTION + " TEXT," +
                COL_ASSET_LOCATIONMAIN + " TEXT," +
                COL_PRESENT_DEPARTMENT + " TEXT," +
                COL_PRESENT_COSTCENTER + " TEXT," +
                COL_PRESENT_LOCASECTION + " TEXT," +
                COL_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP " + "," +
                COL_ASSET_DECLINE + " TEXT," +
                COL_ASSET_DECLINE_DETAIL+ " TEXT," +
                COL_ASSET_INAERA + " TEXT," +
                COL_ASSET_INSPECTOR + " TEXT" +
                ");";
        db.execSQL(creatTableCheckAsset);

//        -------------------CREATE ASSET-------------------------
        String creatTableasset = "CREATE TABLE  " + TABLE_ASSET + "(" +
                COL_index + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TAG_NUMBER + " TEXT NOT NULL," +
                COL_DESCRIPTION + " TEXT," +
                COL_LOCA_DEPARTMENT + " TEXT NOT NULL," +
                COL_COST_CENTER + " TEXT," +
                COL_LOCA_SECTION + " TEXT," +
                COL_LOCATION_MAIN + " TEXT," +
                COL_DATE_IN_SERVICE + " TEXT," +
                COL_UNITS + " TEXT," +
                COL_CURRENT_COST + " TEXT," +
                COL_NET_BOOK_VALUE + " TEXT," +
                COL_BOI + " TEXT," +
                COL_BOI_NUMBER + " TEXT);";
        db.execSQL(creatTableasset);

        Log.d("CREATE TABLE","Create Table Successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSET);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKASSET);
            // Create tables again
            onCreate(db);
    }

    public User queryUser(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(TABLE_USER,
                new String[]{COL_id, COL_USERNAME, COL_PASSWORD, COL_EMPLOYEE_CODE, COL_FULLNAME},
                COL_USERNAME + "=? and " + COL_PASSWORD + "=?",
                new String[]{username, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        cursor.close();
        // return user
        return user;
    }

    public void addAsset(Asset asset) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase DBRead = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TAG_NUMBER, asset.getTagNumber());
        values.put(COL_DESCRIPTION, asset.getDescription());
        values.put(COL_LOCA_DEPARTMENT, asset.getLocaDepartment());
        values.put(COL_COST_CENTER, asset.getCostCenter());
        values.put(COL_LOCA_SECTION, asset.getLocaSection());
        values.put(COL_LOCATION_MAIN, asset.getLocationMain());
        values.put(COL_DATE_IN_SERVICE, asset.getDateInService());
        values.put(COL_UNITS, asset.getUnits());
        values.put(COL_CURRENT_COST, asset.getCurrentCost());
        values.put(COL_NET_BOOK_VALUE, asset.getNetBookValue());
        values.put(COL_BOI, asset.getBoi());
        values.put(COL_BOI_NUMBER, asset.getBoiNumber());

        Cursor cursor = DBRead.query(TABLE_ASSET,
                new String[]{COL_index, COL_TAG_NUMBER, COL_DESCRIPTION, COL_LOCA_DEPARTMENT,
                        COL_COST_CENTER, COL_LOCA_SECTION, COL_LOCATION_MAIN, COL_DATE_IN_SERVICE,
                        COL_UNITS, COL_CURRENT_COST, COL_NET_BOOK_VALUE, COL_BOI, COL_BOI_NUMBER},
                COL_COST_CENTER + "=? and " + COL_DATE_IN_SERVICE + "=? and " + COL_TAG_NUMBER + "=?",
                new String[]{asset.getCostCenter(), asset.getDateInService(), asset.getTagNumber()}, null, null, null, "1");

            if (cursor.getCount() == 0) {
            //         Inserting Row
            db.insert(TABLE_ASSET, null, values);
        }
        cursor.close();

        db.close(); // Closing database connection

    }

//    create method to view Data
    public Cursor viewData(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select tag_number from "+ TABLE_ASSET;
        Cursor cursor = database.rawQuery(query,null);

        return cursor;
    }

    /*    public User queryUser(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(TABLE_USER,
                new String[]{COL_id, COL_USERNAME, COL_PASSWORD, COL_EMPLOYEE_CODE, COL_FULLNAME},
                COL_USERNAME + "=? and " + COL_PASSWORD + "=?",
                new String[]{username, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        cursor.close();
        // return user
        return user;
    }*/

    public QrDivices selectAllDataofAsset(String tagnumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        QrDivices divices = null;

        Cursor cursor = db.query(TABLE_ASSET,
                new String[]{COL_index, COL_TAG_NUMBER, COL_DESCRIPTION, COL_LOCA_DEPARTMENT, COL_COST_CENTER, COL_LOCA_SECTION,
                        COL_LOCATION_MAIN, COL_DATE_IN_SERVICE, COL_UNITS, COL_CURRENT_COST, COL_NET_BOOK_VALUE, COL_BOI, COL_BOI_NUMBER},
                COL_TAG_NUMBER + "=?",
                new String[]{tagnumber}, null, null, null, "1");

//         looping through all rows and adding to list
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            divices = new QrDivices(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                                cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                                cursor.getString(12));
        }
        cursor.close();
        // return user
        return divices;

    }

    public Cursor  getAssetListByKeyword(String search) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  "SELECT " +
                COL_index + "," +
                COL_COST_CENTER + "," +
                COL_TAG_NUMBER +
                " FROM " + TABLE_ASSET +
                " WHERE " +  COL_TAG_NUMBER + "  LIKE  '%" +search + "%' "
                ;

        Cursor cursor = db.rawQuery(selectQuery, null);
//         looping through all rows and adding to list
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;

    }

    public Boolean InsertCheckAsset (CheckAsset chAsset) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase DBRead = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ASSET_TAGNUMBER, chAsset.getCHASTagNumber());
        values.put(COL_ASSET_DESCRIPTION, chAsset.getCHASDescription());
        values.put(COL_ASSET_COSTCENTER, chAsset.getCHASCostCenter());
        values.put(COL_ASSET_DEPARTMENT, chAsset.getCHASLocaDepartment());
        values.put(COL_ASSET_LOCASECTION, chAsset.getCHASLocaSection());
        values.put(COL_ASSET_LOCATIONMAIN, chAsset.getCHASLocationMain());
        values.put(COL_PRESENT_DEPARTMENT, chAsset.getDepartmentPresent());
        values.put(COL_PRESENT_COSTCENTER, chAsset.getCostCenterPresent());
        values.put(COL_PRESENT_LOCASECTION, chAsset.getLocationSectionPresent());
        values.put(COL_TIMESTAMP, getDateTime());
        values.put(COL_ASSET_INAERA, chAsset.getAssetInArea());
        values.put(COL_ASSET_INSPECTOR, chAsset.getAssetInspector());

//           Inserting Row
//        db.insert(TABLE_CHECKASSET, null, values);


        Cursor cursor = DBRead.query(TABLE_CHECKASSET,
                new String[]{COL_ASSET_ID, COL_ASSET_TAGNUMBER, COL_ASSET_COSTCENTER, COL_ASSET_LOCASECTION},
                COL_ASSET_TAGNUMBER + "=?",
                new String[]{chAsset.getCHASTagNumber()}, null, null, null, "1");

        if (cursor.getCount() == 0) {
            //         Inserting Row
            db.insert(TABLE_CHECKASSET, null, values);

            cursor.close();
            db.close(); // Closing database connection

            return true;

        } else{
            cursor.close();
            db.close(); // Closing database connection
            return false;

        }

    }

    public void UpdateStatusAsset(String rdDecline, String decline_detial){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase readDB = this.getReadableDatabase();

        int maxid = getMaxID();
        ContentValues values = new ContentValues();
        values.put(COL_ASSET_DECLINE, rdDecline);
        values.put(COL_ASSET_DECLINE_DETAIL, decline_detial);

        db.update(TABLE_CHECKASSET, values, COL_ASSET_ID + "=" + maxid , null );

    }

    private int getMaxID(){
        int mx=-1;
        try{
            SQLiteDatabase readDB = this.getReadableDatabase();
//            String queryMaxID = "SELECT " +
//                    " MAX("  + COL_index  + ")" +
//                    " FROM " + TABLE_CHECKASSET
//                    ;
            Cursor cursor = readDB.rawQuery(" SELECT MAX(chas_id) FROM checkasset ",new String [] {});
            if (cursor != null)
                if(cursor.moveToFirst())
                {

                    mx = cursor.getInt(0);

                }
            //  cursor.close();

            return mx;
        }
        catch(Exception e){

            return -1;
        }
    }

    public Cursor  getAssetListByLocationSection(String sec) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  "SELECT " +
                COL_index + "," +
                COL_TAG_NUMBER + "," +
                COL_DESCRIPTION +
                " FROM " + TABLE_ASSET +
                " WHERE " +  COL_LOCA_SECTION + "  LIKE  '%" +sec + "%' "
                ;

        Cursor cursor = db.rawQuery(selectQuery, null);
//         looping through all rows and adding to list
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;

    }

    private String getDateTime() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date date = new Date();
//        return dateFormat.format(date);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());  // System.currentTimeMillis()
        String dateTime = timestamp.toString();
        String[] DateandTime = dateTime.split("\\.");
        String dt = DateandTime[0];
        return dt;
    }

    public Cursor getAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  "SELECT " +
                COL_index + "," +
                COL_TAG_NUMBER + "," +
                COL_COST_CENTER +
                " FROM " + TABLE_ASSET
//                " WHERE " +  COL_LOCA_SECTION + "  LIKE  '%" +sec + "%' "
                ;

        Cursor cursor = db.rawQuery(selectQuery, null);
//         looping through all rows and adding to list
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
//        return db.query(
//                TABLE_ASSET,
//                null,
//                null,
//                null,
//                null,
//                null,
//                COL_ASSET_COSTCENTER + " DESC"
//        );
    }

    public Cursor getDiviceOfSection(String costcenter, String section){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  " SELECT " +
                COL_index + "," +
                COL_TAG_NUMBER + "," +
                COL_COST_CENTER +
                " FROM " + TABLE_ASSET +
                " LEFT JOIN " + TABLE_CHECKASSET + " ON " + TABLE_CHECKASSET + "." + COL_ASSET_TAGNUMBER + " =" + TABLE_ASSET + "." + COL_TAG_NUMBER +
                " WHERE " + COL_COST_CENTER + " LIKE '%" +costcenter+ "%' " +
                " AND " + COL_LOCA_SECTION + "  LIKE '%" +section+ "%' " +
                " AND " + TABLE_CHECKASSET + "." + COL_ASSET_TAGNUMBER + " IS NULL"
//                " LIMIT 4 "
//                " ORDER BY " + COL_CURRENT_COST + " DESC "
                ;

//        String selectQuery = "select id, tag_number, cost_center" +
//                " from assetforuser" +
//                " where cost_center like '%5350%'" +
//                "AND loca_section like '%3162%'";

        Cursor cursor = db.rawQuery(selectQuery,null);
//         looping through all rows and adding to list
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;

    }

    int amountAsset;
    public int queryAssetofSection(String costcenter, String section){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  " SELECT COUNT(*)" +
                " FROM " + TABLE_ASSET +
                " LEFT JOIN " + TABLE_CHECKASSET + " ON " + TABLE_CHECKASSET + "." + COL_ASSET_TAGNUMBER + " =" + TABLE_ASSET + "." + COL_TAG_NUMBER +
                " WHERE " + TABLE_ASSET + "." + COL_COST_CENTER + " LIKE '%" +costcenter+ "%' " +
                " AND " + TABLE_ASSET + "." + COL_LOCA_SECTION + "  LIKE '%" +section+ "%' " +
                " AND " + TABLE_CHECKASSET + "." + COL_ASSET_TAGNUMBER + " IS NULL"
                ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            amountAsset = cursor.getInt(0);
        }
        cursor.close();
        // return user
        return amountAsset;
    }
}
