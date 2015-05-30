package com.cursan.youtubeitem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 29/05/15.
 */
public class VideoAdapter extends ArrayAdapter<String> {
    private List<String> ids;
    private List<View> convertViews = new ArrayList<>();
    private YouTubeFailureRecoveryActivity activity;

    public VideoAdapter(YouTubeFailureRecoveryActivity activity, int resource, List<String> ids) {
        super(activity, resource, ids);
        this.ids = ids;
        this.activity = activity;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertViews.size() <= position) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
            YouTubePlayerView youTubeView = (YouTubePlayerView) convertView.findViewById(R.id.youtube_view);
            youTubeView.initialize(activity.getString(R.string.DEVELOPER_KEY), activity);
            convertViews.add(position, youTubeView);
        }
        Log.v("CONVERT_VIEW", convertViews.get(position) + " - " + position);
        return convertViews.get(position);
    }

    public void removeFirstItem() {
        ids.remove(0);
        convertViews.remove(0);
        notifyDataSetChanged();
    }

    public void addItem(String id) {
        ids.add(id);
        notifyDataSetChanged();
    }

    public String getCurrentId() {
        return ids.get(0);
    }

    public View getCurrentView() {
        return convertViews.get(0);
    }

    public int getSize() {
        return ids.size();
    }

}