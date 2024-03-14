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

import com.lpzoutreach.g12lpz.Models.DiscipleLessonsModel;
import com.lpzoutreach.g12lpz.R;

import java.util.List;

public class lifeClassLessonsListView extends ArrayAdapter<DiscipleLessonsModel> {

    private Context context;
    private List<DiscipleLessonsModel> data;

    public lifeClassLessonsListView(@NonNull Context context, int resource, @NonNull List<DiscipleLessonsModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        DiscipleLessonsModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_life_class_lessons,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_lessons);
        TextView number = mView.findViewById(R.id.tv_number_lessons);

        TextView title = mView.findViewById(R.id.tv_title_lessons);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_lessons);

        number.setText(String.valueOf(rows.getLessonNo()));
        title.setText(rows.getLessonTitle());
        subtitle.setText(rows.getMenu_title());

        card.setOnClickListener(v -> {
            rows.getEventListenerUtil().myCallBack("preview-lessons-click",
                    rows.getLessonNo() + "~" + rows.getLessonTitle() + " | " + rows.getMenu_title() + "~normal~dark~" + rows.getLessonID());
                    //Toast.makeText(context,rows.getLesson_id(),Toast.LENGTH_LONG).show();
        }
        );

        return mView;
    }

}
