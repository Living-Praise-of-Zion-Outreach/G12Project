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

import com.lpzoutreach.g12lpz.Models.SlidesModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class slidesParentListView extends ArrayAdapter<SlidesModel> {
    private Context context;
    private List<SlidesModel> data;

    public slidesParentListView(@NonNull Context context, int resource, @NonNull List<SlidesModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        SlidesModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_learning_materials,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_learning_materials);
        ImageView logo = mView.findViewById(R.id.civ_logo_learning_materials);
        TextView title = mView.findViewById(R.id.tv_title_list_learning_materials);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_learning_materials);
        TextView TotalChapters = mView.findViewById(R.id.tv_total_chapters_learning_materials);
        TextView Language = mView.findViewById(R.id.tv_language_learning_materials);
        ImageView icon = mView.findViewById(R.id.iv_icon_arrow_list_learning_materials);

        if(!rows.getPpt_logo().isEmpty()){
            Picasso.get().load(accessUrl.BASE_URL + rows.getPpt_logo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getPpt_name());
        subtitle.setText(rows.getPpt_description());
        Language.setText(rows.getPpt_language());
        TotalChapters.setText("Total Lessons: " + String.valueOf(rows.getPpt_total_lessons()));


        icon.setImageResource(R.drawable.ic_icon_arrow_right);

        card.setOnClickListener(v ->{
                    rows.getEventListenerUtil().myCallBack(rows.getActionEvent(), rows.getPptID() + "~" + rows.getPpt_name());
                }
        );

        return mView;
    }
}
