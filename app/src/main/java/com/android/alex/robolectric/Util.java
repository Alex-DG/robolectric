package com.android.alex.robolectric;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by alexandrediguida on 22/12/2016.
 */

public class Util {

    /**
     * USERNAME PATTERN :
     * <p>
     * Only letters
     * Special letters are allowed
     * . - ' are allowed
     **/
    public static boolean isUserValid(String userStr) {
        return userStr.matches("[a-zA-ZÀ-ÿ .'-]+");
    }

    /**
     * PASSWORD PATTERN :
     * <p>
     * A digit must occur at least once
     * A lower case letter must occur at least once
     * An upper case letter must occur at least once
     * A special character must occur at least once you can replace with your special characters
     * Length - matches between 6 and 10
     **/
    public static boolean isPassValid(String passStr) {
        return passStr.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,10}$");
    }

    /**
     * HIDE ANDROID KEYBOARD
     **/
    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view != null && inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
