package com.lpzoutreach.g12lpz.ListView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class searchPickerListView extends ArrayAdapter<ChurchModel> implements Filterable {

    private Context context;
    private List<ChurchModel> data;
    private List<ChurchModel> filtered_data;
    private int id;
    private String typePicker;
    private String queryText="";

    public searchPickerListView(@NonNull Context context, int id, List<ChurchModel> data, String typePicker){
        super(context, id, data);
        this.context = context;
        this.data = data;
        this.filtered_data = data;
        this.typePicker = typePicker;
    }

    @Override
    public int getCount() {
        return filtered_data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        ChurchModel rows = filtered_data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.list_view_search_picker,null);
        }

        CardView card = mView.findViewById(R.id.item_card_view_search_picker);
        ImageView logo = mView.findViewById(R.id.logo_circle_image_view_search_picker);
        TextView title = mView.findViewById(R.id.tv_title_church_search_picker);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_church_search_picker);
        TextView totalMembers = mView.findViewById(R.id.tv_total_member_search_picker);
        TextView LabelMembers = mView.findViewById(R.id.tv_members_search_picker);



        if(rows.getUrl_logo().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(rows.getUrl_logo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }



        ////////////////////////////////ASSIGNING OF VALUES FROM DATABASE///////////////////////////
        title.setText(rows.getChurch_name());
        if(!rows.getBarangay().isEmpty() && !rows.getMunicipality_city().isEmpty() && !rows.getProvince().isEmpty()){
            subTitle.setText(rows.getBarangay() + ", " + rows.getMunicipality_city() + ", " + rows.getProvince());
        }
        else if(!rows.getMunicipality_city().isEmpty() && !rows.getProvince().isEmpty()){
            subTitle.setText(rows.getMunicipality_city() + ", " + rows.getProvince());
        }else {
            subTitle.setText(rows.getProvince());
        }
        /////////////////////////////////////////////////////////////////////////////////////////////

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typePicker.equals(ChurchModel.MOTHER_CHURCH_SEARCH_PICK_TYPE)){
                    LabelMembers.setText(context.getResources().getString(R.string.label_members));
                    rows.getEventListenerUtil().myCallBack("searchPickerListView.java?Mother-Church", rows.getChurch_code().toString() + "~" + rows.getChurch_name());
                }
               else if(typePicker.equals(ChurchModel.FOUNDER_CHURCH_SEARCH_PICK_TYPE)){
                    LabelMembers.setText(context.getResources().getString(R.string.label_friends));
                    rows.getEventListenerUtil().myCallBack("searchPickerListView.java?Founder-Church", rows.getChurch_code().toString() + "~" + rows.getChurch_name());
                }
            }
        });



        return mView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter =new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        if(charSequence!=null && !charSequence.equals("")){
            queryText = charSequence.toString();
            List<ChurchModel> newData = new ArrayList<>();
            for(int i=0;i<data.size();i++){
                if(data.get(i).getChurch_name().toLowerCase().contains(charSequence.toString().toLowerCase()))
                {
                    newData.add(data.get(i));
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.count = newData.size();
            filterResults.values = newData;
            return filterResults;
        }else if(charSequence.equals("")){
            FilterResults filterResults = new FilterResults();
            filterResults.count = data.size();
            filterResults.values = data;
            return filterResults;
        }

        FilterResults filterResults = new FilterResults();
        filterResults.count = filtered_data.size();
        filterResults.values = filtered_data;

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        filtered_data = (List<ChurchModel>) results.values;
        notifyDataSetChanged();
    }
};


}
