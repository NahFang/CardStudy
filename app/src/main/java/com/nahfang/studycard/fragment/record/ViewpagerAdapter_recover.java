package com.nahfang.studycard.fragment.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahfang.studycard.R;
import com.nahfang.studycard.bean.cardBean;

import java.util.ArrayList;

public class ViewpagerAdapter_recover extends RecyclerView.Adapter<ViewpagerAdapter_recover.ViewHolder>{

    private ArrayList<cardBean> arrayList = new ArrayList<>();
    private RecoverViewModel viewModel;

    public void setArrayList(ArrayList<cardBean> arrayList) {
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void setViewModel(RecoverViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bt_showAnswer;
        TextView textContent;
        TextView bt_recover;
        TextView textTitle;
        TextView bt_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bt_showAnswer = (TextView) itemView.findViewById(R.id.bt_showAnswerInside_recover);
            textContent = (TextView) itemView.findViewById(R.id.text_content_recover);
            bt_recover = (TextView) itemView.findViewById(R.id.bt_recover);
            textTitle = (TextView) itemView.findViewById(R.id.text_title_recover);
            bt_delete = (TextView) itemView.findViewById(R.id.bt_delete_card_recover);
        }

        public TextView getBt_delete() {
            return bt_delete;
        }

        public TextView getBt_showAnswer() {
            return bt_showAnswer;
        }

        public TextView getBt_recover() {
            return bt_recover;
        }

        public TextView getTextContent() {
            return textContent;
        }

        public TextView getTextTitle() {
            return textTitle;
        }
    }

    @NonNull
    @Override
    public ViewpagerAdapter_recover.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_recover,parent,false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.getBt_showAnswer().setOnClickListener((view1)->{
            if (viewHolder.getTextContent().getVisibility() == View.GONE) {
                viewHolder.getTextContent().setVisibility(View.VISIBLE);
                viewHolder.getBt_showAnswer().setVisibility(View.GONE);
            }
        });
        viewHolder.getBt_recover().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardBean cardBean = arrayList.get(viewHolder.getAdapterPosition());
                arrayList.remove(cardBean);
                viewModel.recoverCard(cardBean);
            }
        });
        viewHolder.getBt_delete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardBean cardBean = arrayList.get(viewHolder.getAdapterPosition());
                arrayList.remove(cardBean);
                viewModel.deleteCard(cardBean);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewpagerAdapter_recover.ViewHolder holder, int position) {
        holder.getTextContent().setText(arrayList.get(position).getContent());
        holder.getTextTitle().setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
