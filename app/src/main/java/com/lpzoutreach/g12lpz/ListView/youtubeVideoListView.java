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
import com.squareup.picasso.Picasso;

import java.util.List;

public class youtubeVideoListView extends ArrayAdapter<MusicModel> {

    private Context context;
    private List<MusicModel> data;

    public youtubeVideoListView(@NonNull Context context, int resource, @NonNull List<MusicModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        MusicModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_youtube_video_list,null);
        }

        CardView card = mView.findViewById(R.id.cv_youtube_video_list);
        ImageView logo = mView.findViewById(R.id.iv_logo_youtube_video_list);

        Picasso.get().load(rows.getVideo_youtubeThumbnails())
                .placeholder(context.getDrawable(R.drawable.default_photo))
                .error(context.getDrawable(R.drawable.default_photo))
                .into(logo);

        TextView title = mView.findViewById(R.id.tv_title_youtube_video_list);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_chords_lyrics);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rows.getEventListenerUtil().myCallBack(rows.getActionEvent(),rows.getVideo_youtubeID());
            }
        });

        title.setText(rows.getVideo_youtubeTitle());
        subtitle.setText(rows.getVideo_youtubeChannel());

        return mView;
    }
}
