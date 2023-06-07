package com.android.ravikumar.mymusic;

import android.content.Context;
import android.view.*;
import android.widget.*;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.zip.Inflater;

/**
 * Created by ravikumar on 2/10/17.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    String[] text;
    int[] image;
    public CustomAdapter(Context c,String[] text,int[] image) {
        this.c=c;
        this.text=text;
        this.image=image;
    }
    public int getCount()
    {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View grid;
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
        {
            grid=new View(c);
            grid= in.inflate(R.layout.cell,null);
            TextView tv=(TextView)grid.findViewById(R.id.cell_text);
            ImageView im=(ImageView)grid.findViewById(R.id.cell_image);
            tv.setText(text[i]);
            im.setLayoutParams(new LinearLayout.LayoutParams(350,350));
            im.setImageResource(image[i]);

            im.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
            grid=(View)view;
        }
        return grid;

    }

}
