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

public class horizontalBooksPreviewListView extends ArrayAdapter<EbookModel> {

    private Context context;
    private List<EbookModel> data;

    public horizontalBooksPreviewListView(@NonNull Context context, int resource, @NonNull List<EbookModel> objects) {
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
            mView = layoutInflater.inflate(R.layout.lv_horizonal_books_preview,null);
        }

        CardView card = mView.findViewById(R.id.cv_horizontal_books_preview);
        ImageView logo = mView.findViewById(R.id.tv_book_preview_horizonal_books_preview);
        TextView title = mView.findViewById(R.id.tv_book_name_horizontal_books_preview);


        if(!rows.getBookCoverUrl().isEmpty()){
            Picasso.get().load(accessUrl.BASE_URL + rows.getBookCoverUrl())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getBookName());

        card.setOnClickListener(v ->{
                    //icon.setImageResource(R.drawable.ic_icon_arrow_right);
                    rows.getEventListener().myCallBack(rows.getActionEvent(), rows.getEbookID());
                }
        );

        return mView;
    }
}
