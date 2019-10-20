package com.example.app_vidadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NodeInfoActivity extends AppCompatActivity {

    public  static TextView bat_TxtVw;
    public  static TextView tem_txtVw;
    public  static TextView hum_TxtVw;
    public  static TextView co2_TxtVw;
    public  static TextView n02_TxtVw;
    public  static TextView co_TxtVw;
    public  static TextView o2_TxtVw;
    public  static TextView tmps_TxtVw;
    public  static TextView tmpi_TxtVw;
    public  static TextView hums_TxtVw;
    public  static TextView ph_TxtVw;
    public  static TextView orp_TxtVw;
    public  static TextView do_TxtVw;
    public  static TextView con_TxtVw;

    ArrayList<String> val_names  = new ArrayList<String>(){
        {
            add("Bat");  add("Tem");  add("Hum");  add("C02");  add("N02");
            add("Co");   add("O2");   add("Tmps"); add("Tmpi"); add("Hums");
            add("Ph");   add("Orp");  add("Do");   add("Con");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_info);

        String node_id  = (String) getIntent().getSerializableExtra("node_id");
        bat_TxtVw  = (TextView) findViewById(R.id.bat_textView);
        tem_txtVw  = (TextView) findViewById(R.id.temp_textView);
        hum_TxtVw  = (TextView) findViewById(R.id.hum_textView);
        co2_TxtVw  = (TextView) findViewById(R.id.c02_textView);
        n02_TxtVw  = (TextView) findViewById(R.id.n02_textView);
        co_TxtVw   = (TextView) findViewById(R.id.co_textView);
        o2_TxtVw   = (TextView) findViewById(R.id.o2_textView);
        tmps_TxtVw = (TextView) findViewById(R.id.tmps_textView);
        tmpi_TxtVw = (TextView) findViewById(R.id.tmpi_textView);
        hums_TxtVw = (TextView) findViewById(R.id.hums_textView);
        ph_TxtVw   = (TextView) findViewById(R.id.ph_textView);
        orp_TxtVw  = (TextView) findViewById(R.id.orp_textView);
        do_TxtVw   = (TextView) findViewById(R.id.do_textView);
        con_TxtVw  = (TextView) findViewById(R.id.con_textView);

        ArrayList<String> attribute_vals = new ArrayList<>();
        GetLatestNodeData process        = new GetLatestNodeData();

        try {
            attribute_vals      = process.execute(node_id).get();

            bat_TxtVw.setText  ((val_names.get(0)  + ":  "+ attribute_vals.get(0)).toString());
            tem_txtVw.setText  ((val_names.get(1)  + ":  "+ attribute_vals.get(1)).toString());
            hum_TxtVw.setText  ((val_names.get(2)  + ":  "+ attribute_vals.get(2)).toString());
            co2_TxtVw.setText  ((val_names.get(3)  + ":  "+ attribute_vals.get(3)).toString());
            n02_TxtVw.setText  ((val_names.get(4)  + ":  "+ attribute_vals.get(4)).toString());
            co_TxtVw.setText   ((val_names.get(5)  + ":  "+ attribute_vals.get(5)).toString());
            o2_TxtVw.setText   ((val_names.get(6)  + ":  "+ attribute_vals.get(6)).toString());
            tmps_TxtVw.setText ((val_names.get(7)  + ":  "+ attribute_vals.get(7)).toString());
            tmpi_TxtVw.setText ((val_names.get(8)  + ":  "+ attribute_vals.get(8)).toString());
            hums_TxtVw.setText ((val_names.get(9)  + ":  "+ attribute_vals.get(9)).toString());
            ph_TxtVw.setText   ((val_names.get(10) + ":  "+ attribute_vals.get(10)).toString());
            orp_TxtVw.setText  ((val_names.get(11) + ":  "+ attribute_vals.get(11)).toString());
            do_TxtVw.setText   ((val_names.get(12) + ":  "+ attribute_vals.get(12)).toString());
            con_TxtVw.setText  ((val_names.get(13) + ":  "+ attribute_vals.get(13)).toString());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
