package com.example.petquotes.app;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.EditText;

import com.example.petquotes.R;

public class Helper {

    public static String getTextEt(EditText editText) {
        return editText.getText().toString();
    }

    public static Boolean isEmptyEt(EditText editText) {
        return getTextEt(editText).isEmpty();
    }

    public static void changeColorActionBar(androidx.appcompat.app.ActionBar actionBar) {
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c10059")));
    }

}
