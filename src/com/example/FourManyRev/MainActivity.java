package com.example.FourManyRev;

import java.io.File;

import javax.microedition.khronos.opengles.GL10;

import com.twicecircled.spritebatcher.Drawer;
import com.twicecircled.spritebatcher.SpriteBatcher;

import android.app.Activity;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity implements Drawer{

//	Rect img1 = new Rect(0,0,128,228);
//	Rect img2 = new Rect(128,0,256,228);
//	Rect img3 = new Rect(256,0,384,228);
//	Rect img4 = new Rect(384,0,512,228);
//	Rect img5 = new Rect(512,0,640,228);
//	Rect img6 = new Rect(640,0,768,228);
//	//Rect img7 = new Rect(768,0,896,228);
//	//Rect img8 = new Rect(896,0,1024,228);
//	Rect black = new Rect(896,0,1024,228);
//	Rect blue = new Rect(768,0,896,128);
	
	Rect img1 = new Rect(0,0,128,228);
	Rect img2 = new Rect(128,0,256,228);
	Rect img3 = new Rect(256,0,384,228);
	Rect img4 = new Rect(384,0,512,228);
	Rect img5 = new Rect(512,0,640,228);
	Rect img6 = new Rect(640,0,768,228);
	//Rect img7 = new Rect(768,0,896,228);
	//Rect img8 = new Rect(896,0,1024,228);
	Rect black = new Rect(896,0,1024,228);
	Rect blue = new Rect(768,0,896,128);
	
	float timenow,deltatime,timelast;
	int image_num = 0;
	boolean pause = true;
	int[] resourceIds;
	
    TwoFingerDoubleTapListener multiTouchListener = new TwoFingerDoubleTapListener() {
        @Override
        public void onTwoFingerDoubleTap() {
            Toast.makeText(MainActivity.this, "Two Finger Double Tap", Toast.LENGTH_SHORT).show();
            pause = !pause;
        }
    };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GLSurfaceView glSurf = new GLSurfaceView(this);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
    	File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/animation_sprite");
    	Log.v("downloads_path", path.getAbsolutePath());
        
        setContentView(glSurf);
        

    	if(path.exists() && path.isDirectory()) {
            Toast.makeText(MainActivity.this, "SD card images", Toast.LENGTH_SHORT).show();
            resourceIds = new int[] {R.string.animation_sprite};
    	} else
    		resourceIds = new int[] {R.drawable.sprite6teapot};
        

        
        glSurf.setRenderer(new SpriteBatcher(this, resourceIds, this));

		
	}

	@Override
	public void onDrawFrame(GL10 gl, SpriteBatcher sb) {
		timenow = System.nanoTime()/1000000.0f;  //capture the time once in miliseconds
	    deltatime = timenow-timelast;
	    if (!pause) {
			if (image_num == 0) {
				sb.draw(R.drawable.sprite6teapot, img1, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
			} else if (image_num == 1) {   //7
				sb.draw(R.drawable.sprite6teapot, img2, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
			} else if (image_num == 2) {    //2
				sb.draw(R.drawable.sprite6teapot, img3, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
			} else if (image_num == 3) {   //9
				sb.draw(R.drawable.sprite6teapot, img4, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
			} else if (image_num == 4) {   //4
				sb.draw(R.drawable.sprite6teapot, img5, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
			} else if (image_num == 5) {   //11
				sb.draw(R.drawable.sprite6teapot, img6, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num = 0;
			} else {
				if (image_num == 5)
					image_num = 0;
				else {
					sb.draw(R.drawable.sprite6teapot, black, new Rect(0, 0,
							sb.getViewWidth(), sb.getViewHeight()));
					image_num++;
				}
			}
			//gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			//gl.glFlush();
		}
		Log.v("FrameTime","Millis between this frame and prev: "+(deltatime));
		timelast = timenow;
		
	}
	
    public boolean onTouchEvent(MotionEvent event) {
      	return multiTouchListener.onTouchEvent(event);
    }
    
}
