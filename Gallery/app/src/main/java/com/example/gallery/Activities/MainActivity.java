package com.example.gallery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.gallery.Adapters.GalleryImageAdapter;
import com.example.gallery.Interfaces.IRecyclerViewClickListener;
import com.example.gallery.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Random random = new Random();
        final String[] images = new String[10];
        for ( int i = 0 ; i < images.length ;i++){
            images[i] = "https://oicsum.photo/600?image="+random.nextInt(1000+1);
        }

        final IRecyclerViewClickListener listener = new IRecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Open full screen activity with image click
            }
        };

        GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(this,images,listener);
        recyclerView.setAdapter(galleryImageAdapter);

    }
}
