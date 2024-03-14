package com.lpzoutreach.g12lpz.ListView;

import android.annotation.SuppressLint;
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

import com.lpzoutreach.g12lpz.Models.MusicModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class chordsLyricsListView extends ArrayAdapter<MusicModel> {

    private Context context;
    private List<MusicModel> data;
    private int id;

    public chordsLyricsListView(@NonNull Context context, int id, List<MusicModel> data ){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        MusicModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_chords_lyrics,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_chords_lyrics);
        ImageView logo = mView.findViewById(R.id.civ_logo_chords_lyrics);
        TextView title = mView.findViewById(R.id.tv_title_chords_lyrics);
        TextView code = mView.findViewById(R.id.tv_code_chords_lyrics);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_chords_lyrics);

        TextView views = mView.findViewById(R.id.tv_views_chords_lyrics);
        TextView downloads = mView.findViewById(R.id.tv_downloads_chords_lyrics);
        TextView favorites = mView.findViewById(R.id.tv_favorite_chords_lyrics);


        if(rows.getAlbumPhoto().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(accessUrl.BASE_URL + rows.getAlbumPhoto())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    //.networkPolicy(NetworkPolicy.NO_CACHE)
                    //.memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(logo);
        }

        title.setText(rows.getSongTitle());
        subTitle.setText(rows.getArtistName());
        views.setText(rows.getViews());
        downloads.setText(rows.getDownload());
        favorites.setText(rows.getFavorite());

        card.setOnClickListener(v -> rows.getEventListenerUtil().myCallBack("get-song-id", rows.getSongID() + "~" + rows.getSongTitle() + "~" + rows.getArtistName()));

        return mView;
    }
}
