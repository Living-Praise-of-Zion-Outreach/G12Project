package com.lpzoutreach.g12lpz.RecyclerView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.themes.themes;

import java.util.ArrayList;

public class holyBibleVerseSelectorRecyclerView extends RecyclerView.Adapter<holyBibleVerseSelectorRecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HolyBibleModel> data;

    public holyBibleVerseSelectorRecyclerView(Context context, ArrayList<HolyBibleModel> data){
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public holyBibleVerseSelectorRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_holy_bible_chapter_verse_selector,parent,false);
        return new holyBibleVerseSelectorRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holyBibleVerseSelectorRecyclerView.ViewHolder holder, int position) {

        if(data.get(position).isSelected()){
            holder.title.setTypeface(null, Typeface.BOLD);
            if(themes.isNightMode(context)){
                holder.title.setTextColor(context.getColor(R.color._light_seven));
            }else{
                holder.title.setTextColor(context.getColor(R.color._light_first));
            }
            holder.title.setText(String.valueOf(data.get(position).getVerse()));
        }else{
            holder.title.setText(String.valueOf(data.get(position).getVerse()));
            holder.title.setTypeface(null, Typeface.NORMAL);
            if(themes.isNightMode(context)){
                holder.title.setTextColor(context.getColor(R.color.light_gray));
            }else{
                holder.title.setTextColor(context.getColor(R.color.primary_text));
            }
        }

        if(data.get(position).isIntroduction()){
            holder.icon.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
        }else{
            holder.icon.setVisibility(View.GONE);
            holder.title.setVisibility(View.VISIBLE);
        }

        holder.card.setOnClickListener(view -> data.get(position).getEventListenerUtil().myCallBack(data.get(position).getActionEvent(),data.get(position).getVerse()));
        holder.card.setOnLongClickListener(view -> {
            data.get(position).getEventListenerUtil().myCallBack(data.get(position).getActionEvent(),data.get(position).getVerse());
            return false;
        });
    }

    @Override  public int getItemCount() {return data.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.item_cv_list_holy_bible_chapter_verse_selector);
            title = itemView.findViewById(R.id.tv_title_holy_bible_chapter_verse_selector);
            icon = itemView.findViewById(R.id.iv_icon_holy_bible_chapter_verse_selector);
        }
    }
}
