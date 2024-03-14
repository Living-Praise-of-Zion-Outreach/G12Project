package com.lpzoutreach.g12lpz.ListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
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
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.themes.themes;

import java.util.List;

public class holyBibleBookSelectorListView extends ArrayAdapter<HolyBibleModel> {

    private Context context;
    private List<HolyBibleModel> data;

    private int row_index;

    public holyBibleBookSelectorListView(@NonNull Context context, int resource, @NonNull List<HolyBibleModel> objects) {
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
            mView = layoutInflater.inflate(R.layout.lv_holy_bible_book_selector,null);
        }


        CardView card = mView.findViewById(R.id.item_cv_list_holy_bible_book_selector);
        TextView title = mView.findViewById(R.id.tv_title_holy_bible_book_selector);

        title.setText(rows.getLong_name());

        if(sharedData.get_bible_current_row_index_book(context) == position){
            title.setTypeface(null, Typeface.BOLD);
            if(themes.isNightMode(context)){
                title.setTextColor(context.getColor(R.color._light_seven));
            }else{
                title.setTextColor(context.getColor(R.color._light_first));
            }
        }




        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack(rows.getActionEvent(), rows.getBook_number());
            return false;
        });

        return mView;
    }
}
