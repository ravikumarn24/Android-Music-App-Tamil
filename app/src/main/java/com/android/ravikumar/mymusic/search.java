package com.android.ravikumar.mymusic;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class search extends baseclass {

    Intent in;
    int curr_id;
    String[] song={"Maayam illai Manthiram illai","Odathe thithikaari","Naan AutoKaran","Baasha Paru","Raa Raa Ramaiya","Ulagam oruvanukaa","Kathakali whistle","Erangi vandu aadu","Sword of Destiny","Yaar Petra magano","Open the Bottle","Vilayadu Mangatha"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("Search");
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

    public void btnrespond(View view)
    {
        EditText et=(EditText)findViewById(R.id.searchtext);
        String str=et.getText().toString();
        ListView lv=(ListView)findViewById(R.id.display1);
        ArrayList<String> al=new ArrayList<String>();

        for(int i=0;i<song.length;i++)
        {
            if(song[i].contains(str))
            {
                //Toast.makeText(view.getContext(),song[i],Toast.LENGTH_SHORT).show();
                al.add(song[i]);

            }
        }
        String[] arr=al.toArray(new String[al.size()]);
        lv.setAdapter(new CustomListAdapter(view.getContext(),arr,"play"));
    }


}
