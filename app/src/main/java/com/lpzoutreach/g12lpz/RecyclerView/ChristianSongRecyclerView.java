package com.lpzoutreach.g12lpz.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import com.lpzoutreach.g12lpz.Models.ChristianArtistModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ChristianSongRecyclerView extends RecyclerView.Adapter<ChristianSongRecyclerView.ViewHolder> {
    private ArrayList<ChristianArtistModel> artistList;
    private Context context;

    public ChristianSongRecyclerView(ArrayList<ChristianArtistModel> artistList, Context context){
        this.artistList = artistList;
        this.context = context;
    }


    @NonNull
    @Override
    public ChristianSongRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_christian_artist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChristianSongRecyclerView.ViewHolder holder, int position) {
        holder.setData(artistList.get(position).getUrl_logo(),artistList.get(position).getName_of_artist());
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv;
        public TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_image_recycler_view_christian_artist);
            tv = itemView.findViewById(R.id.tv_subtitle_recycler_view_christian_artist);
        }

        public void setData(String urlLogo, String title){
            if(urlLogo!=null){
                Picasso.get().load(urlLogo)
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo_dark))
                        .into(iv);
            }

            tv.setText(title);
        }
    }
}
