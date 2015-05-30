package allindevelopment.com.mooker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 30/05/15.
 */
public class Random_Fragment extends Fragment {

    int i = 0;
    List<Bitmap> al;
    BitmapAdapter arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View janou = inflater.inflate(R.layout.activity_random, container, false);
        //add the view via xml or programmatically
        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) janou.findViewById(R.id.frame);

        ImageView test = new ImageView(janou.getContext().getApplicationContext());
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
        arrayAdapter = new BitmapAdapter(janou.getContext().getApplicationContext(), R.layout.item, al);


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
                Toast.makeText(janou.getContext(), "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(janou.getContext(), "Right!", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(janou.getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        return janou;
    }
}