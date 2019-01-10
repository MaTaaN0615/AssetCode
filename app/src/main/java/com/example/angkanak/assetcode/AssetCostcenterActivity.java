package com.example.angkanak.assetcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AssetCostcenterActivity extends AppCompatActivity {

    String userCheck = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_costcenter);

        Intent getuser = getIntent();
        userCheck = getuser.getStringExtra("user");

        ArrayList<String> arlCost = new ArrayList<>();
        arlCost.add("1001:สำนักงานกรุงเทพ");
        arlCost.add("1002:ฝ่ายจัดซื้อทั่วไป");
        arlCost.add("1003:ระบบเทคโนโลยีสารสนเทศ");
        arlCost.add("1007:ฝ่ายพัฒนาห่วงโซ่อุปทาน");
        arlCost.add("1011:ฝ่ายบัญชี");
        arlCost.add("1013:ฝ่ายการตลาดและขาย");
        arlCost.add("2102:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2103:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2104:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2105:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2106:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2107:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2108:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2109:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("2201:ฝ่ายบริหารสำนักงานชุมพร");
        arlCost.add("2202:ฝ่ายโรงงาน");
        arlCost.add("2203:ฝ่ายโรงงาน");
        arlCost.add("2204:ฝ่ายโรงงาน");
        arlCost.add("2205:ฝ่ายส่งออก");
        arlCost.add("3001:ฝ่ายฟาร์มเพาะเลี้ยงกุ้ง");
        arlCost.add("4001:ฝ่ายจัดซื้อวัตถุดิบ");
        arlCost.add("4002:ฝ่ายจัดซื้อวัตถุดิบ");
        arlCost.add("4004:ฝ่ายจัดซื้อวัตถุดิบ");
        arlCost.add("4006:ฝ่ายจัดซื้อวัตถุดิบ");
        arlCost.add("4007:ฝ่ายจัดซื้อวัตถุดิบ");
        arlCost.add("5001:ฝ่ายโรงงาน");
        arlCost.add("5131:ฝ่ายโรงงาน");
        arlCost.add("5140:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("5150:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("5311:ฝ่ายโรงงาน");
        arlCost.add("5350:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("5411:ฝ่ายโรงงาน");
        arlCost.add("5420:ฝ่ายโรงงาน");
        arlCost.add("5425:ฝ่ายโรงงาน");
        arlCost.add("5426:ฝ่ายโรงงาน");
        arlCost.add("5460:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("5470:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("5700:ฝ่ายบริหารทรัพยากรบุคคล");
        arlCost.add("5812:ฝ่ายโรงงาน");
        arlCost.add("5901:ฝ่ายวิศวกรรม");
        arlCost.add("5951:ฝ่ายวิศวกรรม");
        arlCost.add("5952:ฝ่ายวิศวกรรม");
        arlCost.add("5966:ฝ่ายวิศวกรรม");
        arlCost.add("6100:ฝ่ายโรงงาน");
        arlCost.add("6200:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("6300:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");
        arlCost.add("6400:ฝ่ายประกันคุณภาพ&บริหารมาตรฐาน");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,arlCost);

        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showPage = new Intent(AssetCostcenterActivity.this,AssetLocationActivity.class);
                String  itemValue    = (String)listView.getItemAtPosition(i);
                showPage.putExtra("cost",""+itemValue+"");

                showPage.putExtra("user", ""+userCheck+"");

                startActivity(showPage);
            }
        });

    }
}
