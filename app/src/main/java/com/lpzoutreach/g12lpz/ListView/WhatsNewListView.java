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

import com.lpzoutreach.g12lpz.Models.NotifyAppUpdateModel;
import com.lpzoutreach.g12lpz.R;

import java.util.List;

public class WhatsNewListView extends ArrayAdapter<NotifyAppUpdateModel> {

    private Context context;
    private List<NotifyAppUpdateModel> data;

    public WhatsNewListView(@NonNull Context context, int resource, @NonNull List<NotifyAppUpdateModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        NotifyAppUpdateModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_whats_new,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_search_person);
        TextView version = mView.findViewById(R.id.tv_version_whats_new);

        TextView content = mView.findViewById(R.id.tv_content_whats_new);
        TextView releasedDate = mView.findViewById(R.id.tv_released_date_whats_new);

        version.setText(rows.getAppTitle());
        content.setText(rows.getAppDescription());
        releasedDate.setText(rows.getDtCreated());

        return mView;
    }
}
