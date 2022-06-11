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

import androidx.annotation.NonNull;

import com.nahfang.studycard.R;

public class DeleteDialog extends Dialog {
    public DeleteDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    public static class Builder {
        private Context context;
        private Boolean isCancelable = true;
        private Boolean isCancelOutside = true;
        private double Height_factor = 0.2;

        public Builder (Context context) {
            this.context = context;
        }

        public Builder setCancelable (Boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }
        public Builder setCancelOutside (Boolean isCancelOutside) {
            this.isCancelOutside = isCancelOutside;
            return this;
        }
        public Builder setFactor (double factor) {
            this.Height_factor = factor;
            return this;
        }

        public DeleteDialog create () {
            DeleteDialog dialog = new DeleteDialog(context, R.style.DialogTheme);
            dialog.setCancelable(isCancelable);
            dialog.setCanceledOnTouchOutside(isCancelOutside);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.DialogTheme);

            //后续改造 希望能将点击事件传递过来，这样可以大大减少adapter的体积

           /* WindowManager windowManager = window.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int height = (int) ((int) display.getHeight() * Height_factor);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,height);*/

            return dialog;
        }
    }
}
