package com.lpzoutreach.g12lpz.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import com.lpzoutreach.g12lpz.Models.ChristianAlbumModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ChristianAlbumRecyclerView extends RecyclerView.Adapter<ChristianAlbumRecyclerView.ViewHolder>{
    private ArrayList<ChristianAlbumModel> albumList;
    private Context context;

    public ChristianAlbumRecyclerView(ArrayList<ChristianAlbumModel> albumList, Context context){
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChristianAlbumRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_christian_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChristianAlbumRecyclerView.ViewHolder holder, int position) {
        holder.setData(albumList.get(position).getUrl_logo(),albumList.get(position).getAlbum_name(), albumList.get(position).getForeign_artist_name(),albumList.get(position).getYear_published());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView album_name,artist_name,year_published;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_image_recycler_view_christian_album);
            album_name = itemView.findViewById(R.id.tv_title_recycler_view_christian_album);
            artist_name = itemView.findViewById(R.id.tv_subtitle_recycler_view_christian_album);
            year_published = itemView.findViewById(R.id.tv_year_published_recycler_view_christian_album);
        }

        public void setData(String urlLogo, String title,String subtitle, String subsubtitle){
            if(urlLogo!=null){
                Picasso.get().load(urlLogo)
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo_dark))
                        .into(iv);
            }

            album_name.setText(title);
            artist_name.setText(subtitle);
            if(!subsubtitle.toString().trim().equals("0")){
                year_published.setText(context.getResources().getString(R.string.year_published_christian_album) + " " + subsubtitle);
            }else{
                year_published.setText(context.getResources().getString(R.string.year_published_christian_album)  + "?");
            }

        }
    }
}
