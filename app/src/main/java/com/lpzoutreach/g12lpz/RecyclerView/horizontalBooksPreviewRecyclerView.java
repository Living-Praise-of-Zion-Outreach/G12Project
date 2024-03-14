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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class horizontalBooksPreviewRecyclerView extends RecyclerView.Adapter<horizontalBooksPreviewRecyclerView.ViewHolder>{
    private ArrayList<EbookModel> data;
    private Context context;

    public horizontalBooksPreviewRecyclerView(Context context,ArrayList<EbookModel> data){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public horizontalBooksPreviewRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_horizonal_books_preview,parent,false);
        return new horizontalBooksPreviewRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(data.get(position).getBookCoverUrl()!=null){
            Picasso.get().load(data.get(position).getBookCoverUrl())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo_dark))
                    .into(holder.coverBook);
        }
        holder.tvtitle.setText(data.get(position).getBookName());
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
    }
}
