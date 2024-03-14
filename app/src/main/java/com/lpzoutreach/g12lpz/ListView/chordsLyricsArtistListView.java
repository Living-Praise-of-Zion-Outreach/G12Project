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

public class chordsLyricsArtistListView extends ArrayAdapter<MusicModel> {
    private Context context;
    private List<MusicModel> data;
    private int id;

    public chordsLyricsArtistListView(@NonNull Context context, int id, List<MusicModel> data ){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        MusicModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_chords_lyrics_artist,null);
        }

        @SuppressLint("CutPasteId") CardView card = mView.findViewById(R.id.cv_chords_lyrics_artist);
        @SuppressLint("CutPasteId") ImageView logo = mView.findViewById(R.id.civ_logo_chords_lyrics_artist);
        TextView title = mView.findViewById(R.id.tv_title_chords_lyrics_artist);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_chords_lyrics_artist);

        if(rows.getArtistPhoto().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(accessUrl.BASE_URL + rows.getArtistPhoto())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getArtistName());
        subTitle.setText(rows.getArtistType());

        card.setOnClickListener(v -> rows.getEventListenerUtil().myCallBack("get-artist-id", rows.getArtistID() + "~" + rows.getArtistName()));

        return mView;
    }
}
