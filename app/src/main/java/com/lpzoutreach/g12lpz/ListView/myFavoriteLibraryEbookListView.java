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

import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class myFavoriteLibraryEbookListView extends ArrayAdapter<EbookModel> {

    private Context context;
    private List<EbookModel> data;

    public myFavoriteLibraryEbookListView(@NonNull Context context, int resource, @NonNull List<EbookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        EbookModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_my_favorite_ebook_library,null);
        }

        CardView card = mView.findViewById(R.id.cv_content_my_favorite_ebook_library);
        ImageView logo = mView.findViewById(R.id.iv_cover_my_favorite_ebook_library);
        TextView title = mView.findViewById(R.id.tv_title_my_favorite_ebook_library);
        @SuppressLint("CutPasteId") TextView shortDes = mView.findViewById(R.id.tv_short_description_my_favorite_ebook_library);
        @SuppressLint("CutPasteId") ImageView icon = mView.findViewById(R.id.iv_right_icon_my_favorite_ebook_library);

        if(rows.getBookCoverUrl().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(accessUrl.BASE_URL + rows.getBookCoverUrl())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }
        title.setText(rows.getBookName());
        shortDes.setText(rows.getBookShortDescription());

        if(rows.isContentData()){
            icon.setImageResource(R.drawable.ic_icon_arrow_right);
        }else{
            icon.setImageResource(R.drawable.ic_icon_download);
        }

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rows.getEventListener().myCallBack(rows.getActionEvent(),rows.getBookName() + "~" + rows.getAndroidPhotoPath() + "~" + rows.getEbookID());
            }
        });
        card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                rows.getEventListener().myCallBack("open-panel-my-favorite-library-ebook",rows.getEbookID());
                return false;
            }
        });

        return mView;
    }
}
