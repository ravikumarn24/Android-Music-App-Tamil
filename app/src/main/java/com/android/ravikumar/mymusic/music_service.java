package com.android.ravikumar.mymusic;

/**
 * Created by ravikumar on 3/10/17.
 */



import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class music_service extends Service {
    public static MediaPlayer mp=null;

    @Override
    public void onCreate() {
        super.onCreate();
        SQLiteDatabase d=baseclass.db.getReadableDatabase();
        Cursor c=d.rawQuery("select songid from songs where song = ? ",new String[]{baseclass.music_queue.get(0)});
        if(c.getCount()>0)c.moveToNext();
        int val=Integer.parseInt(c.getString(0));
        mp = MediaPlayer.create(this,val);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent in=new Intent(getApplicationContext(),playlist.class);
                startActivity(in);
            }
        });
        //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp=null;
    }



}
