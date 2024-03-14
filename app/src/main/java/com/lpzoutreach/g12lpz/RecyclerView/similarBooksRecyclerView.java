package com.lpzoutreach.g12lpz.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class similarBooksRecyclerView extends RecyclerView.Adapter<similarBooksRecyclerView.ViewHolder> {

    ArrayList<EbookModel> data;
    Context context;

    public similarBooksRecyclerView(Context context, ArrayList<EbookModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_horizonal_books_preview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(data.get(position).getBookCoverUrl(),data.get(position).getBookName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverBook;
        public TextView tvtitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coverBook = itemView.findViewById(R.id.tv_book_preview_horizonal_books_preview);
            tvtitle = itemView.findViewById(R.id.tv_book_name_horizontal_books_preview);
        }
        public void setData(String urlLogo, String title){
            if(urlLogo!=null){
                Picasso.get().load(accessUrl.BASE_URL +   urlLogo)
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo_dark))
                        .into(coverBook);
            }
            tvtitle.setText(title);
        }
    }
}
