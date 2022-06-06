package com.nahfang.studycard.fragment.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahfang.studycard.R;

import java.util.ArrayList;

public class DrawerRVAdapter extends RecyclerView.Adapter<DrawerRVAdapter.ViewHodle> {

    private ArrayList<String> datalist;
    private CardViewModel cardViewModel;

    public void setDatalist(ArrayList<String> list) {
        datalist = list;
    }
    public void setCardViewModel(CardViewModel cardViewModel) {
        this.cardViewModel = cardViewModel;
    }

    public static class ViewHodle extends RecyclerView.ViewHolder{
        private final TextView textView;

        public ViewHodle(@NonNull View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.name);
        }
        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public DrawerRVAdapter.ViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_drawer,parent,false);
        RecyclerView.ViewHolder viewHodler = new ViewHodle(view);
        viewHodler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在这里完成切换分类的调用逻辑
                String category = datalist.get(viewHodler.getPosition());
                cardViewModel.ToggleCategory(category);
            }
        });
        return (ViewHodle) viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerRVAdapter.ViewHodle holder, int position) {
        holder.getTextView().setText(datalist.get(position));
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
