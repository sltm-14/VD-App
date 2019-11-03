package com.example.app_home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {


    public  static TextView tittle_TxtVw;
    public  static TextView description_TxtVw;

    public static ImageView background_ImgVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        String tittle = (String) getIntent().getSerializableExtra("btn");

        tittle_TxtVw = (TextView) findViewById(R.id.tittle_TextView);
        description_TxtVw = (TextView) findViewById(R.id.description_TextView);

        background_ImgVw = (ImageView) findViewById(R.id.background_ImageView);

        tittle_TxtVw.setText(tittle);

        switch (tittle){
            case "Creencias":
                description_TxtVw.setText("Descripción de creencias");
                background_ImgVw.setImageResource(R.drawable.cake_grape);
                break;
            case "Técnicas":
                description_TxtVw.setText("Descripción de técnicas");
                background_ImgVw.setImageResource(R.drawable.cake_peach);
                break;
            case "Nosotros":
                description_TxtVw.setText("Descripción de nosotros");
                background_ImgVw.setImageResource(R.drawable.tangerine_cake);
                break;
            default:
                description_TxtVw.setText(" ");
                break;

        }



    }
}
