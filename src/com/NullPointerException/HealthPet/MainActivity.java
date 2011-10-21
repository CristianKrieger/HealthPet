package com.NullPointerException.HealthPet;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.NullPointerException.HealthPet.UI.UI;

public class MainActivity extends Activity {
	private long delay;
	private boolean inside=true;
	private Random r = new Random();
	private Timer tmr = new Timer();
	private Timer tmr2 = new Timer();
	private View dog, sideDog;
	private MediaPlayer ladrido, fondoNoche, fondoDia;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        ladrido = MediaPlayer.create(this, R.raw.dog);
        fondoNoche = MediaPlayer.create(this, R.raw.perronoche);
        fondoNoche.start();
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        ladrido = MediaPlayer.create(this, R.raw.dog);
        fondoNoche = MediaPlayer.create(this, R.raw.perronoche);
        fondoNoche.setLooping(true);
        fondoNoche.start();
        
        final View img = (View)findViewById(R.id.ImageView1);
        dog=img;
        final View img2 = (View)findViewById(R.id.Sidedog);
        sideDog=img2;
        BitmapDrawable frame1 = (BitmapDrawable)getResources().
        		getDrawable(R.drawable.orejas1);
        BitmapDrawable frame2 = (BitmapDrawable)getResources().
        		getDrawable(R.drawable.orejas2);
		int reasonableDuration = 250;
		final AnimationDrawable mAnimation = new AnimationDrawable();
		mAnimation.addFrame(frame1, reasonableDuration);
		mAnimation.addFrame(frame2, reasonableDuration*10);
		mAnimation.addFrame(frame1, reasonableDuration);
		
		final ImageView pasear = (ImageView) findViewById(R.id.bar6);
		final RelativeLayout container = (RelativeLayout) findViewById(R.id.mainContainer);
		final LinearLayout indicadores = (LinearLayout) findViewById(R.id.indicadores);
		
		img.setBackgroundResource(R.drawable.orejas1);
		img2.setBackgroundResource(R.drawable.cola1);

		delay = (long) r.nextInt(120000);
		tmr.schedule(new TimerTask() {
			public void run() {
				randomAnimation(1);
			}
		}, delay);
		
		img.setOnClickListener(new View.OnClickListener() {
//			@Override
			public void onClick(View v) {
				
				img.setBackgroundDrawable(mAnimation);
				mAnimation.start();
				mAnimation.setVisible(true, true);
			}
		});
		
		img2.setOnClickListener(new View.OnClickListener() {
//			@Override
			public void onClick(View v) {
				img2.setVisibility(8);
				img.setVisibility(0);
				img.setBackgroundDrawable(mAnimation);
				mAnimation.start();
				mAnimation.setVisible(true, true);
			}
		});
		
		pasear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(inside){
					container.setBackgroundResource(R.drawable.fondo_2);
					inside=false;
				}else{
					container.setBackgroundResource(R.drawable.fondo_1);
					inside=true;
				}
			}
		});
		
		indicadores.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final Dialog dialog = new Dialog(MainActivity.this);
				dialog.setContentView(R.layout.details_dialog);
		        dialog.setCancelable(true);
		        dialog.show();
			}
		});
    }
    
    public void randomAnimation(int tmrNum){
    	delay = (long) r.nextInt(120000);
    	switch(tmrNum){
    	case 1:
    		tmr2.schedule(new TimerTask() {
//    			@Override
    			public void run() {
    				randomAnimation(2);
    			}
    		}, delay);
    		break;
    	case 2:
    		tmr.schedule(new TimerTask() {
//    			@Override
    			public void run() {
    				randomAnimation(1);
    			}
    		}, delay);
    		break;
    	}
    	handler.sendEmptyMessage(0);
    }
    
    private Handler handler = new Handler() {
//        @Override
        public void handleMessage(Message msg) {
        	dog.setVisibility(8);
        	sideDog.setVisibility(0);
        	BitmapDrawable frame1 = (BitmapDrawable)getResources().
            		getDrawable( R.drawable.cola1);
            BitmapDrawable frame2 = (BitmapDrawable)getResources().
            		getDrawable(R.drawable.cola2);
            BitmapDrawable frame3 = (BitmapDrawable)getResources().
            		getDrawable(R.drawable.cola3);
        	int reasonableDuration = 175;
    		AnimationDrawable mAnimation = new AnimationDrawable();
    		mAnimation.addFrame(frame1, reasonableDuration);
    		mAnimation.addFrame(frame2, reasonableDuration);
    		mAnimation.addFrame(frame3, reasonableDuration);
    		mAnimation.addFrame(frame2, reasonableDuration);
    		mAnimation.addFrame(frame1, reasonableDuration);
    		mAnimation.addFrame(frame2, reasonableDuration);
    		mAnimation.addFrame(frame3, reasonableDuration);
        	sideDog.setBackgroundDrawable(mAnimation);
    		mAnimation.start();
    		mAnimation.setVisible(true, true);
    		ladrido.start();
        }
    };
    
    @Override
	protected void onStop() {
		ladrido.stop();
		ladrido.release();
		fondoNoche.stop();
		fondoNoche.release();
		super.onStop();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
//    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
            case R.id.configuration_menu: 
            	startActivity( new Intent (this, PreferencesActivity.class));
            	break;
            	
            case R.id.close_session_menu:
            	UI.showExitDialog(MainActivity.this);
//            	finish();
            	break;
        }
        return true;
    }
}


