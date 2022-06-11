package com.nahfang.studycard.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nahfang.studycard.R;

/**
 * 内容展示框
 */

public class ContentDialog extends Dialog{


    public ContentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
   public static class Builder {
       private Context context;
       private String msg;
       private boolean isCancelable = true;
       private boolean isCancleOutside = false;
       private double Height_factor = 0.7;

       public Builder (Context context) {
           this.context = context;
       }

       public Builder setMessage (String msg) {
           this.msg = msg;
           return this;
       }

       public Builder setCancelable (boolean isCancelable) {
           this.isCancelable = isCancelable;
           return this;
       }

       public Builder setOutsiCancel (boolean isCancelOutside) {
           this.isCancleOutside = isCancelOutside;
           return this;
       }
       public Builder setHeightFactor(double factor) {
           this.Height_factor = factor;
           return this;
       }

       public ContentDialog create () {
           ContentDialog dialog = new ContentDialog(context, R.style.DialogTheme);
           View view = LayoutInflater.from(context).inflate(R.layout.dialog_content,null);
           dialog.setCancelable(isCancelable);
           dialog.setCanceledOnTouchOutside(isCancleOutside);
           if (isCancelable) {
               ImageView imageView = view.findViewById(R.id.bt_close);
               imageView.setVisibility(View.VISIBLE);
               imageView.setClickable(true);
               imageView.setOnClickListener(view1 -> dialog.dismiss());
           }
           TextView textView = view.findViewById(R.id.text_content);
           textView.setText(msg);

           dialog.setContentView(view);
           Window window = dialog.getWindow();
           window.setGravity(Gravity.BOTTOM);
           window.setWindowAnimations(R.style.DialogTheme);
           WindowManager windowManager = window.getWindowManager();
           Display display = windowManager.getDefaultDisplay();
           int height = (int) ((int) display.getHeight() * Height_factor);
           window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,height);

           return dialog;
       }
    }
}
