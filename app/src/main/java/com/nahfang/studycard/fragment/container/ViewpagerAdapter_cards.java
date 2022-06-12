package com.nahfang.studycard.fragment.container;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahfang.studycard.R;
import com.nahfang.studycard.bean.cardBean;

import java.util.ArrayList;

public class ViewpagerAdapter_cards extends RecyclerView.Adapter<ViewpagerAdapter_cards.ViewHolder> {

    private ArrayList<cardBean> arrayList = new ArrayList<>();


    public static class ViewHolder extends RecyclerView.ViewHolder{
       TextView bt_showAnswer;
       TextView textContent;
       TextView bt_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bt_showAnswer = (TextView) itemView.findViewById(R.id.bt_showAnswerInside);
            textContent = (TextView) itemView.findViewById(R.id.text_content);
            bt_update = (TextView) itemView.findViewById(R.id.bt_updateInside);
        }

       public TextView getBt_showAnswer() {
           return bt_showAnswer;
       }

       public TextView getBt_update() {
           return bt_update;
       }

       public TextView getTextContent() {
           return textContent;
       }
   }

   public void setArrayList (ArrayList<cardBean> cardBeans) {
        this.arrayList = cardBeans;
   }

    @NonNull
    @Override
    public ViewpagerAdapter_cards.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_viewpager,parent,false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
       ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.getBt_showAnswer().setOnClickListener((view1)->{
            if (viewHolder.getTextContent().getVisibility() == View.GONE) {
                viewHolder.getTextContent().setVisibility(View.VISIBLE);
                viewHolder.getBt_showAnswer().setVisibility(View.GONE);
            }
        });
        viewHolder.getBt_update().setOnClickListener((view1)->{
            //打开编辑页面
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewpagerAdapter_cards.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size()+3;
    }
}
