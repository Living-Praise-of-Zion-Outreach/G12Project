package com.lpzoutreach.g12lpz.RecyclerView;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import java.util.ArrayList;

public class HolyBibleHomeRecyclerView extends RecyclerView.Adapter<HolyBibleHomeRecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HolyBibleModel> data;

    public HolyBibleHomeRecyclerView(Context context, ArrayList<HolyBibleModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HolyBibleHomeRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_holy_bible_home,parent,false);
        return new HolyBibleHomeRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolyBibleHomeRecyclerView.ViewHolder holder, int position) {

        if(data.get(position).getVerse()<=9)
            holder.verse_no.setText("   " + data.get(position).getVerse());
        else if(data.get(position).getVerse()<=99)
            holder.verse_no.setText(" " + data.get(position).getVerse());
        else if(data.get(position).getVerse()<=999)
            holder.verse_no.setText(data.get(position).getVerse());

        if(data.get(position).getText() == null){
            holder.verse_text.setText("");
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.verse_text.setText(Html.fromHtml(data.get(position).getText(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.verse_text.setText(Html.fromHtml(data.get(position).getText()));
            }
        }



    }

    @Override
    public int getItemCount() {return data.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView verse_no;
        TextView verse_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            verse_no = itemView.findViewById(R.id.tv_verse_no_rv_holy_bible);
            verse_text = itemView.findViewById(R.id.tv_verse_text_rv_holy_bible);
        }
    }
}
