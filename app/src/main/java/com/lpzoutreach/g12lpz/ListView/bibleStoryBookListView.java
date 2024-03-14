package com.lpzoutreach.g12lpz.ListView;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.lpzoutreach.g12lpz.Models.BookModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class bibleStoryBookListView extends ArrayAdapter<BookModel> {

    private Context context;
    private List<BookModel> data;
    private int id;

    public bibleStoryBookListView(@NonNull Context context, int id, List<BookModel> data ){
        super(context, id, data);
        this.context = context;
        this.data = data;
    }


    @SuppressLint({"UseCompatLoadingForDrawables", "InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mView = convertView;

        BookModel rows = data.get(position);

        if(mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.lv_bible_story_book,null);
        }

        @SuppressLint("CutPasteId") CardView card = mView.findViewById(R.id.item_cv_list_bible_story_book);
        @SuppressLint("CutPasteId") ImageView logo = mView.findViewById(R.id.iv_cover_bible_story_book);
        @SuppressLint("CutPasteId") ImageView right_icon = mView.findViewById(R.id.iv_icon_bible_story_book);
        TextView title = mView.findViewById(R.id.tv_title_bible_story_book);
        TextView subTitle = mView.findViewById(R.id.tv_description_bible_story_book);
        TextView chapter = mView.findViewById(R.id.tv_chapters_bible_story_book);

        if(rows.isIs_file_downloaded())
            right_icon.setImageResource(R.drawable.ic_icon_arrow_right);
        else
            right_icon.setImageResource(R.drawable.ic_icon_download);

        if(rows.getLessonsLogo().isEmpty()){
            logo.setImageResource(R.drawable.default_photo);
        }else{
            Picasso.get().load(accessUrl.BASE_URL + rows.getLessonsLogo())
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(logo);
        }

        title.setText(rows.getLessonTitle());
        chapter.setText("Chapter " + rows.getLessonNo());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            subTitle.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
        subTitle.setText(rows.getLessonSubtitle());

        card.setOnClickListener(v -> rows.getEventListenerUtil().myCallBack(rows.getActionEvent(), rows.getLessonsID() + "~" +  rows.getContent()));

        return mView;
    }
}
