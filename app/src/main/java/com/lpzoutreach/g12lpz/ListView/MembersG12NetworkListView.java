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

import com.lpzoutreach.g12lpz.Models.MembersG12NetworkModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MembersG12NetworkListView extends ArrayAdapter<MembersG12NetworkModel> {

    private Context context;
    private List<MembersG12NetworkModel> data;
    private int id;
    private SparseBooleanArray mSelectedItemsIds;

    public MembersG12NetworkListView(@NonNull Context context, int resource, @NonNull List<MembersG12NetworkModel> objects) {
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

        MembersG12NetworkModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_members_network,null);
        }

        CardView card = mView.findViewById(R.id.item_card_view_network);
        ImageView logo = mView.findViewById(R.id.iv_logo_network_list_view);
        TextView name = mView.findViewById(R.id.tv_member_name_network);
        TextView org_name = mView.findViewById(R.id.tv_organization_name_network_list_view);
        TextView count_primary_leaders = mView.findViewById(R.id.tv_count_primary_leaders_network);
        TextView tv_count_oneff_network = mView.findViewById(R.id.tv_count_oneff_network);
        TextView tv_count_many_network = mView.findViewById(R.id.tv_count_many_network);

        String img_path=accessUrl.BASE_URL + "";

        String[] logo_checker = rows.getMember_logo().split("/");
        if(logo_checker[0].equals("assets")){
            if(!rows.getMember_logo().isEmpty()){
                img_path = accessUrl.BASE_URL + rows.getMember_logo();
                Picasso.get().load(accessUrl.BASE_URL + rows.getMember_logo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                img_path = accessUrl.BASE_URL + "assets/images/default-photo_square.png";
                logo.setImageResource(R.drawable.default_photo);
            }
        }else{
            if(!rows.getMember_logo().isEmpty()){
                img_path = rows.getMember_logo();
                Picasso.get().load(rows.getMember_logo())
                        .placeholder(context.getDrawable(R.drawable.default_photo))
                        .error(context.getDrawable(R.drawable.default_photo))
                        .into(logo);
            }else{
                img_path = accessUrl.BASE_URL + "assets/images/default-photo_square.png";
                logo.setImageResource(R.drawable.default_photo);
            }
        }

        name.setText(rows.getFirst_name() + " " + rows.getLast_name());
        if(rows.getOrganization_name().equals("null")){
            org_name.setText("- No Network Name -");
        }else{
            org_name.setText(rows.getOrganization_name());
        }

        count_primary_leaders.setText(String.valueOf(rows.getPrimary_leaders()));
        tv_count_oneff_network.setText(String.valueOf(rows.get_144()));
        tv_count_many_network.setText(String.valueOf(rows.get_1789()));

        final String path = img_path;
        card.setOnClickListener(view -> rows.getEventListenerUtil().myCallBack(rows.getActionType(),
                rows.getUserID() + "~" + name.getText().toString() + "~" + path));

        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack(rows.getActionType(),
                    rows.getUserID() + "~" + name.getText().toString() + "~" + path);
            return false;
        });


        return mView;
    }

}
