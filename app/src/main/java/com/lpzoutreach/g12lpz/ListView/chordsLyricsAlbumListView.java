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

public class chordsLyricsAlbumListView extends ArrayAdapter<MusicModel> {
    private Context context;
    private List<MusicModel> data;
    private int id;

    public chordsLyricsAlbumListView(@NonNull Context context, int id, List<MusicModel> data ){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        MusicModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_chords_lyrics_album,null);
        }

        @SuppressLint("CutPasteId") CardView card = mView.findViewById(R.id.cv_chords_lyrics);
        @SuppressLint("CutPasteId") ImageView logo = mView.findViewById(R.id.civ_logo_album_chords_lyrics);
        TextView title = mView.findViewById(R.id.tv_title_album_chords_lyrics);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_chords_lyrics);

        if(rows.getAlbumPhoto().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(accessUrl.BASE_URL + rows.getAlbumPhoto())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getAlbumName() + " " + rows.getAlbumYearReleased());
        subTitle.setText(rows.getArtistName());

        card.setOnClickListener(v -> rows.getEventListenerUtil().myCallBack(rows.getActionEvent(), rows.getAlbumID() + "~" + rows.getAlbumName() + "~" + rows.getArtistName()));

        return mView;
    }

}
