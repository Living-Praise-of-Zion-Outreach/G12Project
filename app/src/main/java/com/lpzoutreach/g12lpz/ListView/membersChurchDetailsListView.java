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

import com.lpzoutreach.g12lpz.Models.ChurchMembersModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class membersChurchDetailsListView extends ArrayAdapter<ChurchMembersModel> {
    private Context context;
    private List<ChurchMembersModel> data;
    private int id;
    private SparseBooleanArray mSelectedItemsIds;

    public membersChurchDetailsListView(@NonNull Context context, int resource, @NonNull List<ChurchMembersModel> objects) {
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

        ChurchMembersModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_members_church_details,null);
        }

        CardView card = mView.findViewById(R.id.item_card_view_church_details);
        ImageView logo = mView.findViewById(R.id.iv_logo_list_view_members_church_details);
        TextView title = mView.findViewById(R.id.tv_name_list_view_church_details);
        TextView subtitle = mView.findViewById(R.id.tv_position_list_view_members_church_details);

        String[] logo_checker = rows.getProfile_photo_path().split("/");
        if(logo_checker[0].equals("assets")){
            if(!rows.getProfile_photo_path().isEmpty()){
                Picasso.get().load(accessUrl.BASE_URL + rows.getProfile_photo_path())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                logo.setImageResource(R.drawable.default_photo);
            }
        }else{
            if(!rows.getProfile_photo_path().isEmpty()){
                Picasso.get().load(rows.getProfile_photo_path())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                logo.setImageResource(R.drawable.default_photo);
            }
        }

        title.setText(rows.getFirst_name() + " " + rows.getMiddle_name() + " " +  rows.getLast_name());
        subtitle.setText(rows.getChurch_position());

        card.setOnClickListener(view -> rows.getEventListenerUtil().myCallBack("membersChurchDetails",
                rows.getUserID() + "~" + rows.getChurch_position()));

        return mView;
    }

}
