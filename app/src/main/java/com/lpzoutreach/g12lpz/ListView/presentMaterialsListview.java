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

import com.lpzoutreach.g12lpz.Models.PresentMaterialsModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class presentMaterialsListview extends ArrayAdapter<PresentMaterialsModel> {

    private Context context;
    private List<PresentMaterialsModel> data;

    public presentMaterialsListview(@NonNull Context context, int resource, @NonNull List<PresentMaterialsModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        PresentMaterialsModel rows = data.get(position);

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

        if(!rows.getPresent_Logo().isEmpty()){
            Picasso.get().load(accessUrl.BASE_URL + rows.getPresent_Logo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getPresentTitle());
        subtitle.setText(rows.getPresentSubTitle());
        Language.setText(rows.getPresentLanguage());
        TotalChapters.setText("Total Slides: " + rows.getPresentSlides());

        if(rows.getIs_file_downloaded())
            icon.setImageResource(R.drawable.ic_icon_arrow_right);
        else
            icon.setImageResource(R.drawable.ic_icon_download);

        card.setOnClickListener(v ->{
                    //icon.setImageResource(R.drawable.ic_icon_arrow_right);
                    rows.getEventListenerUtil().myCallBack("listview-click", rows.getMaterialPresentID());
                }
        );

        return mView;
    }

}
