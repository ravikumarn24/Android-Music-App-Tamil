package com.android.ravikumar.mymusic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class playlist extends baseclass {

    Intent in;
    int curr_id,paused;
    static float rating=(float)0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paused=0;
        setContentView(R.layout.activity_playlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("Playlist");
        curr_id=R.id.home;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ListView lv=(ListView)findViewById(R.id.playlistview);
        String arr[]=baseclass.music_queue.toArray(new String[baseclass.music_queue.size()]);
        lv.setAdapter(new CustomListAdapter(playlist.this,arr,"In playlist"));
        check();
       /* if(baseclass.music_queue.isEmpty()==false) {
            ListView lv=(ListView)findViewById(R.id.playlistview);
            String arr[]=baseclass.music_queue.toArray(new String[baseclass.music_queue.size()]);
            lv.setAdapter(new CustomListAdapter(playlist.this,arr,null));
            if((music_service.mp==null)||(music_service.mp.isPlaying()==false)&&paused==0) {
                in = new Intent(this, music_service.class);
                startService(in);
            }
            SeekBar s=(SeekBar)findViewById(R.id.seekbar);
            s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    Toast.makeText(seekBar.getContext(), i, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void play(View v)
    {
        music_service.mp.start();
        paused=0;
    }
    public void pause(View v)
    {
        music_service.mp.pause();
         paused=1;

    }
    public void stop(View v)
    {
        baseclass.music_queue.remove(0);
        paused=0;
        Intent in =new Intent(this,playlist.class);
    }
    public void check()
    {
        try {
            Intent in = getIntent();
            if (in != null) {
                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
                if ((music_service.mp == null) || (music_service.mp.isPlaying() == false && paused == 0)) {
                    in = new Intent(this, music_service.class);
                    startService(in);
                }
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
        }
    }

}
