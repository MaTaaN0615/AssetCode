package com.example.angkanak.assetcode.fragment;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileImport {
    static String xlsFilePath = Environment.getExternalStorageDirectory()
            + "/outxmldata/";
    public static boolean daochu(String tmpname, ArrayList<HashMap<String, String>> lists2) {

        try {

            String file = "";
            if (tmpname.isEmpty())
                file = xlsFilePath + "xls"
                        + GetTimesyyyymmddhhmmss() + ".xls";
            else
                file = xlsFilePath + tmpname;
            File path2 = new File(xlsFilePath);

            if (path2.mkdirs()) {

            }
            List<Object> al22 = new ArrayList<Object>();
            List<String> al2 = new ArrayList<String>();
            al2.add("Numbering");

            // al2.add("Filter bar");

            al22.add(al2);
            FileXls.writeXLS(file, al22);
            List<Object> ac = new ArrayList<Object>();

            String id = "";
            // String sxl = "";
            for (int i = 0; i < lists2.size(); i++) {
                List<String> al = new ArrayList<String>();
                Set<Map.Entry<String, String>> sets = lists2.get(i).entrySet();


                for (Map.Entry<String, String> entry : sets) {

                    if (entry.getKey().equals("tagUii")) {
                        id = entry.getValue().toString();
                    }
                    else {
                    }
                    // Object value=entry.getValue();
                }
                al.add(id);

                // al.add(sxl);
                ac.add(al);
            }

            return FileXls.writeXLS(file, ac);
        } catch (Exception ex) {
            Log.i("Export exception.", ex.getMessage());
            return false;
        }
    }



    public static String GetTimesyyyymmdd() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// Get current time
        String dt = formatter.format(curDate);

        return dt;

    }

    public static String GetTimesddMMyy() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date curDate = new Date(System.currentTimeMillis());// Get current time
        String dt = formatter.format(curDate);

        return dt;

    }

    public static String GetTimesyyyymmddhhmmss() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());// Get current time
        String dt = formatter.format(curDate);

        return dt;

    }
}
