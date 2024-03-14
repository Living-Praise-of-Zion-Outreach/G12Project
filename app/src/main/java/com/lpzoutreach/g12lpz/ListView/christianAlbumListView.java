package com.lpzoutreach.g12lpz.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.lpzoutreach.g12lpz.Models.ChristianAlbumModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class christianAlbumListView extends ArrayAdapter<ChristianAlbumModel> {
    private Context context;
    private List<ChristianAlbumModel> data;
    private int id;

    public christianAlbumListView(Context context, int id, List<ChristianAlbumModel> data){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        ChristianAlbumModel rows = data.get(position);
        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.list_view_christian_album,null);
        }

        CardView card = mView.findViewById(R.id.item_card_view_list_christian_album);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_view_christian_album);
        TextView title = mView.findViewById(R.id.tv_title_christian_album_list_view);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_christian_album_list_view);
        TextView song = mView.findViewById(R.id.tv_song_christian_album_list_view);
        TextView published = mView.findViewById(R.id.tv_published_chrisitan_album_list_view);

        if(rows.getUrl_logo().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(rows.getUrl_logo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getAlbum_name());
        subtitle.setText(rows.getForeign_artist_name());
        song.setText(context.getResources().getString(R.string.song_christian_album) + " " + rows.getSong());
        published.setText(context.getResources().getString(R.string.year_published_christian_album) + " " + rows.getYear_published());

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rows.getEventListenerUtil().myCallBack("list-view", rows.getAlbum_id().toString());
            }
        });

        card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                rows.getEventListenerUtil().myCallBack("list-view-delete", rows.getAlbum_id().toString());
                return false;
            }
        });

        return mView;
    }
}
