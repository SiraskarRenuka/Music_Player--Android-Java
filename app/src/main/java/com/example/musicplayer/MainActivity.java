package com.example.musicplayer;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {
    TextView currenttime;
    TextView songtime;
    TextView songname;
    SeekBar songprogress,seekbarvol;
    ImageView btforword, btplay, btbackword,btnext,btprevious,imageview;
    MediaPlayer mPlayer;

    private AudioManager audioManager;
    static int openingtime=0,starttime=0,endtime=0,forwordtime=5000,backwordtime=5000;
    int currentindex=0;
    Handler hld = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager=(AudioManager)  getSystemService(Context.AUDIO_SERVICE);
        songname=findViewById(R.id.tv_songname);
        currenttime=findViewById(R.id.tv_start_time);
        songtime=findViewById(R.id.tv_song_time);
        imageview=findViewById(R.id.img_background);

        btbackword=findViewById(R.id.img_backword);
        btforword=findViewById(R.id.img_forword);
        btplay=findViewById(R.id.img_play);
        btnext=findViewById(R.id.img_next);
        btprevious=findViewById(R.id.img_previous);
        seekbarvol=findViewById(R.id.seekbar_volume);



        songprogress=findViewById(R.id.seekbar);
        //creating array list to store our song
        ArrayList<Integer> songs=new ArrayList<>();
        songs.add(0,R.raw.music);
        songs.add(1,R.raw.rattan_lambiyaan);
        songs.add(2,R.raw.baygo);
        songs.add(3,R.raw.jogi);
        songs.add(4,R.raw.soniyoraaz);
        songs.add(5,R.raw.arziya);
        songs.add(6,R.raw.pehlidafa);
        songs.add(7,R.raw.pehlapyaar);
        songs.add(8,R.raw.saathiya);
        songs.add(9,R.raw.pehla);
        songs.add(10,R.raw.aapliyaari);
        songs.add(11,R.raw.chorikiyarejiya);
        songs.add(12,R.raw.humdard);
        songs.add(13,R.raw.manbharriya);
        songs.add(14,R.raw.mastmagan);
        songs.add(15,R.raw.ranjha1);
        songs.add(16,R.raw.hasibangaye);
        songs.add(17,R.raw.merenishan1);
        songs.add(18,R.raw.teriaakhonmein);
        songs.add(19,R.raw.mannmera1);
        songs.add(20,R.raw.rabba);
        songs.add(21,R.raw.khudajane);
        songs.add(22,R.raw.tujannena);
        songs.add(23,R.raw.terahone);
        songs.add(24,R.raw.jabseterenaina);
        songs.add(25,R.raw.bulleya);
        songs.add(26,R.raw.dekhatenu);
        songs.add(27,R.raw.dhoondeakiyaaan);
        songs.add(28,R.raw.dildiyagalla);
        songs.add(29,R.raw.dilmerinasune);
        songs.add(30,R.raw.ekladki);
        songs.add(31,R.raw.galliyaan);
        songs.add(32,R.raw.galkarke);
        songs.add(33,R.raw.ikmulakat);
        songs.add(34,R.raw.kabirsinghalbum);
        songs.add(35,R.raw.khaboosejyada);
        songs.add(36,R.raw.mererashkekamar);
        songs.add(37,R.raw.naina);
        songs.add(38,R.raw.paniyosa);
        songs.add(39,R.raw.phirbhitumko);
        songs.add(40,R.raw.piyaore);
        songs.add(41,R.raw.ranjhan);
        songs.add(42,R.raw.samjava);
        songs.add(43,R.raw.sochnasake);
        songs.add(44,R.raw.tuzmainrabdikhta);
        songs.add(45,R.raw.zallima);
        songs.add(46,R.raw.rangrez);
        songs.add(47,R.raw.bewajha);
        songs.add(48,R.raw.tuitni);
        songs.add(49,R.raw.alla);
        songs.add(50,R.raw.hum);
        songs.add(51,R.raw.khud);
        songs.add(52,R.raw.peeloo);

        mPlayer=MediaPlayer.create(getApplicationContext(),songs.get(currentindex));
       int maxV=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
       int curV=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
       seekbarvol.setMax(maxV);
       seekbarvol.setProgress(curV);

       seekbarvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
        songprogress.setClickable(false);

        btplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               
                endtime=mPlayer.getDuration();
                starttime= mPlayer.getCurrentPosition();
                if(mPlayer!=null && mPlayer.isPlaying())
                {
                    mPlayer.pause();
                    btplay.setImageResource(R.drawable.ic_play);
                    Toast.makeText(MainActivity.this, "Pausing audio", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mPlayer.start();
                    Toast.makeText(MainActivity.this, "Playing audio", Toast.LENGTH_SHORT).show();
                    btplay.setImageResource(R.drawable.ic_pause);
                }
                if(openingtime==0)
                {
                    songprogress.setMax(endtime);
                    openingtime=1;
                }
                songtime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(endtime),
                        TimeUnit.MILLISECONDS.toSeconds(endtime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endtime))));

                currenttime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(starttime),
                        TimeUnit.MILLISECONDS.toSeconds(starttime)-TimeUnit.MINUTES.toSeconds
                                (TimeUnit.MILLISECONDS.toMinutes(starttime))));
                songprogress.setProgress(starttime);
                hld.postDelayed(UpdateSongTime,100);
                SongNames();


            }
        });

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer!=null)
                {
                  btplay.setImageResource(R.drawable.ic_pause);

                }
                if(currentindex < songs.size()-1)
                {
                    currentindex++;

                }
                else
                {
                    currentindex=0;
                }
                if(mPlayer.isPlaying())
                {
                    mPlayer.stop();
                }
                mPlayer=MediaPlayer.create(getApplicationContext(),songs.get(currentindex));
                mPlayer.start();
                SongNames();
                endtime=mPlayer.getDuration();
                starttime= mPlayer.getCurrentPosition();
                songtime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(endtime),
                        TimeUnit.MILLISECONDS.toSeconds(endtime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endtime))));

                currenttime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(starttime),
                        TimeUnit.MILLISECONDS.toSeconds(starttime)-TimeUnit.MINUTES.toSeconds
                                (TimeUnit.MILLISECONDS.toMinutes(starttime))));
                songprogress.setProgress(starttime);
                hld.postDelayed(UpdateSongTime,100);



            }
        });
        btprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer!=null)
                {
                    btplay.setImageResource(R.drawable.ic_pause);

                }
                if(currentindex>0)
                {
                    currentindex--;
                }
                else
                {
                    currentindex= songs.size()-1;
                }
                if(mPlayer.isPlaying())
                {
                    mPlayer.stop();
                }
                mPlayer=MediaPlayer.create(getApplicationContext(),songs.get(currentindex));
                mPlayer.start();
                SongNames();
                endtime=mPlayer.getDuration();
                starttime= mPlayer.getCurrentPosition();
                songtime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(endtime),
                        TimeUnit.MILLISECONDS.toSeconds(endtime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endtime))));

                currenttime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(starttime),
                        TimeUnit.MILLISECONDS.toSeconds(starttime)-TimeUnit.MINUTES.toSeconds
                                (TimeUnit.MILLISECONDS.toMinutes(starttime))));
                songprogress.setProgress(starttime);
                hld.postDelayed(UpdateSongTime,100);


            }
        });


        btforword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((starttime+forwordtime)<=endtime) {
                    starttime=starttime+forwordtime;
                    mPlayer.seekTo(starttime);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Can't forword song", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btbackword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((starttime-backwordtime)>0) {
                    starttime=starttime-backwordtime;
                    mPlayer.seekTo(starttime);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Can't backword song", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void SongNames()
    {
        if(currentindex==0) {
            songname.setText("Aashiq Tera");
            imageview.setImageResource(R.drawable.tera);
        }
        if(currentindex==1) {
            songname.setText("Raatan Lambiyan");
            imageview.setImageResource(R.drawable.img);
        }
        if(currentindex==2) {
            songname.setText("Mazi Bay Go");
            imageview.setImageResource(R.drawable.images);
        }
        if(currentindex==3) {
            songname.setText("Jogi");
            imageview.setImageResource(R.drawable.jogi);
        }
        if(currentindex==4) {
            songname.setText("Soniyo");
            imageview.setImageResource(R.drawable.pq);
        }
        if(currentindex==5) {
            songname.setText("Arziyaan");
            imageview.setImageResource(R.drawable.b);
        }
        if(currentindex==6) {
            songname.setText("Pehli Dafa");
            imageview.setImageResource(R.drawable.pehli);
        }
        if(currentindex==7) {
            songname.setText("Tera Didar Hua");
            imageview.setImageResource(R.drawable.y);
        }
        if(currentindex==8) {
            songname.setText("Sathiya");
            imageview.setImageResource(R.drawable.s);
        }
        if(currentindex==9) {
            songname.setText("Lag Ja Gale");
            imageview.setImageResource(R.drawable.l);
        }
        if(currentindex==10) {
            songname.setText("Aapli Yaari");
            imageview.setImageResource(R.drawable.aapli);
        }
        if(currentindex==11) {
            songname.setText("Chori kiya re jiya");
            imageview.setImageResource(R.drawable.chori);
        }
        if(currentindex==12) {
            songname.setText("Humdard");
            imageview.setImageResource(R.drawable.ek);
        }
        if(currentindex==13) {
            songname.setText("Mann Bharryaa 2.0");
            imageview.setImageResource(R.drawable.man);
        }
        if(currentindex==14) {
            songname.setText("Man Mast Magan");
            imageview.setImageResource(R.drawable.mast);
        }
        if(currentindex==15) {
            songname.setText("Ranjha");
            imageview.setImageResource(R.drawable.raw);
        }
        if(currentindex==16) {
            songname.setText("Hasi ban gaye");
            imageview.setImageResource(R.drawable.hamari);
        }
        if(currentindex==17) {
            songname.setText("Mere Nishaan");
            imageview.setImageResource(R.drawable.mere);
        }
        if(currentindex==18) {
            songname.setText("Teri Aakhon mein");
            imageview.setImageResource(R.drawable.teri);
        }
        if(currentindex==19) {
            songname.setText("Mann Mera");
            imageview.setImageResource(R.drawable.mera);
        }
        if(currentindex==20) {
            songname.setText("Rabba");
            imageview.setImageResource(R.drawable.ra);
        }
        if(currentindex==21) {
            songname.setText("Khuda Jane");
            imageview.setImageResource(R.drawable.khuda2);
        }
        if(currentindex==22) {
            songname.setText("Tu jane Na");
            imageview.setImageResource(R.drawable.tu);
        }
        if(currentindex==23) {
            songname.setText("Tera hone laga hoon");
            imageview.setImageResource(R.drawable.ter);
        }
        if(currentindex==24) {
            songname.setText("Jab se tere Naina");
            imageview.setImageResource(R.drawable.jab);
        }
        if(currentindex==25) {
            songname.setText("Bulleya");
            imageview.setImageResource(R.drawable.bulleya);
        }
        if(currentindex==26) {
            songname.setText("Dekha Tenu");
            imageview.setImageResource(R.drawable.dekhatenu);
        }
        if(currentindex==27) {
            songname.setText("Dhoonde Akhiyan");
            imageview.setImageResource(R.drawable.akh);
        }
        if(currentindex==28) {
            songname.setText("Dil Diya Gallan");
            imageview.setImageResource(R.drawable.dil);
        }
        if(currentindex==29) {
            songname.setText("Dil Meri Na Sune");
            imageview.setImageResource(R.drawable.meri);
        }
        if(currentindex==30) {
            songname.setText("Ek ladki ko dekha toh");
            imageview.setImageResource(R.drawable.vaishu);
        }
        if(currentindex==31) {
            songname.setText("Galliyan");
            imageview.setImageResource(R.drawable.gal);
        }
        if(currentindex==32) {
            songname.setText("Gal Karke");
            imageview.setImageResource(R.drawable.galkarke);
        }
        if(currentindex==33) {
            songname.setText("Ek Mulakat");
            imageview.setImageResource(R.drawable.ekmulakat);
        }
        if(currentindex==34) {
            songname.setText("Kabir Singh Album");
            imageview.setImageResource(R.drawable.kabir);
        }
        if(currentindex==35) {
            songname.setText("Rula ke gaya ishq tera");
            imageview.setImageResource(R.drawable.rula);
        }
        if(currentindex==36) {
            songname.setText("Mere Rashke Kamar");
            imageview.setImageResource(R.drawable.mere2);
        }
        if(currentindex==37) {
            songname.setText("Naina");
            imageview.setImageResource(R.drawable.na);
        }
        if(currentindex==38) {
            songname.setText("Paniyo Sa");
            imageview.setImageResource(R.drawable.pa);
        }
        if(currentindex==39) {
            songname.setText("Phir bhi tumko chahunga");
            imageview.setImageResource(R.drawable.phir);
        }
        if(currentindex==40) {
            songname.setText("Piya o re piya");
            imageview.setImageResource(R.drawable.piya);
        }
        if(currentindex==41) {
            songname.setText("Raanjhana");
            imageview.setImageResource(R.drawable.p);
        }
        if(currentindex==42) {
            songname.setText("Samjhawan");
            imageview.setImageResource(R.drawable.sa);
        }
        if(currentindex==43) {
            songname.setText("Soch na sake");
            imageview.setImageResource(R.drawable.so);
        }
        if(currentindex==44) {
            songname.setText("Tuz main rab dikhta hain");
            imageview.setImageResource(R.drawable.up);
        }
        if(currentindex==45) {
            songname.setText("Zaalima");
            imageview.setImageResource(R.drawable.za);
        }
        if(currentindex==46) {
            songname.setText("Rangrez Piya");
            imageview.setImageResource(R.drawable.rani);
        }
        if(currentindex==47) {
            songname.setText("Bewajah");
            imageview.setImageResource(R.drawable.hello);
        }

        if(currentindex==48) {
            songname.setText("Tu Itni Khoobsurat Hai");
            imageview.setImageResource(R.drawable.aap);
        }

        if(currentindex==49) {
            songname.setText("Allah Waariyan");
            imageview.setImageResource(R.drawable.shitu);
        }

        if(currentindex==50) {
            songname.setText("Hum Mar Jayenge");
            imageview.setImageResource(R.drawable.shit);
        }


        if(currentindex==51) {
            songname.setText("Khud Ko tujhe saup ke");
            imageview.setImageResource(R.drawable.yak);
        }
        if(currentindex==52) {
            songname.setText("peelo tere neele");
            imageview.setImageResource(R.drawable.sath);
        }



        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                songprogress.setMax(mPlayer.getDuration());
                mPlayer.start();
            }
        });
        songprogress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                {
                    mPlayer.seekTo(i);
                    songprogress.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private Runnable UpdateSongTime=new Runnable() {
        @Override
        public void run() {
            starttime=mPlayer.getCurrentPosition();
            currenttime.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes(starttime),
                    TimeUnit.MILLISECONDS.toSeconds(starttime)-TimeUnit.MINUTES.toSeconds
                            (TimeUnit.MILLISECONDS.toMinutes(starttime))));
            songprogress.setProgress(starttime);
            hld.postDelayed(this,100);


        }
    };
}
