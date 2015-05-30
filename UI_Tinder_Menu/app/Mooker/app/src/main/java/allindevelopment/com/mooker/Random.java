package allindevelopment.com.mooker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class Random extends ActionBarActivity {

    int i = 0;
    List<Bitmap> al;
    BitmapAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        //add the view via xml or programmatically
        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        ImageView test = new ImageView(this);
        test.setImageResource(R.drawable.ic_launcher);


        al = new ArrayList<Bitmap>();
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));
        al.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher));

        //choose your favorite adapter
        arrayAdapter = new BitmapAdapter(this, R.layout.item, al);


        //set the listener and the adapter
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(Random.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(Random.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(Random.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}