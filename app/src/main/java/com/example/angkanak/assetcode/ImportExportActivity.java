package com.example.angkanak.assetcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.angkanak.assetcode.model.Asset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ImportExportActivity extends AppCompatActivity {

    Button btnmainmenuIMEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_export);

        findViewById(R.id.btnImport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readWeatherData();
            }
        });

        btnmainmenuIMEX = findViewById(R.id.btnmainmenuIMEX);
        btnmainmenuIMEX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImportExportActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void readWeatherData() {
        ArrayList<String> arrayList_txt = new ArrayList<>();

        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.assets);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("TIS-620"))
        );

        // Initialization
        String line = "";
        ArrayList<String> list = new ArrayList<String>();
        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Initialization
        try {
            // Step over headers
            reader.readLine();
            // If buffer is not empty
            while ((line = reader.readLine()) != null) {
//                Log.d("MyActivity","Line: " + line);
                arrayList_txt.add(line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Setters
//                sample.setMonth(tokens[0]);
//                sample.setRainfall(Double.parseDouble(tokens[1]));
//                sample.setSun(Integer.parseInt(tokens[2]));

                for(int i = 0; i<12; i++){
                    int le = tokens[i].length();
                    if(le == 0){
                        tokens[i]=null;
                    }
                }
               dbHelper.addAsset(new Asset(tokens[0], tokens[1], tokens[2], tokens[3],
                                            tokens[4], tokens[5], tokens[6], tokens[7],
                                            tokens[8], tokens[9], tokens[10], tokens[11]));
               Toast.makeText(ImportExportActivity.this,"Seccess",Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            // Logs error with priority level
            Log.wtf("ImportExportActivity", "Error reading data file on line" + line, e);
            // Prints throwable details
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,arrayList_txt);
        final ListView listView = findViewById(R.id.listViewAllAsset);
        listView.setAdapter(adapter);
    }

}