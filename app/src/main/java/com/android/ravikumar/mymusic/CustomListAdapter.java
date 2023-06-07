package com.android.ravikumar.mymusic;

/**
 * Created by ravikumar on 3/10/17.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by ravikumar on 2/10/17.
 */

public class CustomListAdapter extends BaseAdapter {
    Context c;
    String[] text;
    String btn;
    static int ii=0;
    public CustomListAdapter(Context c, String[] text, String btn) {
        this.c=c;
        this.text=text;
        this.btn=btn;
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
        this.ii=i;
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
        {
            grid=new View(c);

            grid= in.inflate(R.layout.textandbutton,null);
            TextView tv=(TextView)grid.findViewById(R.id.song_text);
            tv.setText(text[i]);
            if(btn!=null&&btn.equals("In playlist")==false) {
                Button m = (Button) grid.findViewById(R.id.song_button);
                m.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View parent=(View)view.getParent();
                        TextView tv=parent.findViewById(R.id.song_text);
                        baseclass.music_queue.add(tv.getText().toString());
                        Toast.makeText(view.getContext(),"Added to playlist ",Toast.LENGTH_SHORT).show();
                        Intent in =new Intent(view.getContext(),playlist.class);
                        view.getContext().startActivity(in);

                    }
                });
                m.setText(btn);

            }
            else
            {
                Button m = (Button) grid.findViewById(R.id.song_button);
                m.setText(btn);
            }

        }
        else
        {
            grid=(View)view;
        }
        return grid;

    }

}

