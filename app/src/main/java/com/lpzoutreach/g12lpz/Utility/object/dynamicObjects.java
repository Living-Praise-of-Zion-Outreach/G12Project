package com.lpzoutreach.g12lpz.Utility.object;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.squareup.picasso.Picasso;

public class dynamicObjects {
    public static void create_header(Context context, LinearLayout linearLayout, String title) {

        TextView tv_title = new TextView(context);
        tv_title.setId(View.generateViewId());
        tv_title.setText(title);
        tv_title.setPadding(0,dpToPx(10),dpToPx(10),dpToPx(10));

        if(themes.isNightMode(context)){
            tv_title.setTextColor(context.getColor(R.color._light_seven));
        }else{
            tv_title.setTextColor(context.getColor(R.color._light_first));
        }

        tv_title.setTypeface(null, Typeface.BOLD);

        ((LinearLayout) linearLayout).addView(tv_title);
    }
    public static void create_icon_title_label_no_svg(Context context, LinearLayout linearLayout, int img_source,String title, String label){

        ConstraintLayout layout = new ConstraintLayout(context);
        layout.setId(View.generateViewId());
        layout.setPadding(0,dpToPx(10),dpToPx(10),dpToPx(10));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        CardView logo_content = new CardView(context);
        logo_content.setId(View.generateViewId());
        if(themes.isNightMode(context)){
            logo_content.setCardBackgroundColor(context.getResources().getColor(R.color._light_seven));
        }else{
            logo_content.setCardBackgroundColor(context.getResources().getColor(R.color._light_first));
        }
        logo_content.setRadius(dpToPx(50));
        logo_content.setPadding(dpToPx(12),dpToPx(12),dpToPx(12),dpToPx(12));


        ConstraintLayout.LayoutParams logo_Params = new ConstraintLayout.LayoutParams(dpToPx(40), dpToPx(40));
        logo_Params.startToStart = layout.getId();
        logo_Params.bottomToBottom = layout.getId();
        logo_Params.topToTop = layout.getId();
        create_image_view_no_svg(context,logo_content,img_source);
        logo_content.setLayoutParams(logo_Params);


        TextView tv_title = new TextView(context);
        tv_title.setId(View.generateViewId());
        tv_title.setText(title);
        tv_title.setTypeface(null, Typeface.BOLD);

        ConstraintLayout.LayoutParams title_Params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_Params.endToEnd = layout.getId();
        title_Params.topToTop = layout.getId();
        title_Params.startToEnd = logo_content.getId();
        title_Params.leftMargin = dpToPx(10);
        tv_title.setLayoutParams(title_Params);

        TextView tv_label = new TextView(context);
        tv_label.setId(View.generateViewId());
        tv_label.setText(label);
        tv_label.setTextSize(11);
        tv_label.setTypeface(null, Typeface.ITALIC);

        tv_label.setTextColor(context.getColor(R.color.secondary_text));

        ConstraintLayout.LayoutParams label_Params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label_Params.startToEnd = logo_content.getId();
        label_Params.topToBottom = tv_title.getId();

        label_Params.leftMargin = dpToPx(10);
        tv_label.setLayoutParams(label_Params);

        layout.addView(logo_content);
        layout.addView(tv_title);
        layout.addView(tv_label);

        ((LinearLayout) linearLayout).addView(layout);
    }
    public static void create_icon_title_label(Context context, LinearLayout linearLayout, int img_source,String title, String label){

        ConstraintLayout layout = new ConstraintLayout(context);
        layout.setId(View.generateViewId());
        layout.setPadding(0,dpToPx(10),dpToPx(10),dpToPx(10));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        CardView logo_content = new CardView(context);
        logo_content.setId(View.generateViewId());
        if(themes.isNightMode(context)){
            logo_content.setCardBackgroundColor(context.getResources().getColor(R.color._light_seven));
        }else{
            logo_content.setCardBackgroundColor(context.getResources().getColor(R.color._light_first));
        }
        logo_content.setRadius(dpToPx(50));
        logo_content.setPadding(dpToPx(12),dpToPx(12),dpToPx(12),dpToPx(12));


        ConstraintLayout.LayoutParams logo_Params = new ConstraintLayout.LayoutParams(dpToPx(40), dpToPx(40));
        logo_Params.startToStart = layout.getId();
        logo_Params.bottomToBottom = layout.getId();
        logo_Params.topToTop = layout.getId();
        create_image_view(context,logo_content,img_source);
        logo_content.setLayoutParams(logo_Params);


        TextView tv_title = new TextView(context);
        tv_title.setId(View.generateViewId());
        tv_title.setText(title);
        tv_title.setTypeface(null, Typeface.BOLD);

        ConstraintLayout.LayoutParams title_Params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_Params.endToEnd = layout.getId();
        title_Params.topToTop = layout.getId();
        title_Params.startToEnd = logo_content.getId();
        title_Params.leftMargin = dpToPx(10);
        tv_title.setLayoutParams(title_Params);

        TextView tv_label = new TextView(context);
        tv_label.setId(View.generateViewId());
        tv_label.setText(label);
        tv_label.setTextSize(11);
        tv_label.setTypeface(null, Typeface.ITALIC);

        tv_label.setTextColor(context.getColor(R.color.secondary_text));

        ConstraintLayout.LayoutParams label_Params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label_Params.startToEnd = logo_content.getId();
        label_Params.topToBottom = tv_title.getId();

        label_Params.leftMargin = dpToPx(10);
        tv_label.setLayoutParams(label_Params);

        layout.addView(logo_content);
        layout.addView(tv_title);
        layout.addView(tv_label);

        ((LinearLayout) linearLayout).addView(layout);
    }
    public static void create_icon_title_label(Context context, LinearLayout linearLayout, String img_source,String title, String label){

        ConstraintLayout layout = new ConstraintLayout(context);
        layout.setId(View.generateViewId());
        layout.setPadding(0,dpToPx(10),dpToPx(10),dpToPx(10));
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        CardView logo_content = new CardView(context);
        logo_content.setId(View.generateViewId());
        if(themes.isNightMode(context)){
            logo_content.setCardBackgroundColor(context.getResources().getColor(R.color._light_seven));
        }else{
            logo_content.setCardBackgroundColor(context.getResources().getColor(R.color._light_first));
        }
        logo_content.setRadius(dpToPx(50));
        logo_content.setPadding(dpToPx(0),dpToPx(0),dpToPx(0),dpToPx(0));

        ConstraintLayout.LayoutParams logo_Params = new ConstraintLayout.LayoutParams(dpToPx(40), dpToPx(40));
        logo_Params.startToStart = layout.getId();
        logo_Params.bottomToBottom = layout.getId();
        logo_Params.topToTop = layout.getId();
        create_image_view_path(context,logo_content,img_source);
        logo_content.setLayoutParams(logo_Params);

        TextView tv_title = new TextView(context);
        tv_title.setId(View.generateViewId());
        tv_title.setText(title);
        tv_title.setTypeface(null, Typeface.BOLD);

        ConstraintLayout.LayoutParams title_Params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_Params.endToEnd = layout.getId();
        title_Params.topToTop = layout.getId();
        title_Params.startToEnd = logo_content.getId();
        title_Params.leftMargin = dpToPx(10);
        tv_title.setLayoutParams(title_Params);

        TextView tv_label = new TextView(context);
        tv_label.setId(View.generateViewId());
        tv_label.setText(label);
        tv_label.setTextSize(11);
        tv_label.setTypeface(null, Typeface.ITALIC);

        tv_label.setTextColor(context.getColor(R.color.secondary_text));

        ConstraintLayout.LayoutParams label_Params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label_Params.startToEnd = logo_content.getId();
        label_Params.topToBottom = tv_title.getId();

        label_Params.leftMargin = dpToPx(10);
        tv_label.setLayoutParams(label_Params);

        layout.addView(logo_content);
        layout.addView(tv_title);
        layout.addView(tv_label);

        ((LinearLayout) linearLayout).addView(layout);
    }
    public static void create_image_view_no_svg(Context context, CardView cardView, int src){
        ImageView img = new ImageView(context);
        img.setImageResource(src);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();

        //img.setColorFilter(ContextCompat.getColor(context, R.color.white));

        marginParams.setMargins(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(0));
        cardView.addView(img);
    }
    public static void create_image_view(Context context, CardView cardView, int src){
        ImageView img = new ImageView(context);
        img.setImageResource(src);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();

        img.setColorFilter(ContextCompat.getColor(context, R.color.white));

        marginParams.setMargins(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));
        cardView.addView(img);
    }
    public static void create_image_view_path(Context context, CardView cardView, String src){
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();

        if(!src.isEmpty()){
            Picasso.get().load(src)
                    .placeholder(context.getDrawable(R.drawable.default_photo))
                    .error(context.getDrawable(R.drawable.default_photo))
                    .into(img);
        }

        //img.setColorFilter(ContextCompat.getColor(context, R.color.white));

        marginParams.setMargins(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(0));
        cardView.addView(img);
    }
    public static void create_separator(Context context, LinearLayout linearLayout){
        View separator = new View(context);

        if(themes.isNightMode(context)){
            separator.setBackgroundColor(context.getColor(R.color._six));
        }else{
            separator.setBackgroundColor(context.getColor(R.color.separator_light));
        }

        ConstraintLayout layout = new ConstraintLayout(context);
        layout.setId(View.generateViewId());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        ConstraintLayout.LayoutParams layoutParams2  = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(1));

        layoutParams2.startToStart = layout.getId();
        layoutParams2.topToTop = layout.getId();
        layoutParams2.endToEnd = layout.getId();
        layoutParams2.bottomToBottom = layout.getId();
        layoutParams2.topMargin = dpToPx(16);
        layoutParams2.bottomMargin = dpToPx(16);

        separator.setLayoutParams(layoutParams2);

        layout.addView(separator);

        ((LinearLayout) linearLayout).addView(layout);
    }
    public static void create_title_label(Context context, LinearLayout linearLayout, String title, String label){

        ConstraintLayout layout = new ConstraintLayout(context);
        layout.setId(View.generateViewId());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        ConstraintSet constraintSet= new ConstraintSet();

        TextView tv_label = new TextView(context);
        tv_label.setText(label);
        tv_label.setId(View.generateViewId());
        tv_label.setTypeface(null, Typeface.BOLD);

        if(themes.isNightMode(context)){
            tv_label.setTextColor(context.getColor(R.color._light_seven));
        }else{
            tv_label.setTextColor(context.getColor(R.color._light_first));
        }


        TextView tv_title = new TextView(context);
        tv_title.setText(title);
        tv_title.setId(View.generateViewId());

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.startToStart = layout.getId();
        layoutParams.endToEnd = layout.getId();
        layoutParams.topToTop = layout.getId();

        tv_label.setLayoutParams(layoutParams);

        ConstraintLayout.LayoutParams layoutParams2  = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams2.startToStart = layout.getId();
        layoutParams2.topToBottom = tv_label.getId();
        layoutParams2.topMargin = 32;

        tv_title.setLayoutParams(layoutParams2);

        layout.addView(tv_label);
        layout.addView(tv_title);

        ((LinearLayout) linearLayout).addView(layout);

    }
    public static void create_similar_books(Context context, LinearLayout linearLayout, int ebookID, String logo, String title, elUtil evenListenerUtil, String parentID){
        CardView cv = new CardView(new ContextThemeWrapper(context, R.style.similar_books), null, 0);
        cv.setId(View.generateViewId());
        cv.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(100), LinearLayout.LayoutParams.MATCH_PARENT));
        cv.setContentPadding(dpToPx(8),dpToPx(8),dpToPx(8),dpToPx(8));
        cv.setPadding(dpToPx(8),dpToPx(8),dpToPx(8),dpToPx(8));
        cv.setFocusable(true);
        cv.setClickable(true);

        if(themes.isNightMode(context)){
            cv.setCardBackgroundColor(context.getColor(R.color._fifth));
        }else{
            cv.setCardBackgroundColor(context.getColor(R.color._second));
        }

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evenListenerUtil.myCallBack("select-similar-books",ebookID);
            }
        });

        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        cv.setForeground(context.getDrawable(outValue.resourceId));

        ConstraintLayout layout = new ConstraintLayout(context);
        layout.setId(View.generateViewId());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        cv.addView(layout);

        ImageView img = new ImageView(context);
        img.setId(View.generateViewId());
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams imglayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img.setLayoutParams(imglayoutParams);

        Picasso.get().load(accessUrl.BASE_URL +   logo)
                .placeholder(context.getDrawable(R.drawable.default_photo))
                .error(context.getDrawable(R.drawable.default_photo_dark))
                .into(img);

        ConstraintLayout.LayoutParams image_params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        image_params.startToStart = layout.getId();
        image_params.endToEnd = layout.getId();
        image_params.topToTop = layout.getId();
        image_params.bottomToBottom = layout.getId();
        img.setLayoutParams(image_params);

        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();
        //img.setColorFilter(ContextCompat.getColor(context, R.color.white));
        //marginParams.setMargins(dpToPx(0), dpToPx(20), dpToPx(0), dpToPx(40));

        layout.addView(img);

        TextView tvTitle = new TextView(context);
        tvTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setMaxLines(2);
        tvTitle.setTypeface(null, Typeface.BOLD);
        if(themes.isNightMode(context)){
            tvTitle.setBackgroundColor(context.getColor(R.color._fifth));
        }else{
            tvTitle.setBackgroundColor(context.getColor(R.color._second));
        }
        tvTitle.setEllipsize(TextUtils.TruncateAt.END);
        tvTitle.setTextSize(12);
        ConstraintLayout.LayoutParams title_params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_params.startToStart = layout.getId();
        title_params.endToEnd = layout.getId();
        title_params.bottomToBottom = layout.getId();
        tvTitle.setLayoutParams(title_params);
        layout.addView(tvTitle);


        ((LinearLayout) linearLayout).addView(cv);
    }
    public static void create_tag_label(Context context, LinearLayout linearLayout, String text, int marginLeft, elUtil eventListenerUtil){
        TextView contentTag = new TextView(context,null,0,R.style.custom_tag);
        contentTag.setText(text.trim());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(dpToPx(marginLeft), 0, 0, 0);
        contentTag.setLayoutParams(params);
        contentTag.setPadding(dpToPx(10),dpToPx(3),dpToPx(10),dpToPx(3));
        contentTag.setTextColor(context.getColor(R.color.white));
        contentTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);

        contentTag.setOnClickListener(view -> eventListenerUtil.myCallBack("tag",text));

        linearLayout.addView(contentTag);
    }
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }




}
