package com.afordev.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by penguo on 2018-05-31.
 */

public class CustomDatePickerDialog {

    private Context mContext;

    private String title;
    private String positiveText, neutralText, negativeText;

    private DialogInterface.OnClickListener positiveListener, negativeListener, neutralListener;
    private DatePicker.OnDateChangedListener dateChangedListener;
    private DatePicker datePicker;
    private TextView tvTitle;
    private LinearLayout layout;

    public CustomDatePickerDialog(Context mContext) {
        this.mContext = mContext;
        initSet();
    }

    /**
     * Default Setting
     */
    private void initSet() {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) li.inflate(R.layout.dialog_datepicker, null);
        datePicker = layout.findViewById(R.id.cdp_datepicker);
        tvTitle = layout.findViewById(R.id.cdp_tv_title);
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
        dateChangedListener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void show(Calendar cal) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog;

        datePicker.init(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) - 1,
                cal.get(Calendar.DATE),
                dateChangedListener);
        if (!title.equals("")) {
            tvTitle.setText(title);
        } else {
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
