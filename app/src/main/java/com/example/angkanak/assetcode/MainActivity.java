package com.example.angkanak.assetcode;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button btnexit, btnasset, btnIEport, btnSearch;
  String userCheck = null;


  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Intent getuser = getIntent();
      userCheck = getuser.getStringExtra("user");

    btnasset = (Button)findViewById(R.id.btnAsset);
    btnasset.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AssetCostcenterActivity.class);
          intent.putExtra("user", ""+userCheck+"");
        startActivity(intent);
      }
    });

    btnIEport = (Button) findViewById(R.id.btnImExport);
    btnIEport.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intentIE = new Intent(MainActivity.this, ImportExportActivity.class);
        startActivity(intentIE);
      }
    });

    btnexit = (Button)findViewById(R.id.btnExit);
    btnexit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit ??");
        builder.setPositiveButton("Yes. Exit now!", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            finish();
          }
        });
        builder.setNegativeButton("Not now.", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
      }
    });

    btnSearch = (Button)findViewById(R.id.btnSearch);
    btnSearch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class );
        startActivity( intentSearch);
      }
    });
  }
}
