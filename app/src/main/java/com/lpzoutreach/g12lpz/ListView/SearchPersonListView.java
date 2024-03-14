package com.lpzoutreach.g12lpz.ListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.lpzoutreach.g12lpz.Models.PersonalInformationModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchPersonListView extends ArrayAdapter<PersonalInformationModel> {

    private Context context;
    private List<PersonalInformationModel> data;
    private int id;
    private SparseBooleanArray mSelectedItemsIds;

    public SearchPersonListView(@NonNull Context context, int resource, @NonNull List<PersonalInformationModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
        mSelectedItemsIds = new SparseBooleanArray();
    }


    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        PersonalInformationModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_search_person,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_search_person);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_search_person);
        TextView title = mView.findViewById(R.id.tv_title_list_search_person);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_search_person);
        TextView subsubtitle = mView.findViewById(R.id.tv_subsubtitle_search_person);

        String img_path="";
        String[] logo_checker = rows.getProfile_photo().split("/");
        if(logo_checker[0].equals("assets")){
            if(!rows.getProfile_photo().isEmpty()){
                img_path = accessUrl.BASE_URL + rows.getProfile_photo();
                Picasso.get().load(accessUrl.BASE_URL + rows.getProfile_photo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                img_path = accessUrl.BASE_URL + "assets/images/default-photo_square.png";
                logo.setImageResource(R.drawable.default_photo);
            }
        }else{
            if(!rows.getProfile_photo().isEmpty()){
                img_path = rows.getProfile_photo();
                Picasso.get().load(rows.getProfile_photo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                img_path = accessUrl.BASE_URL + "assets/images/default-photo_square.png";
                logo.setImageResource(R.drawable.default_photo);
            }
        }

        title.setText(rows.getFirst_name() + " " +  rows.getMiddle_name() + " " + rows.getLast_name());

        if(rows.getFirst_name().equals("") && rows.getMiddle_name().equals("") && rows.getLast_name().equals("")){
            title.setText("No Name");
        }

        if(rows.getChu_church_name().equals("null")){
            subtitle.setText("none");
        }else{
            subtitle.setText(rows.getChu_church_name());
        }

        subsubtitle.setText(rows.getEmail());


        final String path = img_path;
        card.setOnClickListener(view -> rows.getEventListenerUtil().myCallBack("searchPerson",
                rows.getUserID()
                ));

        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack("searchPerson",
                    rows.getUserID());
            return false;
        });


        return mView;
    }

}
