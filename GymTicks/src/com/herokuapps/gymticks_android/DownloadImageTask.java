package com.herokuapps.gymticks_android;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    int dpWidth = 100;
    int dpHeight = 100;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public DownloadImageTask(ImageView bmImage, int dpWidth, int dpHeight) {
        this.bmImage = bmImage;
        this.dpWidth = dpWidth;
        this.dpHeight = dpHeight;
    }
    
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
    		bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, dpWidth, dpHeight, true));
    }
}