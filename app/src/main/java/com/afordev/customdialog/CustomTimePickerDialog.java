package com.afordev.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by penguo on 2018-05-31.
 */

public class CustomTimePickerDialog {

    private Context mContext;

    private String title;
    private String positiveText, neutralText, negativeText;

    private DialogInterface.OnClickListener positiveListener, negativeListener, neutralListener;
    private TimePicker timePicker;
    private TextView tvTitle;
    private LinearLayout layout;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    public CustomTimePickerDialog(Context mContext) {
        this.mContext = mContext;

        initSet();
    }

    /**
     * Default Setting
     */
    private void initSet() {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) li.inflate(R.layout.dialog_timepicker, null);
        timePicker = layout.findViewById(R.id.ctp_timepicker);
        tvTitle = layout.findViewById(R.id.ctp_tv_title);
        positiveText = "Finish";
        neutralText = "";
        negativeText = "Cancel";
        positiveListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        negativeListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
    }

    public void setPositiveListener(String text, DialogInterface.OnClickListener positiveListener) {
        this.positiveText = text;
        this.positiveListener = positiveListener;
    }

    public void setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
    }

    public void setNegativeListener(String text, DialogInterface.OnClickListener negativeListener) {
        this.negativeText = text;
        this.negativeListener = negativeListener;
    }

    public void setNeutralListener(String text, DialogInterface.OnClickListener neutralListener) {
        this.neutralText = text;
        this.neutralListener = neutralListener;
    }

    public TimePicker getTimePicker() {
        return timePicker;
    }

    public void show(Calendar cal) {
        builder = new AlertDialog.Builder(mContext);
        int hour, minute;
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        } else {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
        if(!title.equals("")){
            tvTitle.setText(title);
        }else{
            tvTitle.setVisibility(View.GONE);
        }

        builder.setView(layout);

        if (positiveListener != null) {
            builder.setPositiveButton(positiveText, positiveListener);
        }
        if (negativeListener != null) {
            builder.setNegativeButton(negativeText, negativeListener);
        }
        if (neutralListener != null) {
            builder.setNeutralButton(neutralText, neutralListener);
        }

        dialog = builder.create();
        dialog.show();
    }
}
