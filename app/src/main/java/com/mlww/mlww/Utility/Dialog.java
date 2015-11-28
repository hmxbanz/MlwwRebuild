package com.mlww.mlww.Utility;

        import android.app.Activity;
        import android.content.Context;
        import android.content.res.Configuration;
        import android.content.res.Resources;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.os.Handler;
        import android.util.DisplayMetrics;
        import android.view.Display;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.Window;
        import android.view.WindowManager;
       //import android.view.WindowManager.LayoutParams;
        import android.widget.LinearLayout;
        import android.widget.LinearLayout.LayoutParams;
        import android.widget.TextView;

        import com.mlww.mlww.R;

/**
 * Created by root on 2015/11/27.
 */


    public class Dialog {
    public static final int RADIO_DIALOG = 2;
    public static final int SELECT_DIALOG = 1;

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private static android.app.Dialog ShowDialog(Context context, String title, String toastText, final DialogClickListener dialogClickListener, int showType) {
        final android.app.Dialog dialog = new android.app.Dialog(context, R.style.DialogStyle);
        dialog.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.point)).setText(title);
        ((TextView) view.findViewById(R.id.toast)).setText(toastText);

        if (showType !=   RADIO_DIALOG  ) {
            view.findViewById(R.id.ok).setVisibility(View.GONE);
            view.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogClickListener.cancel();
                    }
                },200);
            }
        });

        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogClickListener.confirm();
                    }
                },200);
            }
        });

        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogClickListener.confirm();
                    }
                },200);
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (context.getResources().getConfiguration().orientation == 2)
        {
                layoutParams.width = (8 * (getScreenHeight(context) / 10));
                layoutParams.width = (8 * (getScreenWidth(context) / 10));
        }
            window.setAttributes(layoutParams);

        dialog.show();
        return dialog;
    }

    private static android.app.Dialog ShowDialog(Context context, String title, String[] itemList, final DialogItemClickListener dialogItemClickListener)
    {
        final android.app.Dialog dialog = new android.app.Dialog(context,  R.style.DialogStyle);
        dialog.setCancelable(false);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_radio, null);
        dialog.setContentView(dialogView);
        ((TextView)dialogView.findViewById(R.id.title)).setText(title);
        LinearLayout localLinearLayout = (LinearLayout)dialogView.findViewById(R.id.dialogLayout);
        localLinearLayout.removeAllViews();
        dialogView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                dialog.dismiss();
            }
        });
        if(itemList==null) {
            dialog.show();
            return dialog;
        }
        for (int x=0;x<itemList.length;x++) {
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
            localLayoutParams.rightMargin = 1;

            final TextView textView = new TextView(context);
            textView.setLayoutParams(localLayoutParams);

            textView.setTextSize(18);
            textView.setText(itemList[x]);
            textView.setTextColor(context.getResources().getColor(R.color.dialog_Txt_blue));

            int k = context.getResources().getDimensionPixelSize(R.dimen.padding_photo_name_small);
            textView.setPadding(k, k, k, k);
            textView.setSingleLine(true);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setBackgroundResource(R.drawable.menudialog_center_selector);
            //textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
            if(x==itemList.length-1)
            textView.setBackgroundResource(R.drawable.menudialog_bottom2_selector);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    dialog.dismiss();
                    dialogItemClickListener.confirm(textView.getText().toString());
                }
            });
            localLinearLayout.addView(textView);


            TextView localTextView2 = new TextView(context);
            localTextView2.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
           localTextView2.setBackgroundResource(R.color.app_text_gray);
           localLinearLayout.addView(localTextView2);
        }


        Window localWindow = dialog.getWindow();
        WindowManager.LayoutParams localLayoutParams1 = localWindow.getAttributes();
        localLayoutParams1.width = getScreenWidth(context);
        localWindow.setGravity(80);
        localWindow.setWindowAnimations(R.style.dialogAnim);
        localWindow.setAttributes(localLayoutParams1);

        dialog.show();
        return dialog;
    }


    public static android.app.Dialog showListDialog(Context context, String title, String[] itemList, DialogItemClickListener dialogItemClickListener)
    {
        return ShowDialog(context, title, itemList, dialogItemClickListener);
    }

    public static android.app.Dialog showRadioDialog(Context context, String title, String message, DialogClickListener dialogClickListener)
    {
        return ShowDialog(context, title, message, dialogClickListener, RADIO_DIALOG);
    }

    public static android.app.Dialog showSelectDialog(Context context, String message, DialogClickListener dialogClickListener)
    {
        return ShowDialog(context, context.getResources().getString(R.string.pointMessage), message, dialogClickListener, SELECT_DIALOG);
    }

    public static android.app.Dialog showSelectDialog(Context context, String title, String message, DialogClickListener dialogClickListener)
    {
        return ShowDialog(context, title, message, dialogClickListener, SELECT_DIALOG);
    }

   public  interface DialogClickListener {
        void cancel();

        void confirm();
    }
   public  interface DialogItemClickListener {
        void confirm(String paramString);
    }
}