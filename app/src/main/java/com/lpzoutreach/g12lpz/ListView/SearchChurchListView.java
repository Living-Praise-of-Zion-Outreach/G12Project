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

import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.string.utilFormatter;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchChurchListView extends ArrayAdapter<ChurchModel> {

    private Context context;
    private List<ChurchModel> data;
    private int id;
    private SparseBooleanArray mSelectedItemsIds;

    public SearchChurchListView(@NonNull Context context, int resource, @NonNull List<ChurchModel> objects) {
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

        ChurchModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_search_church,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_search_church);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_search_church);
        TextView title = mView.findViewById(R.id.tv_title_list_search_church);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_search_church);
        TextView totalMembers = mView.findViewById(R.id.tv_total_members_search_church);

        String img_path="";
        String[] logo_checker = rows.getUrl_logo().split("/");
        if(logo_checker[0].equals("assets")){
            if(!rows.getUrl_logo().isEmpty()){
                img_path = accessUrl.BASE_URL + rows.getUrl_logo();
                Picasso.get().load(accessUrl.BASE_URL + rows.getUrl_logo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                img_path = accessUrl.BASE_URL + "assets/images/default-photo_square.png";
                logo.setImageResource(R.drawable.default_photo);
            }
        }else{
            if(!rows.getUrl_logo().isEmpty()){
                img_path = rows.getUrl_logo();
                Picasso.get().load(rows.getUrl_logo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                img_path = accessUrl.BASE_URL + "assets/images/default-photo_square.png";
                logo.setImageResource(R.drawable.default_photo);
            }
        }

        title.setText(rows.getChurch_name());

        if(rows.getCountry().equals("") && rows.getZip_code().equals("") && rows.getProvince().equals("")
            && rows.getMunicipality_city().equals("") && rows.getBarangay().equals("") && rows.getAddress().equals("")
            ){
            subtitle.setText("No Address");
        }else{
            String addressBuilder="";
            if(!rows.getAddress().equals(""))
                addressBuilder = rows.getAddress();
            if(!rows.getBarangay().equals(""))
                addressBuilder += ", " + rows.getBarangay();
            if(!rows.getMunicipality_city().equals(""))
                addressBuilder += ", " + rows.getMunicipality_city();
            if(!rows.getProvince().equals(""))
                addressBuilder += ", " + rows.getProvince();
            if(!rows.getZip_code().equals(""))
                addressBuilder += ", " + rows.getZip_code();
            if(!rows.getCountry().equals(""))
                addressBuilder += ", " + rows.getCountry();
            subtitle.setText(addressBuilder);
        }

        totalMembers.setText(utilFormatter.getIntegerWithComma(rows.getTotal_members()));

        card.setOnClickListener(view -> rows.getEventListenerUtil().myCallBack("searchChurch",
                rows.getChurchID() + "~" + rows.getChurch_name()
        ));

        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack("searchChurch",
                    rows.getChurchID() + "~" + rows.getChurch_name());
            return false;
        });

        return mView;
    }
}
