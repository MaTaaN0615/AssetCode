package com.example.angkanak.assetcode;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    DatabaseHelper dbHelper = new DatabaseHelper(this);
    private SQLiteDatabase mDatabase;

    private RecyclerView recyclerView;
    private AssetAdapter mAdapter;

    private final static String TAG= SearchActivity.class.getName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AssetAdapter(this,dbHelper.getAllItems());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /*recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),"Click here", Toast.LENGTH_SHORT).show();;
                return true;
            }
        });*/

//        recyclerView.setAdapter(new RecyclerVeiwAdapter());

//        mAdapter.swapCursor(dbHelper.getAllItems());
//        db = new DatabaseHelper( this);
//        mDatabase = db.getReadableDatabase();

//        cursor = db.getAssetList();
//        assetAdapter = new AssetAdapter(SearchActivity.this, cursor,0);
//        listAssetShow = (ListView) findViewById(R.id.lstAsset);
//        listAssetShow.setAdapter(assetAdapter);

//        ViewData();

//        listAssetShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String text = listAssetShow.getItemAtPosition(position).toString();
//                Toast.makeText(SearchActivity.this, ""+text, Toast.LENGTH_SHORT).show();
//            }
//        });

//        recyclerView.OnClickListener(new View.OnClickListener())

    }

    public class RecyclerVeiwAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.show_search_item,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 1000;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTagnum;
        TextView tvDescription;
        TextView tvLocaDepart;
        TextView tvCostcenter;
        TextView tvLocaSec;
        TextView tvLocaMain;
        TextView tvDate;
        TextView tvUnit;
        TextView tvCurCost;
        TextView tvNetBook;
        TextView tvBoi;
        TextView tvBoinum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTagnum = itemView.findViewById(R.id.textTagNumber);
            tvDescription = itemView.findViewById(R.id.textDescription);
            tvLocaDepart = itemView.findViewById(R.id.textLocationDepart);
            tvCostcenter = itemView.findViewById(R.id.textCostCenter);
            tvLocaSec = itemView.findViewById(R.id.textLocationSection);
            tvLocaMain = itemView.findViewById(R.id.textLocationMain);
            tvDate = itemView.findViewById(R.id.textDateinservice);
            tvUnit = itemView.findViewById(R.id.textUnits);
            tvCurCost = itemView.findViewById(R.id.textCurrentcost);
            tvNetBook = itemView.findViewById(R.id.textNetbookvalue);
            tvBoi = itemView.findViewById(R.id.textBoi);
            tvBoinum = itemView.findViewById(R.id.textBoinumber);

        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }


    /*private void ViewData(){
        Cursor cursor = db.viewData();
        if (cursor.getCount() == 0){
            Toast.makeText(SearchActivity.this,"No data to show", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
            }

            adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,listItem);
            listAssetShow.setAdapter(adapter);
        }
    }*/


    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            android.support.v7.widget.SearchView search = (android.support.v7.widget.SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    mAdapter.swapCursor(dbHelper.getAssetListByKeyword(s));
                    //cursor=db.getAssetListByKeyword(s);
//                    if (mAdapter==null){
//                        Toast.makeText(SearchActivity.this,"No records found!",Toast.LENGTH_LONG).show();
//                    }else{
//                        Toast.makeText(SearchActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
//                    }
//                    assetAdapter.swapCursor(cursor);

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    mAdapter.swapCursor(dbHelper.getAssetListByKeyword(s));
//                    cursor = db.getAssetListByKeyword(s);
//                    if (cursor!=null){
//                        assetAdapter.swapCursor(cursor);
//                    }
                    return false;
                }

            });

        }

        return true;

    }

}
