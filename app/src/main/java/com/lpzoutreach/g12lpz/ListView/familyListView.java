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

import com.lpzoutreach.g12lpz.Models.FamilyBackgroundModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class familyListView extends ArrayAdapter<FamilyBackgroundModel> {
    private Context context;
    private List<FamilyBackgroundModel> data;

    public familyListView(@NonNull Context context, int resource, @NonNull List<FamilyBackgroundModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        FamilyBackgroundModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_search_name,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_search_name);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_search_name);
        TextView title = mView.findViewById(R.id.tv_title_list_search_name);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_search_name);

        if(rows.getProfile_photo_path().isEmpty()){
            if(rows.getAndroid_photo_path().isEmpty()){
                logo.setImageResource(R.drawable.default_photo);
            }else{
                Picasso.get().load(rows.getAndroid_photo_path())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }
        }else{
            Picasso.get().load(rows.getProfile_photo_path())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getRel_name());
        subTitle.setText(rows.getRelationship());

        card.setOnClickListener(v ->
                rows.getEventListenerUtil().myCallBack("lv-click", rows.getFamilyBackgroundID())
        );

        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack("lv-delete", rows.getFamilyBackgroundID());
            return false;
        });

        return mView;
    }

}
