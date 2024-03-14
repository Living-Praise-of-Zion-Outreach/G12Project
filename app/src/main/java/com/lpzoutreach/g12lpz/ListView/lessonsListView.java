package com.lpzoutreach.g12lpz.ListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.lpzoutreach.g12lpz.Models.LessonsModel;
import com.lpzoutreach.g12lpz.R;

import java.util.List;

public class lessonsListView extends ArrayAdapter<LessonsModel> {


    private Context context;
    private List<LessonsModel> data;

    public lessonsListView(@NonNull Context context, int resource, @NonNull List<LessonsModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        LessonsModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_lessons,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_lessons);
        TextView number = mView.findViewById(R.id.tv_number_lessons);

        TextView title = mView.findViewById(R.id.tv_title_lessons);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_lessons);

        number.setText(rows.getLesson_number());
        title.setText(rows.getLesson_name());
        subtitle.setText(rows.getLesson_subname());

        card.setOnClickListener(v ->
                rows.getEventListenerUtil().myCallBack("preview-lessons-click",
                        rows.getLesson_number()+"~"+rows.getLesson_name() + " | " + rows.getLesson_subname() + "~"
                + rows.getAndroid_content() + "~" + rows.getAndroid_content_dark() + "~" + rows.getLesson_id())
        );

        return mView;
    }

}
