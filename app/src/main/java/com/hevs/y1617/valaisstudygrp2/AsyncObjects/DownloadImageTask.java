package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by gez on 27.11.2016.
 */

 /* to execute !!!!
  new DownloadImageTask((ImageView)myView.findViewById(R.id.imageView_link))
                    .execute("https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Karte_Lage_Kanton_Wallis_2016.png/1280px-Karte_Lage_Kanton_Wallis_2016.png");
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
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
        bmImage.setImageBitmap(result);
    }
}