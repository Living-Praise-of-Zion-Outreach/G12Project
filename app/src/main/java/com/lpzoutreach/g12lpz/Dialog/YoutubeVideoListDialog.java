package com.lpzoutreach.g12lpz.Dialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.lpzoutreach.g12lpz.ListView.youtubeVideoListView;
import com.lpzoutreach.g12lpz.Models.MusicModel;
import com.lpzoutreach.g12lpz.R;
import java.util.List;

public class YoutubeVideoListDialog extends AppCompatDialogFragment {
    private Context context;
    private List<MusicModel> data;
    private View view;

    public YoutubeVideoListDialog(Context context, List<MusicModel> data){
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_youtube_video_list, null);
        builder.setView(view).setCancelable(false);
        ListView lv = view.findViewById(R.id.lv_dialog_youtube_video_list);
        youtubeVideoListView adapter = new youtubeVideoListView(context, R.layout.lv_youtube_video_list, data);
        lv.setAdapter(adapter);
        return builder.create();
    }


}
