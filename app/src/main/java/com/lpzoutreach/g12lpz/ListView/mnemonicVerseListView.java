package com.lpzoutreach.g12lpz.ListView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.lpzoutreach.g12lpz.Models.MnemonicVerseModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.object.dynamicObjects;

import java.util.List;

public class mnemonicVerseListView extends ArrayAdapter<MnemonicVerseModel> {

    private Context context;
    private List<MnemonicVerseModel> data;

    public mnemonicVerseListView(@NonNull Context context, int resource, @NonNull List<MnemonicVerseModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        MnemonicVerseModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_mnemonic_verse,null);
        }

        CardView card = mView.findViewById(R.id.item_cv_list_mnemonic_verse);
        TextView verses = mView.findViewById(R.id.tv_verses_mnemonic_verse);
        TextView gospel = mView.findViewById(R.id.tv_gospel_reference_mnemonic_verse);
        LinearLayout tag = mView.findViewById(R.id.ll_tag_mnemonic_verse);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            verses.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            verses.setText(Html.fromHtml(rows.getVerse(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            verses.setText(Html.fromHtml(rows.getVerse()));
        }



        gospel.setText(rows.getVerseReference() + " (" + rows.getBibleVersion() + ")");

        String[] tags = rows.getTag().split(",");

        tag.removeAllViews();

        for(int i=0;i<tags.length;i++){
            if(i==0)
                dynamicObjects.create_tag_label(context,tag,tags[i],0,rows.getEventListenerUtil());
            else
                dynamicObjects.create_tag_label(context,tag,tags[i],10,rows.getEventListenerUtil());
        }


        return mView;
    }

}
