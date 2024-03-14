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
import com.lpzoutreach.g12lpz.Models.ChristianArtistModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class searchChristianArtistListView extends ArrayAdapter<ChristianArtistModel> {
    private Context context;
    private List<ChristianArtistModel> data;
    private int id;

    public searchChristianArtistListView(Context context, int id, List<ChristianArtistModel> data){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        ChristianArtistModel rows = data.get(position);
        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.list_view_christian_artist,null);
        }

        CardView card = mView.findViewById(R.id.item_card_view_list_christian_artist);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_view_christian_artist);
        TextView title = mView.findViewById(R.id.tv_title_christian_artist_list_view);

        if(rows.getUrl_logo().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{

            Picasso.get().load(rows.getUrl_logo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getName_of_artist());

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rows.getEventListenerUtil().myCallBack("searchArtistPickerListView.java?Christian-Artist", rows.getArtist_id().toString() + "~" + rows.getName_of_artist());
            }
        });

        return mView;
    }
}
