package com.lpzoutreach.g12lpz.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.widget.ImageView;
import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class churchListView extends ArrayAdapter<ChurchModel> {
    private Context context;
    private List<ChurchModel> data;
    private int id;

    public churchListView(@NonNull Context context, int id,  List<ChurchModel> data ){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        ChurchModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.list_view_church,null);
        }

        CardView card = mView.findViewById(R.id.item_card_view_list_church);
        ImageView logo = mView.findViewById(R.id.civ_logo_list_view);
        TextView title = mView.findViewById(R.id.tv_title_church_list_view);
        TextView code = mView.findViewById(R.id.tv_code_church_list_view);
        TextView subTitle = mView.findViewById(R.id.tv_subtitle_church_list_view);

        if(rows.getUrl_logo().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(rows.getUrl_logo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    //.networkPolicy(NetworkPolicy.NO_CACHE)
                    //.memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(logo);
        }

        title.setText(rows.getChurch_name());
        code.setText("ID: " + rows.getChurch_code());

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
                rows.getEventListenerUtil().myCallBack("list-view", rows.getChurch_code().toString());
            }
        });

        return mView;
    }
}
