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

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.PersonalInformationModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class searchNameListView extends ArrayAdapter<PersonalInformationModel> {

    private elUtil eventListenerUtil;
    private Context context;
    private List<PersonalInformationModel> data;
    private int id;
    private SparseBooleanArray mSelectedItemsIds;

    public searchNameListView(@NonNull Context context, int resource, @NonNull List<PersonalInformationModel> objects) {
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
            mView = layoutInflater.inflate(R.layout.lv_search_name,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_search_name);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_search_name);
        TextView title = mView.findViewById(R.id.tv_title_list_search_name);
        TextView subtitle = mView.findViewById(R.id.tv_subtitle_search_name);

        if(rows.getProfile_photo().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(rows.getProfile_photo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getFirst_name() + " " + rows.getMiddle_name() + " " + rows.getLast_name());
        if(!rows.getChu_church_name().equals("null") && !rows.getNetwork_name().equals("null") ){
            subtitle.setText(rows.getChu_church_name() + " | " + rows.getNetwork_name());}
        else if (!rows.getChu_church_name().equals("null"))    {
                subtitle.setText(rows.getChu_church_name());
            }
        else {
            subtitle.setText("");
        }


        card.setOnClickListener(v ->
                rows.getHandler().myCallBack("network-leader",
                        String.valueOf(rows.getProfileID()) +"~" + rows.getProfile_photo() + "~"
                                + rows.getFirst_name() + " " + rows.getMiddle_name() + " " + rows.getLast_name() + "~"
                                + rows.getChu_church_name() + "~" + rows.getNetwork_name())
        );

        return mView;
    }


    public long getItemId(int i){
        return i;
    }

    public void removeItem(int position){
        data.remove(position);
    }

    public  void removeSelection(){
        mSelectedItemsIds = new SparseBooleanArray();
    }

    public void toggleSelection(int position){
        selectView(position,!mSelectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value){
        if(value){
            mSelectedItemsIds.put(position,value);
        }else{
            mSelectedItemsIds.delete(position);
        }
    }

    public SparseBooleanArray getSelectedIds(){
        return mSelectedItemsIds;
    }

}
