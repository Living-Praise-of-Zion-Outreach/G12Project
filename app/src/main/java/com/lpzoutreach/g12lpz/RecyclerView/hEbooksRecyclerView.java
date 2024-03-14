package com.lpzoutreach.g12lpz.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class hEbooksRecyclerView extends RecyclerView.Adapter<hEbooksRecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<EbookModel> data;
    private TextView title_book;
    private TextView short_desc_book;
    private elUtil eventListenerUtil;

    private CardView footer_book;
    private CardView footer_see_details_book;

    private int row_index = 0;

    public hEbooksRecyclerView(Context context, ArrayList<EbookModel> data, TextView title_book, TextView short_desc_book, elUtil eventListenerUtil, CardView footer_book,CardView footer_see_details_book){
        this.context = context;
        this.data = data;
        this.title_book = title_book;
        this.short_desc_book = short_desc_book;
        this.eventListenerUtil = eventListenerUtil;
        this.footer_book = footer_book;
        this.footer_see_details_book = footer_see_details_book;
    }

    @NonNull
    @Override
    public hEbooksRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_horizonal_books_preview,parent,false);
        return new hEbooksRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hEbooksRecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(data.get(position).getBookCoverUrl()!=null){
            Picasso.get().load(accessUrl.BASE_URL +  data.get(position).getBookCoverUrl())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo_dark))
                    .into(holder.coverBook);
        }
        holder.tvtitle.setText(data.get(position).getBookName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });


        if(row_index==position){

            holder.itemView.setOnClickListener(view -> eventListenerUtil.myCallBack("open-group-ebook",data.get(position).getEbookID()));

            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color._fourth));
            TextView title = holder.itemView.findViewById(R.id.tv_book_name_horizontal_books_preview);
            title.setTextColor(context.getResources().getColor(R.color.black));

            footer_book.setVisibility(View.VISIBLE);

            title_book.setText(data.get(position).getBookName());
            short_desc_book.setText(data.get(position).getBookShortDescription());
            footer_book.setOnClickListener(view -> eventListenerUtil.myCallBack("open-group-ebook",data.get(position).getEbookID()));
            footer_see_details_book.setOnClickListener(view -> eventListenerUtil.myCallBack("open-group-ebook",data.get(position).getEbookID()));

        }else{
            if(themes.isNightMode(context)){
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color._fifth));
                TextView title = holder.itemView.findViewById(R.id.tv_book_name_horizontal_books_preview);
                title.setTextColor(context.getResources().getColor(R.color.white));
            }else{
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color._second));
                TextView title = holder.itemView.findViewById(R.id.tv_book_name_horizontal_books_preview);
                title.setTextColor(context.getResources().getColor(R.color.black));
            }

        }

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
