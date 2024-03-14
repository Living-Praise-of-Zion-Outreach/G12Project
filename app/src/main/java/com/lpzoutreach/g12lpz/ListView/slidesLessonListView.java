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

import com.lpzoutreach.g12lpz.Models.SlidesModel;
import com.lpzoutreach.g12lpz.R;

import java.util.List;

public class slidesLessonListView extends ArrayAdapter<SlidesModel> {

    private Context context;
    private List<SlidesModel> data;

    public slidesLessonListView(@NonNull Context context, int resource, @NonNull List<SlidesModel> objects) {
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
            mView = layoutInflater.inflate(R.layout.lv_lessons,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_lessons);
        TextView number = mView.findViewById(R.id.tv_number_lessons);

        TextView title = mView.findViewById(R.id.tv_title_lessons);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_lessons);

        number.setText(rows.getPpt_lesson_no());
        title.setText(rows.getPpt_lesson_name());
        subtitle.setText(rows.getPpt_lesson_description());

        card.setOnClickListener(v ->
            rows.getEventListenerUtil().myCallBack(rows.getActionEvent(),
               rows.getPpt_lesson_no()+"~"+rows.getPpt_lesson_name() + "~" + rows.getPpt_url() + "~" + rows.getPpt_lesson_description())
        );

        return mView;
    }
}
