package com.lpzoutreach.g12lpz.ListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.object.dynamicObjects;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class eBookListView extends ArrayAdapter<EbookModel> {
    private Context context;
    private List<EbookModel> data;
    private int id;

    public eBookListView(@NonNull Context context, int resource, @NonNull List<EbookModel> objects) {
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
            mView = layoutInflater.inflate(R.layout.lv_ebooks,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_ebooks);
        ImageView logo = mView.findViewById(R.id.iv_cover_book_ebooks);
        TextView bookName = mView.findViewById(R.id.tv_title_list_ebook);
        TextView views = mView.findViewById(R.id.tv_views_ebooks);
        TextView favorite = mView.findViewById(R.id.tv_favorite_ebooks);
        TextView download = mView.findViewById(R.id.tv_download_ebooks);
        TextView shortDescription = mView.findViewById(R.id.tv_short_description_list_ebooks);
        LinearLayout tag = mView.findViewById(R.id.ll_tag_ebooks);

        ConstraintLayout loading = mView.findViewById(R.id.cl_loading_list_ebooks);
        ConstraintLayout content = mView.findViewById(R.id.cl_content_ebooks);

        if(rows.isContentData()){

            loading.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);

            if(!rows.getBookCoverUrl().isEmpty()){
                Picasso.get().load(accessUrl.BASE_URL + rows.getBookCoverUrl())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else if(!rows.getAndroidPhotoPath().isEmpty()) {
                logo.setImageResource(R.drawable.default_photo);
                Picasso.get().load(rows.getAndroidPhotoPath())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                logo.setImageResource(R.drawable.default_photo);
            }

            bookName.setText(rows.getBookName());
            views.setText(rows.getViews());
            favorite.setText(rows.getFavorites());
            download.setText(rows.getDownloads());
            shortDescription.setText(rows.getBookShortDescription());

            String[] tags = rows.getBookTag().split(",");

            tag.removeAllViews();

            for(int i=0;i<tags.length;i++){
                if(i==0)
                    dynamicObjects.create_tag_label(context,tag,tags[i],0,rows.getEventListener());
                else
                    dynamicObjects.create_tag_label(context,tag,tags[i],10,rows.getEventListener());
            }

            card.setOnClickListener(v ->
                    rows.getEventListener().myCallBack(rows.getActionEvent(), rows.getEbookID())
            );

            card.setOnLongClickListener(view -> {
                rows.getEventListener().myCallBack(rows.getActionEvent(), rows.getEbookID());
                return false;
            });

        }else{
            loading.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
        }

        return mView;
    }

}
