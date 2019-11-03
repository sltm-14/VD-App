package com.example.app_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button creenciasBtn;
    private Button tecnicasBtn;
    private Button nosotrosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        creenciasBtn = (Button) findViewById(R.id.creencias_btn);

        creenciasBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDescriptionActivity("Creencias");
            }
        });

        tecnicasBtn = (Button) findViewById(R.id.tecnicas_btn);

        tecnicasBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDescriptionActivity("Técnicas");
            }
        });

        nosotrosBtn = (Button) findViewById(R.id.nosotros_btn);

        nosotrosBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDescriptionActivity("Nosotros");
            }
        });

    }
    public void openDescriptionActivity(String btn){
        Intent intent = new Intent(this,DescriptionActivity.class);
        intent.putExtra("btn",  btn);
        startActivity(intent);
    }
}
