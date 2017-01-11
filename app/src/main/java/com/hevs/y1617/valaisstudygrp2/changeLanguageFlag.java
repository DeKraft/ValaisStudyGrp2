package com.hevs.y1617.valaisstudygrp2;

import android.widget.ImageButton;

import java.util.Locale;

/**
 * Created by Romain Coupy on 07.12.2016.
 */

public class changeLanguageFlag {

    private static String locale;

    public static void changeLanguageFlag(ImageButton imgBtn) {

        locale = Locale.getDefault().getISO3Language();

        switch (locale)
        {
            case "fra":
                imgBtn.setImageResource(R.mipmap.ic_french_flag);
                break;
            case "deu":
                imgBtn.setImageResource(R.mipmap.ic_german_flag);
                break;
        }

    }

}
