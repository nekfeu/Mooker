package allindevelopment.com.mooker;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Kevin on 29/05/15.
 */
public class BitmapAdapter extends ArrayAdapter<Bitmap> {
    private List<Bitmap> bitmaps;
    private Context context;

    public BitmapAdapter(Context context, int resource, List<Bitmap> objects) {
        super(context, resource, objects);
        this.bitmaps = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.helloview);
        iv.setImageBitmap(bitmaps.get(position));
        return convertView;
    }
}