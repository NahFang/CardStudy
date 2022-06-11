package com.nahfang.studycard.fragment.container;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.nahfang.studycard.R;
import com.nahfang.studycard.bean.cardsBean;
import com.nahfang.studycard.components.dialog.DeleteDialog;
import com.nahfang.studycard.utils.BlurBitmap;

import java.util.ArrayList;

public class cardsAdapter extends RecyclerView.Adapter {
    
    public static final int VIEW_TYPE_ADD = 0; //添加按钮
    public static final int VIEW_TYPE_IN  =1; //分类
    public static final int MAX_LENGTH = 5;

    ArrayList<cardsBean> arrayList = new ArrayList<>();
    NoteViewModel noteViewModel;
    NoteFragment fragment; //这里实际上是与fragment耦合了，做法并不推荐，但是现阶段只能想到这样解决。

    public void setFragment(NoteFragment fragment) {
        this.fragment = fragment;
    }

    public void setViewModel (NoteViewModel noteViewModel) {
        this.noteViewModel = noteViewModel;
    }
    public void setArrayList(ArrayList<cardsBean> arrayList) {
        this.arrayList = arrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView  textView;
        private final ImageView imageView;
        private final LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_cards);
            imageView = itemView.findViewById(R.id.img_cards);
            layout = itemView.findViewById(R.id.bt_in_cards);
        }
        public TextView getTextView() {return textView;}
        public ImageView getImageView() {return imageView;}
        public LinearLayout getLayout() {return layout;}
    }
    public static class ViewHodel_bt extends  RecyclerView.ViewHolder {
        private final LinearLayout layout;

        public ViewHodel_bt(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.bt_add_cards);
        }
        public LinearLayout getLayout() {return layout;}
    }
    @Override
    public int getItemViewType(int position) {
        return (position == arrayList.size()) ? VIEW_TYPE_ADD: VIEW_TYPE_IN;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        if(viewType == VIEW_TYPE_IN) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cards,parent,false);
            viewHolder = new ViewHolder(view);
            viewHolder.itemView.setOnClickListener(view1 -> {
                //在这里完成启动内容页
            });

            RecyclerView.ViewHolder finalViewHolder = viewHolder;
            //长按删除逻辑
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //在这里完成对分类的删除
                    DeleteDialog dialog = new DeleteDialog
                            .Builder(parent.getContext())
                            .create();
                    View view_dialog = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_delete,null);
                    TextView bt = (TextView) view_dialog.findViewById(R.id.bt_delete);
                    bt.setOnClickListener((view1 -> {
                        int position = finalViewHolder.getAdapterPosition();
                        arrayList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        //需要在这里对数据库的一些操作进行
                    }));
                    dialog.setContentView(view_dialog);
                    Window window = dialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,400);
                    dialog.show();
                    return true;
                }
            });
        } else if (viewType == VIEW_TYPE_ADD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cards_add,parent,false);
            viewHolder = new ViewHolder(view);
            viewHolder.itemView.setOnClickListener(view1 -> {

                //获取截图
                Bitmap bitmap = fragment.getCurrentBitmap();
                AddCategoryDialog dialog = new AddCategoryDialog.Builder(parent.getContext()).setCancelableOutside(true).create();
                Window window = dialog.getWindow();
                View decorView = window.getDecorView();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //设置导航栏颜
                window.setNavigationBarColor(Color.TRANSPARENT);
                //内容扩展到导航栏
                window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
                Bitmap blurBg = BlurBitmap.blur(parent.getContext(), bitmap);
                window.setBackgroundDrawable(new BitmapDrawable(parent.getResources(), blurBg));
                bitmap.recycle();

                View view_bt = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_add_category,null);
                ImageView bt_cancel = (ImageView) view_bt.findViewById(R.id.bt_cancel_add);
                bt_cancel.setOnClickListener(view_cancel -> {
                    //取消dialog
                    dialog.dismiss();
                });
                ImageView bt_finish = (ImageView) view_bt.findViewById(R.id.bt_finish_add);
                //字数监控
                EditText text = (EditText) view_bt.findViewById(R.id.edit_add);
                TextView count_text = (TextView) view_bt.findViewById(R.id.count_name);
                text.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String content = text.getText().toString();
                        count_text.setText(content.length() + "/"+ MAX_LENGTH);
                    }

                });
                //添加逻辑
                bt_finish.setOnClickListener(view_finish-> {
                    //添加分类逻辑
                    String name = text.getText().toString();
                    if(name.equals("")) {
                        Toast.makeText(parent.getContext(),"卡集名称不能为空噢",Toast.LENGTH_SHORT).show();
                    } else {
                        cardsBean cardsBean = new cardsBean();
                        cardsBean.setName_cards(name);
                        cardsBean.setSrc_cards(R.drawable.ic_cards_1);
                        noteViewModel.addCardCategory(cardsBean);
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view_bt);
                //回收资源
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // 对话框取消时释放背景图bitmap
                        if (blurBg != null && !blurBg.isRecycled()) {
                            blurBg.recycle();
                        }
                    }
                });
                dialog.show();
            });

        }
        assert viewHolder != null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position < arrayList.size()) {
            cardsAdapter.ViewHolder viewHodel = (cardsAdapter.ViewHolder) holder;
            viewHodel.getImageView().setImageResource(arrayList.get(position).getSrc_cards());
            viewHodel.getTextView().setText(arrayList.get(position).getName_cards());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size() + 1;
    }
}
