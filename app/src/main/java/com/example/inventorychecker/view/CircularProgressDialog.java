package com.example.inventorychecker.view;

import android.app.Dialog;
import android.content.Context;

import com.example.inventorychecker.activity.R;

/**
 * Created by HP on 5/5/2016.
 */
public class CircularProgressDialog extends Dialog {

    public CircularProgressDialog(Context context) {
        super(context);
        init();
    }

    public CircularProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected CircularProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init(){
        setContentView(R.layout.view_circle_progress_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }
}
