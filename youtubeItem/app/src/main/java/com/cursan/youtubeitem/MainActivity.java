package com.cursan.youtubeitem;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeFailureRecoveryActivity {
    private int i = 0;
    private List<String> youtubeIds;
    private VideoAdapter adapter;
    private Button btnRight;
    private Button btnLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwipeFlingAdapterView container = (SwipeFlingAdapterView) findViewById(R.id.frame);

        btnRight = (Button) findViewById(R.id.right);
        btnLeft = (Button) findViewById(R.id.left);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.getTopCardListener().selectRight();
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.getTopCardListener().selectLeft();
            }
        });

        youtubeIds = new ArrayList<String>();
        youtubeIds.add("wKJ9KzGQq0w");
        youtubeIds.add("nCgQDjiotG0");
        youtubeIds.add("wKJ9KzGQq0w");
        youtubeIds.add("nCgQDjiotG0-3");
        youtubeIds.add("wKJ9KzGQq0w-4");
        youtubeIds.add("nCgQDjiotG0-5");
        youtubeIds.add("wKJ9KzGQq0w-6");
        youtubeIds.add("nCgQDjiotG0-7");
        youtubeIds.add("wKJ9KzGQq0w-8");

        adapter = new VideoAdapter(this, R.layout.item, youtubeIds);
        container.setAdapter(adapter);
        container.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "my removed object!");
                adapter.removeFirstItem();
                playVideo();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            int nb = 9;
            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //adapter.addItem(((Math.random() < 0.5) ? "nCgQDjiotG0" : "wKJ9KzGQq0w") + "-" + nb++);
                Log.d("LIST", "notified");
                playVideo();
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = container.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        container.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void playVideo() {
        if (adapter.getSize() > 0) {
            //YouTubePlayerView youTubeView = adapter.getCurrentView();
            //youTubeView.initialize(getString(R.string.DEVELOPER_KEY), MainActivity.this);
            Log.v("playVideo()", adapter.getCurrentId() + "(" + adapter.getCurrentView() + ")");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {

        if (!wasRestored) {
            player.cueVideo(adapter.getCurrentId());
            Log.v("onInitializationSucs()", adapter.getCurrentId() + "(" + adapter.getCurrentView() + ")");
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
