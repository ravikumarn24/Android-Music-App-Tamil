package com.android.ravikumar.mymusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ravikumar on 3/10/17.
 */

public class database extends SQLiteOpenHelper {

    int[] id={1,2,3,4,5,6,7,8,9,10,11,12};
    String[] film={"24","24","Baasha","Baasha","Baasha","Kabali","Kathakali","Kathakali","Kaththi","Kaththi","Mangatha","Mangatha"};
    String[] song={"Maayam illai Manthiram illai","Odathe thithikaari","Naan AutoKaran","Baasha Paru","Raa Raa Ramaiya","Ulagam oruvanukaa","Kathakali whistle","Erangi vandu aadu","Sword of Destiny","Yaar Petra magano","Open the Bottle","Vilayadu Mangatha"};
    int[] image={R.drawable.film24,R.drawable.film24,R.drawable.baashafilm,R.drawable.baashafilm,R.drawable.baashafilm,R.drawable.kabalifilm,R.drawable.kathakalifilm,R.drawable.kathakalifilm,R.drawable.kaththifilm,R.drawable.kaththifilm,R.drawable.mangaathafilm,R.drawable.mangaathafilm};
    int[] songid={R.raw.m1_1,R.raw.m1_2,R.raw.m2_1,R.raw.m2_2,R.raw.m2_3,R.raw.m3_1,R.raw.m4_1,R.raw.m4_2,R.raw.m5_1,R.raw.m5_2,R.raw.m6_1,R.raw.m6_2};
    public database(Context context) {
        super(context, "music.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists songs (id int primary key ,film varchar, imageid int,song varchar,songid int,rating real);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS songs");
        onCreate(db);
    }
    public void insert()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        for(int i=0;i<id.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",id[i]);
            contentValues.put("film",film[i]);
            contentValues.put("imageid",image[i]);
            contentValues.put("song",song[i]);
            contentValues.put("songid",songid[i]);
            contentValues.put("rating",(float)(i%5+1));
            db.insert("songs", null, contentValues);
        }
    }


}
