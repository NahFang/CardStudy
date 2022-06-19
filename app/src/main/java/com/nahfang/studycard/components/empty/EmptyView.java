package com.nahfang.studycard.components.empty;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.nahfang.studycard.R;

/**
 * 废弃
 */
public class EmptyView extends androidx.appcompat.widget.AppCompatImageView {
    public EmptyView(Context context) {
        super(context);
        this.setImageResource(R.drawable.bg_empty);
    }
    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setImageResource(R.drawable.bg_empty);
    }
}
