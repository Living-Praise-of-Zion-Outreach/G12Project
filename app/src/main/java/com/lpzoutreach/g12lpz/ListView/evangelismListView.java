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
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.string.utilString;
import com.squareup.picasso.Picasso;

import java.util.List;

public class evangelismListView extends ArrayAdapter<EvangelismModel> {
    private Context context;
    private List<EvangelismModel> data;
    private int id;
    private SparseBooleanArray mSelectedItemsIds;

    public evangelismListView(@NonNull Context context, int resource, @NonNull List<EvangelismModel> objects) {
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

        EvangelismModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_evangelize,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_evangelize);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_evangelize);
        TextView title = mView.findViewById(R.id.tv_title_list_evangelize);
        TextView code = mView.findViewById(R.id.tv_code_list_evangelize);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_list_evangelize);
        TextView subsubTitle = mView.findViewById(R.id.tv_subsubtitle_evangelize);

        if(!rows.getProfilePhotoPath().isEmpty()){
            Picasso.get().load(rows.getProfilePhotoPath())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }else if(!rows.getAndroidPhotoPath().isEmpty()) {
            logo.setImageResource(R.drawable.default_photo);
            Picasso.get().load(rows.getAndroidPhotoPath())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }else{
            logo.setImageResource(R.drawable.default_photo);
        }


        title.setText(rows.getFirst_name() + " " + rows.getMiddle_name() + " " + rows.getLast_name());
        code.setText("");

        if(rows.getCon_mobile_no().trim().equals("") && rows.getEmail().trim().equals(""))
            subTitle.setText("?");
        else
            subTitle.setText(rows.getCon_mobile_no() + " " + rows.getEmail());

        if(rows.getLoc_address().trim().equals("") && rows.getLoc_barangay().trim().equals("") && rows.getLoc_city().trim().equals("") && rows.getLoc_province().trim().equals("") && rows.getLoc_country().trim().equals(""))
            subsubTitle.setText("?");
        else {
            String address = "";
            address = utilString.setConcat(address,rows.getLoc_address(),",");
            address = utilString.setConcat(address,rows.getLoc_barangay(),",");
            address = utilString.setConcat(address,rows.getLoc_city(),",");
            address = utilString.setConcat(address,rows.getLoc_province(),",");
            address = utilString.setConcat(address,rows.getLoc_country(),",");
            subsubTitle.setText(address);
        }

        card.setOnClickListener(v ->
                rows.getEventListenerUtil().myCallBack("lv-click", rows.getTempProfileID())
        );

        card.setOnLongClickListener(view -> {
            rows.getEventListenerUtil().myCallBack("lv-delete", rows.getTempProfileID());
            return false;
        });


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
