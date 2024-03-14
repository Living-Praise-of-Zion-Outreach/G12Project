package com.lpzoutreach.g12lpz.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.themes.themes;

import java.util.ArrayList;

public class holyBibleVersionSelectorRecyclerView extends RecyclerView.Adapter<holyBibleVersionSelectorRecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HolyBibleModel> data;
    public holyBibleVersionSelectorRecyclerView(Context context, ArrayList<HolyBibleModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public holyBibleVersionSelectorRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_holy_bible_version_selector,parent,false);
        return new holyBibleVersionSelectorRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holyBibleVersionSelectorRecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(data.get(position).isSelected()){
            holder.title.setTypeface(null, Typeface.BOLD);
            //holder.card.setStrokeWidth(dynamicObjects.dpToPx(2));

            if(themes.isNightMode(context)){
                //holder.card.setCardBackgroundColor(context.getColor(R.color._fourth));
            }else{
                holder.card.setCardBackgroundColor(context.getColor(R.color.light_gray));
            }



            if(themes.isNightMode(context)){
                holder.title.setTextColor(context.getColor(R.color._light_seven));
                holder.card.setStrokeColor(context.getColor(R.color._light_seven));

            }else{
                holder.title.setTextColor(context.getColor(R.color._light_first));
                holder.card.setStrokeColor(context.getColor(R.color._light_first));
            }
            holder.title.setText(String.valueOf(data.get(position).getShortBibleName()));


            holder.right_icon.setImageResource(R.drawable.ic_icon_check);



        }else{
            holder.title.setText(String.valueOf(data.get(position).getShortBibleName()));
            holder.title.setTypeface(null, Typeface.NORMAL);
            if(themes.isNightMode(context)){
                holder.title.setTextColor(context.getColor(R.color.light_gray));
            }else{
                holder.title.setTextColor(context.getColor(R.color.primary_text));
            }

            if(data.get(position).isDownloaded()){
                holder.right_icon.setImageResource(R.drawable.ic_icon_offline_bible);
            }else{
                holder.right_icon.setImageResource(R.drawable.ic_icon_download);
            }
            holder.card.setStrokeWidth(0);
        }

        holder.subTitle.setText(data.get(position).getLongBibleName());
        holder.language.setText(data.get(position).getLanguage());



        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get(position).isDownloaded()){
                    String path = data.get(position).getAndroidPath();
                    data.get(position).getEventListenerUtil().myCallBack(data.get(position).getActionEvent(),data.get(position).getShortBibleName() + "~" + data.get(position).getAndroidPath() + "~" + data.get(position).getBibleType());
                }else{
                    data.get(position).getEventListenerUtil().myCallBack("download-holy-bible",data.get(position).getHolyBibleID());
                }
           }
        });

    }

    @Override  public int getItemCount() {return data.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView title;
        TextView subTitle;
        TextView language;
        ImageView right_icon;
        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cv_holy_bible_version_selector);
            title = itemView.findViewById(R.id.tv_title_holy_bible_version_selector);
            subTitle = itemView.findViewById(R.id.tv_subtitle_holy_bible_version_selector);
            language = itemView.findViewById(R.id.tv_language_holy_bible_version_selector);
            right_icon = itemView.findViewById(R.id.iv_icon_arrow_list_lessons);
        }
    }
}
