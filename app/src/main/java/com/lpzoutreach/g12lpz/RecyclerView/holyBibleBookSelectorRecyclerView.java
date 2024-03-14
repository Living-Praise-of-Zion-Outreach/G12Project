package com.lpzoutreach.g12lpz.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.themes.themes;

import java.util.ArrayList;

public class holyBibleBookSelectorRecyclerView extends RecyclerView.Adapter<holyBibleBookSelectorRecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<HolyBibleModel> data;

    public holyBibleBookSelectorRecyclerView(Context context, ArrayList<HolyBibleModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public holyBibleBookSelectorRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_holy_bible_book_selector,parent,false);
        return new holyBibleBookSelectorRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holyBibleBookSelectorRecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(data.get(position).isSelected()){
            holder.title.setTypeface(null, Typeface.BOLD);
            if(themes.isNightMode(context)){
                holder.title.setTextColor(context.getColor(R.color._light_seven));
            }else{
                holder.title.setTextColor(context.getColor(R.color._light_first));
            }
            holder.title.setText(data.get(position).getLong_name());
        }else{
            holder.title.setText(data.get(position).getLong_name());
            holder.title.setTypeface(null, Typeface.NORMAL);
            if(themes.isNightMode(context)){
                holder.title.setTextColor(context.getColor(R.color.light_gray));
            }else{
                holder.title.setTextColor(context.getColor(R.color.primary_text));
            }
        }

        holder.card.setOnClickListener(view -> data.get(position).getEventListenerUtil().myCallBack(data.get(position).getActionEvent(),data.get(position).getBook_number()));
        holder.card.setOnLongClickListener(view -> {
            data.get(position).getEventListenerUtil().myCallBack(data.get(position).getActionEvent(),data.get(position).getBook_number());
            return false;
        });

    }

    @Override
    public int getItemCount() {return data.size();}

    @SuppressLint("NotifyDataSetChanged")
    public void resetAllColor(int position){
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.item_cv_list_holy_bible_book_selector);
            title = itemView.findViewById(R.id.tv_title_holy_bible_book_selector);
        }
    }
}
