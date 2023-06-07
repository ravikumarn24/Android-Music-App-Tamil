package com.android.ravikumar.mymusic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Music extends baseclass {


    static int raiseIntent=0;
    String[] text={"24","Baasha","Kabali","Kathakali","Kaththi","Mangatha"};
    int[] image={R.drawable.film24,R.drawable.baashafilm,R.drawable.kabalifilm,R.drawable.kathakalifilm,R.drawable.kaththifilm,R.drawable.mangaathafilm};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         baseclass.db=new database(Music.this);

        SQLiteDatabase d=db.getReadableDatabase();
        Cursor c=d.rawQuery("select * from songs",null);
        if(c.getCount()==0)db.insert();
        c.close();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GridView gv= (GridView)findViewById(R.id.gridview);
        gv.setAdapter(new CustomAdapter(this,text,image));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // String s=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(),"You have Selected "+text[i],Toast.LENGTH_SHORT).show();
                SQLiteDatabase d=baseclass.db.getReadableDatabase();
                Cursor c=d.rawQuery("select song from songs where film = ? ",new String[]{text[i]});
                ArrayList<String> song_names=new ArrayList<String>();
                for(int j=0;j<c.getCount();j++)
                {
                    c.moveToNext();
                    song_names.add(c.getString(0));

                }
                c.close();

                ListView ls=(ListView) findViewById(R.id.display);
                ls.setVisibility(View.VISIBLE);
                ((GridView)findViewById(R.id.gridview)).setVisibility(view.INVISIBLE);
                String[] arr=song_names.toArray(new String[song_names.size()]);
                if(baseclass.music_queue.isEmpty()==true)
                ls.setAdapter(new CustomListAdapter(Music.this,arr,"play"));
                else
                    ls.setAdapter(new CustomListAdapter(Music.this,arr,"add to list"));


            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(((GridView)findViewById(R.id.gridview)).getVisibility()==View.INVISIBLE)
        {
            ((ListView)findViewById(R.id.display)).setVisibility(View.INVISIBLE);
                ((GridView)findViewById(R.id.gridview)).setVisibility(View.VISIBLE);

        }
        else {
            super.onBackPressed();
        }
    }



}
