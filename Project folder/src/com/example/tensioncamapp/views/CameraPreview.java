package com.example.tensioncamapp.views;

/** 
 * @author Lisa Rythén Larsson, Fredrik Johansson
 * @copyright Lisa Rythén Larsson, Fredrik Johansson, Max Dubois, Martin Falk Danauskis
 * @param responeanswer: result from the analyze 
 *  */

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** A basic Camera preview class */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "PreviewAactivity";
	private SurfaceHolder mHolder;
    private Camera mCamera;
    
    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        this.mHolder = getHolder();
        this.mHolder.addCallback(this);
	}

	/**Displays the picture on the camera */
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
    	try {
            this.mCamera.setPreviewDisplay(holder);
            this.mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
      //not uses
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    	//not used
    }

    
    }