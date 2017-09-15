package me.lsran.gankapp.utils;

import android.text.TextUtils;
import android.widget.Toast;

import me.lsran.gankapp.App;

/**
 * @author lssRan
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(String text) {
        if (TextUtils.isEmpty(text)) return;

        String message = text.trim();
        if (!TextUtils.isEmpty(message)) {
            if (mToast == null) {
                mToast = Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
        }
    }

    public static void showToast(String text, int showTime) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, showTime);
        } else {
            mToast.setText(text);
            mToast.setDuration(showTime);
        }
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}

