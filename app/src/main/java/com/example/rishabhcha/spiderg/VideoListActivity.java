package com.example.rishabhcha.spiderg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rishabhcha.spiderg.adapter.RecyclerAdapter;

public class VideoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.videoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerAdapter adapter=new RecyclerAdapter(VideoListActivity.this);
        recyclerView.setAdapter(adapter);

    }
}
