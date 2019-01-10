package com.example.angkanak.assetcode;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class LoginActivity extends AppCompatActivity {

  private Button btLogin;
  private EditText Username;
  private EditText Password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    final DatabaseHelper dbHelper = new DatabaseHelper(this);
    dbHelper.getWritableDatabase();
    exportDatabaseFile(this,"SFIASSET.db");

    btLogin = findViewById(R.id.btnLogin);

    Username = findViewById(R.id.edtUsername);
    Password = findViewById(R.id.edtPassword);

    btLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!emptyValidation()) {
          User user = dbHelper.queryUser(Username.getText().toString(), Password.getText().toString());
          if (user != null) {
            Bundle mBundle = new Bundle();
            mBundle.putString("user", user.getFullname());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtras(mBundle);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();
            Username.setText("");
            Password.setText("");
          } else {
            Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            Password.setText("");
          }
        }else{
          Toast.makeText(LoginActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }
      }
    });

  }

  private boolean emptyValidation() {
    if (TextUtils.isEmpty(Username.getText().toString()) || TextUtils.isEmpty(Password.getText().toString())) {
      return true;
    }else {
      return false;
    }
  }

  public boolean exportDatabaseFile(Context context, String dbName) {
    try {
      File dbFile = context.getDatabasePath(dbName);
      File exportFile = new File(Environment.getExternalStorageDirectory() + "/" + dbName);
      FileInputStream fileInputStream = new FileInputStream(dbFile);
      FileOutputStream fileOutputStream = new FileOutputStream(exportFile);
      FileChannel fileInputChannel = fileInputStream.getChannel();
      FileChannel fileOutputChannel = fileOutputStream.getChannel();
      fileInputChannel.transferTo(0, fileInputChannel.size(), fileOutputChannel);
      fileInputStream.close();
      fileOutputStream.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
}
