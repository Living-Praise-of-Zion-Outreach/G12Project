package com.lpzoutreach.g12lpz.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.ChristianAlbumModel;
import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.R;

import java.util.ArrayList;

public class SearchAlbumPickerDialog extends AppCompatDialogFragment {
    private ChristianAlbumModel passData;
    private elUtil eventListenerUtil = elUtil.getInstance();
    private TextInputLayout tilSearch;
    private SearchView svSearch;
    private ListView listviews;
    private ConstraintLayout cl_no_data;
    private ArrayList<ChurchModel> cdata = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private View view;
    private Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String artist_id;

    public SearchAlbumPickerDialog(Context context,String artist_id, ChristianAlbumModel passData){
        this.context=context;
        this.passData=passData;
        this.artist_id = artist_id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_search_album_picker, null);

        builder.setView(view).setCancelable(false);

        svSearch = view.findViewById(R.id.sv_search_album_picker);
        listviews = view.findViewById(R.id.lv_search_album_picker);
        cl_no_data = view.findViewById(R.id.cl_no_data_found_search_album_picker);


        eventListenerUtil.addEventListener("searchAlbumPickerListView.java?Christian-Album", new elHandler() {
            @Override
            public void callback(elEvent event) {
                passData.getEventListenerUtil().myCallBack("searchAlbumPickerListView.java?Christian-Album",event.getData().toString());
                getDialog().dismiss();
            }
        });

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadChristianAlbumData(artist_id, query.toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //loadChristianArtistData(newText);
                return false;
            }
        });

        svSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadChristianAlbumData(artist_id, svSearch.getQuery().toString());
            }
        });




        loadChristianAlbumData(artist_id, "");

        return builder.create();

    }


    private ArrayList<ChristianAlbumModel> loadChristianAlbumData(String artist_id, String keyword){
       /*
        ArrayList<ChristianAlbumModel> cdata = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("christian-album")
                .whereEqualTo("foreign_artist_id",artist_id)
                //.orderBy("album_name", Query.Direction.ASCENDING)
                .limit(20)
                //.startAt(keyword)
                //.endAt(keyword + "\uf8ff")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot document: queryDocumentSnapshots)
                        {
                            ChristianAlbumModel row = new ChristianAlbumModel();
                            row.setAlbum_id(document.getString("album_id"));
                            row.setUrl_logo(document.getString("url_logo"));
                            row.setAlbum_name(document.getString("album_name"));
                            row.setEventListenerUtil(eventListenerUtil);
                            cdata.add(row);
                        }

                        if(cdata.size()>0) {

                            arrayAdapter = new searchChristianAlbumListView(getContext(), R.layout.list_view_christian_album, cdata);
                            listviews.setAdapter(arrayAdapter);
                            listviews.setClickable(true);
                            listviews.setDivider(null);
                            cl_no_data.setVisibility(View.INVISIBLE);
                            listviews.setVisibility(View.VISIBLE);
                        }
                        else {
                            arrayAdapter = new searchChristianAlbumListView(getContext(), R.layout.list_view_christian_album, cdata);
                            listviews.setAdapter(arrayAdapter);
                            listviews.setClickable(true);
                            listviews.setDivider(null);
                            cl_no_data.setVisibility(View.VISIBLE);
                            listviews.setVisibility(View.INVISIBLE);
                        }

                    }
                });

        return cdata;

        */
        return null;
    }
}
