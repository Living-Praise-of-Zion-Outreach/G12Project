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
import com.lpzoutreach.g12lpz.Models.ChristianArtistModel;
import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.R;
import java.util.ArrayList;

public class SearchArtistPickerDialog extends AppCompatDialogFragment {
    private ChristianArtistModel passData;
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

    public SearchArtistPickerDialog(Context context, ChristianArtistModel passData){
        this.context=context;
        this.passData=passData;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_search_artist_picker, null);

        builder.setView(view).setCancelable(false);

        svSearch = view.findViewById(R.id.sv_search_artist_picker);
        listviews = view.findViewById(R.id.lv_search_artist_picker);
        cl_no_data = view.findViewById(R.id.cl_no_data_found_search_artist_picker);


        eventListenerUtil.addEventListener("searchArtistPickerListView.java?Christian-Artist", new elHandler() {
            @Override
            public void callback(elEvent event) {
                passData.getEventListenerUtil().myCallBack("SearchArtistPickerDialog.java?Christian-Artist",event.getData().toString());
                getDialog().dismiss();
            }
        });

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadChristianArtistData(query.toString());
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
                loadChristianArtistData(svSearch.getQuery().toString());
            }
        });




        loadChristianArtistData("");

        return builder.create();

    }


    private ArrayList<ChristianArtistModel> loadChristianArtistData(String keyword){
       /*

        ArrayList<ChristianArtistModel> cdata = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("christian-artist")
                .orderBy("name_of_artist", Query.Direction.ASCENDING)
                .limit(20)
                .startAt(keyword)
                .endAt(keyword + "\uf8ff")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot document: queryDocumentSnapshots)
                        {
                                ChristianArtistModel row = new ChristianArtistModel();
                                row.setArtist_id(document.getString("artist_id"));
                                row.setUrl_logo(document.getString("url_logo"));
                                row.setName_of_artist(document.getString("name_of_artist"));
                                row.setEventListenerUtil(eventListenerUtil);
                                cdata.add(row);
                        }

                        if(cdata.size()>0) {

                            arrayAdapter = new searchChristianArtistListView(getContext(), R.layout.list_view_christian_artist, cdata);
                            listviews.setAdapter(arrayAdapter);
                            listviews.setClickable(true);
                            listviews.setDivider(null);
                            cl_no_data.setVisibility(View.INVISIBLE);
                            listviews.setVisibility(View.VISIBLE);
                        }
                        else {
                            arrayAdapter = new searchChristianArtistListView(getContext(), R.layout.list_view_search_picker, cdata);
                            listviews.setAdapter(arrayAdapter);
                            listviews.setClickable(true);
                            listviews.setDivider(null);
                            cl_no_data.setVisibility(View.VISIBLE);
                            listviews.setVisibility(View.INVISIBLE);
                        }

                    }
                });


        return cdata;*/
        return null;
    }

}
