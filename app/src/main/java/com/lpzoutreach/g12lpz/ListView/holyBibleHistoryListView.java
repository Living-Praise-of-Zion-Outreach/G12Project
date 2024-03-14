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

import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;

import java.util.List;

public class holyBibleHistoryListView extends ArrayAdapter<HolyBibleModel> {
    private Context context;
    private List<HolyBibleModel> data;

    private int row_index;

    public holyBibleHistoryListView(@NonNull Context context, int resource, @NonNull List<HolyBibleModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        HolyBibleModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_holy_bible_history,null);
        }

        CardView card = mView.findViewById(R.id.cv_holy_bible_history);
        TextView title = mView.findViewById(R.id.tv_title_holy_bible_history);

        title.setText(rows.getLong_name() + " " + rows.getChapter() + " " + rows.getBookVersion());

        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack(rows.getActionEvent(), rows.getBook_number() + "~" + rows.getChapter() + "~" + rows.getVerse() + "~" + rows.getBookVersion());
            return false;
        });
        card.setOnClickListener(view -> rows.getEventListenerUtil().myCallBack(rows.getActionEvent(), rows.getBook_number() + "~" + rows.getChapter() + "~" + rows.getVerse() + "~" + rows.getBookVersion()));

        return mView;
    }
}
