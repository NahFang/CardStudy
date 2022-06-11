package com.nahfang.studycard.fragment.container;

import static androidx.fragment.app.FragmentManager.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.nahfang.studycard.MainActivity;
import com.nahfang.studycard.R;
import com.nahfang.studycard.utils.BlurBitmap;

public class AddCategoryDialog extends Dialog {
    public AddCategoryDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    public static class Builder {
        private Context context;
        private Boolean isCancelable = true;
        private Boolean isCancelOutside = false;

        public Builder(Context context) {
            this.context = context;
        }
        public Builder setIsCancelable (Boolean bool) {
            this.isCancelable = bool;
            return this;
        }

        public Builder setCancelableOutside(Boolean cancelableOutside) {
            isCancelOutside = cancelableOutside;
            return this;
        }

        public AddCategoryDialog create () {
            AddCategoryDialog dialog = new AddCategoryDialog(context, R.style.SquareEntranceDialogStyle);
            dialog.setCancelable(isCancelable);
            dialog.setCanceledOnTouchOutside(isCancelOutside);

            return dialog;
        }
    }
}
