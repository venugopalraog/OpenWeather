package com.sample.openweather.utils;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

import com.sample.openweather.R;

import org.apache.commons.lang3.StringUtils;

import timber.log.Timber;

public class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void setTextToTextView(TextView textView, String text) {
        if (StringUtils.isEmpty(text) || textView == null) {
            Timber.tag(TAG).d("Invalid parameters - return");
            return;
        }

        textView.setText(text);
        textView.setContentDescription(text);
    }

    public static Dialog createProgressBarDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.ProgressDialogTheme);
        dialog.setContentView(R.layout.dialog_progressbar);
        dialog.show();
        return dialog;
    }

    public static void hideProgressBar(Dialog dialog) {
        if (dialog != null)
            dialog.dismiss();
    }

}