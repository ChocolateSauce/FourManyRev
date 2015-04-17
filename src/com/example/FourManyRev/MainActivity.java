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

	Rect img1 = new Rect(0,0,128,128);
	Rect img2 = new Rect(128,0,256,128);
	Rect img3 = new Rect(256,0,384,128);
	Rect img4 = new Rect(384,0,512,128);
	Rect img5 = new Rect(512,0,640,128);
	Rect img6 = new Rect(640,0,768,128);
	Rect img7 = new Rect(768,0,896,128);
	Rect img8 = new Rect(896,0,1024,128);
	Rect black = new Rect(0,128,128,256);
	//Rect black = new Rect(1152,0,1280,128);
	
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
    
    GLSurfaceView glSurf;

	
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
    		resourceIds = new int[] {R.drawable.test_all};
        

        
        glSurf.setRenderer(new SpriteBatcher(this, resourceIds, this));

		
	}
	
    protected void onPause() {
        super.onPause();
        glSurf.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurf.onResume();
    }

	@Override
	public void onDrawFrame(GL10 gl, SpriteBatcher sb) {
		timenow = System.nanoTime()/1000000.0f;  //capture the time once in miliseconds
	    deltatime = timenow-timelast;
	    if (!pause) {
			if (image_num == 0) {
				sb.draw(R.drawable.test_all, img1, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
//			} else if (image_num == 1) {   //7
//				sb.draw(R.drawable.test_4, img2, new Rect(0, 0,
//						sb.getViewWidth(), sb.getViewHeight()));
//				image_num++;
			} else if (image_num == 2) {    //2
				sb.draw(R.drawable.test_all, img3, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
//			} else if (image_num == 3) {   //9
//				sb.draw(R.drawable.test_4, img4, new Rect(0, 0,
//						sb.getViewWidth(), sb.getViewHeight()));
//				image_num++;
			} else if (image_num == 4) {   //4
				sb.draw(R.drawable.test_all, img5, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				image_num++;
//			} else if (image_num == 5) {   //11
//				sb.draw(R.drawable.test_4, img6, new Rect(0, 0,
//						sb.getViewWidth(), sb.getViewHeight()));
//				image_num = 0;
			} else {
				sb.draw(R.drawable.test_all, black, new Rect(0, 0,
						sb.getViewWidth(), sb.getViewHeight()));
				if (image_num == 5)
					image_num = 0;
				else
					image_num++;
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
