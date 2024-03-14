package com.lpzoutreach.g12lpz.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.object.dynamicObjects;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class searchEBookRecyclerView extends RecyclerView.Adapter<searchEBookRecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<EbookModel> data;

    public searchEBookRecyclerView(Context context, ArrayList<EbookModel> data){
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public searchEBookRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_ebooks,parent,false);
        return new searchEBookRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchEBookRecyclerView.ViewHolder holder, int position) {
        if(!data.get(position).getBookCoverUrl().isEmpty()){
            Picasso.get().load(accessUrl.BASE_URL + data.get(position).getBookCoverUrl())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(holder.logo);
        }else if(!data.get(position).getAndroidPhotoPath().isEmpty()) {
            holder.logo.setImageResource(R.drawable.default_photo);
            Picasso.get().load(data.get(position).getAndroidPhotoPath())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(holder.logo);
        }else{
            holder.logo.setImageResource(R.drawable.default_photo);
        }

        holder.bookName.setText(data.get(position).getBookName());
        holder.views.setText(data.get(position).getViews());
        holder.favorite.setText(data.get(position).getFavorites());
        holder.download.setText(data.get(position).getDownloads());
        holder.shortDescription.setText(data.get(position).getBookShortDescription());

        String[] tags = data.get(position).getBookTag().split(",");

        holder.tag.removeAllViews();

        for(int i=0;i<tags.length;i++){
            if(i==0)
                dynamicObjects.create_tag_label(context,holder.tag,tags[i],0,data.get(position).getEventListener());
            else
                dynamicObjects.create_tag_label(context,holder.tag,tags[i],10,data.get(position).getEventListener());
        }

        holder.card.setOnClickListener(v ->
                data.get(position).getEventListener().myCallBack(data.get(position).getActionEvent(), data.get(position).getEbookID())
        );

        holder.card.setOnLongClickListener(view -> {
            data.get(position).getEventListener().myCallBack(data.get(position).getActionEvent(), data.get(position).getEbookID());
            return false;
        });
    }

    @Override
    public int getItemCount() {return data.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView logo;
        TextView bookName;
        TextView views;
        TextView favorite;
        TextView download;
        TextView shortDescription;
        LinearLayout tag;

        public ViewHolder(@NonNull View mView) {
            super(mView);
             card = mView.findViewById(R.id.item_cv_list_ebooks);
             logo = mView.findViewById(R.id.iv_cover_book_ebooks);
             bookName = mView.findViewById(R.id.tv_title_list_ebook);
             views = mView.findViewById(R.id.tv_views_ebooks);
             favorite = mView.findViewById(R.id.tv_favorite_ebooks);
             download = mView.findViewById(R.id.tv_download_ebooks);
             shortDescription = mView.findViewById(R.id.tv_short_description_list_ebooks);
             tag = mView.findViewById(R.id.ll_tag_ebooks);
        }
    }
}
