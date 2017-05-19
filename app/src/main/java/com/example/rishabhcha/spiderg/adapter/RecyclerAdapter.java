package com.example.rishabhcha.spiderg.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishabhcha.spiderg.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

/**
 * Created by rishabhcha on 19/5/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VideoInfoHolder> {

    public static final String API_KEY = "AIzaSyCmDV7DYogMbThNy4PSmmt1raglHJ0Gvrc";

    String[] VideoID = {"NyPuetyXBbc", "5DP5I8Gd6wY", "KxHN1xQIX9Y", "_akd-o3ppAE", "6CWJMCqU5ow"};

    String[] Titles = {"360° Thought Experiment Trailer | Genius",
            "Short wildlife video clip HD",
            "Karnataka Wildlife and Nature Video- Jaya Hai Kannada Thaye",
            "Giraffe Vs Giraffe Deadliest Fight Ever Seen - Nat Geo Wild",
            "Kannada Jeevaswara"};

    Context ctx;

    public RecyclerAdapter(Context context) {
        this.ctx = context;
    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {

        holder.youTubeTitleView.setText(Titles[position]);

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                Toast.makeText(ctx, "Error while loading Thumbnail", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

                youTubeThumbnailView.setVisibility(View.VISIBLE);

            }
        };

        holder.youTubeThumbnailView.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(VideoID[position]);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                Toast.makeText(ctx, "Error while loading Thumbnail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return VideoID.length;
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        YouTubeThumbnailView youTubeThumbnailView;
        TextView youTubeTitleView;

        public VideoInfoHolder(View itemView) {
            super(itemView);

            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.video_thumbnail);
            youTubeTitleView = (TextView) itemView.findViewById(R.id.video_title);

            youTubeThumbnailView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, API_KEY, VideoID[getLayoutPosition()], 500, true, true);
            ctx.startActivity(intent);
        }
    }
}
