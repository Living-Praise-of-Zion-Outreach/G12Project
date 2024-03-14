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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.searchPickerListView;
import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.R;

import java.util.ArrayList;

public class SearchPickerDialog extends AppCompatDialogFragment {
    private ChurchModel passData;
    private elUtil eventListenerUtil = elUtil.getInstance();
    private MaterialButtonToggleGroup tooglebutton;
    private TextInputLayout tilSearch;
    private SearchView svSearch;
    private MaterialButton searchName;
    private MaterialButton searchCode;
    private ListView listviews;
    private ConstraintLayout cl_no_data;
    private ArrayList<ChurchModel> cdata = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private View view;
    private Context context;
    String selectedValue = "Male";
    String selectedSearchType = "Search Name";

    public SearchPickerDialog(Context context, ChurchModel passData){
        this.context=context;
        this.passData=passData;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_search_picker, null);

        builder.setView(view).setCancelable(false);

        tooglebutton = view.findViewById(R.id.toggle_button_search_picker);
        svSearch = view.findViewById(R.id.sv_search_picker);
        searchName = view.findViewById(R.id.search_name_picker_button);
        searchCode = view.findViewById(R.id.search_code_picker_button);
        listviews = view.findViewById(R.id.search_picker_list_view);
        cl_no_data = view.findViewById(R.id.cl_no_data_found_search_picker);

        searchName.setChecked(true);

        eventListenerUtil.addEventListener("searchPickerListView.java?Mother-Church", new elHandler() {
            @Override
            public void callback(elEvent event) {
                passData.getEventListenerUtil().myCallBack("SearchPickerDialog.java?Mother-Church",event.getData().toString());
                getDialog().dismiss();
            }
        });
        eventListenerUtil.addEventListener("searchPickerListView.java?Founder-Church", new elHandler() {
            @Override
            public void callback(elEvent event) {
                passData.getEventListenerUtil().myCallBack("SearchPickerDialog.java?Founder-Church",event.getData().toString());
                getDialog().dismiss();
            }
        });

        tooglebutton.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (searchName.isChecked()) {
                    //tvSearch.setHint("Search Name");
                    selectedSearchType = "Search Name";
                    svSearch.setQuery("", false);
                    svSearch.clearFocus();
                } else if (searchCode.isChecked()) {
                    //tvSearch.setHint("Search Code");
                    selectedSearchType = "Search Code";
                    svSearch.setQuery("", false);
                    svSearch.clearFocus();
                }
            }
        });

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadChurchData(svSearch.getQuery().toString(),passData.getChurch_code());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //arrayAdapter.getFilter().filter(newText);
                if(selectedSearchType.equals("Search Name")){
                   // searchChurchData(newText,passData.getChurch_code());
                }
                else if(selectedSearchType.equals("Search Code")){
                    //loadChurchData_Code(newText,passData.getChurch_code());
                }

                return false;
            }
        });

        svSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedSearchType.equals("Search Name")){
                    loadChurchData(svSearch.getQuery().toString(),passData.getChurch_code());
                }
                else if(selectedSearchType.equals("Search Code")){
                    loadChurchData_Code(svSearch.getQuery().toString(),passData.getChurch_code());
                }
            }
        });






        if(passData.getSearchPickType().equals(ChurchModel.MOTHER_CHURCH_SEARCH_PICK_TYPE)){
            cdata = loadChurchData("",passData.getChurch_code());

        }else if(passData.getSearchPickType().equals(ChurchModel.FOUNDER_CHURCH_SEARCH_PICK_TYPE)){
            for(int i=0;i<10;i++){
                ChurchModel row = new ChurchModel();
                row.setChurch_code("asdaddsa" + i);
                row.setEventListenerUtil(eventListenerUtil);
                row.setChurch_name("Person Name" + i);
                row.setTotal_members("100");
                row.setBarangay("Brgy. Maapag");
                row.setMunicipality_city("Valencia City");
                row.setProvince("Bukidnon");
                cdata.add(row);
            }
        }

        return builder.create();

    }


    private void searchChurchData(String keyword, String notIncludedChurchName){
        ArrayList<ChurchModel> temp_data = new ArrayList<>();
        for(int i=0;i<cdata.size();i++){
            if(cdata.get(i).getChurch_name().equals(notIncludedChurchName)){
            }else{
                if(cdata.get(i).getChurch_name().contains(keyword))
                {
                    temp_data.add(cdata.get(i));
                }
            }
        }

        if(temp_data.size()>0) {
            arrayAdapter = new searchPickerListView(getContext(), R.layout.list_view_search_picker, temp_data, passData.getSearchPickType());
            listviews.setAdapter(arrayAdapter);
            listviews.setClickable(true);
            listviews.setDivider(null);
            cl_no_data.setVisibility(View.INVISIBLE);
            listviews.setVisibility(View.VISIBLE);
        }
        else {
            arrayAdapter = new searchPickerListView(getContext(), R.layout.list_view_search_picker, temp_data, passData.getSearchPickType());
            listviews.setAdapter(arrayAdapter);
            listviews.setClickable(true);
            listviews.setDivider(null);
            cl_no_data.setVisibility(View.VISIBLE);
            listviews.setVisibility(View.INVISIBLE);
        }

    }

    private ArrayList<ChurchModel> loadChurchData_Code(String code, String notIncludedChurchName){
        /*
        ArrayList<ChurchModel> cdata = new ArrayList<>();
        //FirebaseAuth auth = FirebaseAuth.getInstance();
        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("churches")
                .whereEqualTo("code_id",code)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot document: queryDocumentSnapshots)
                        {
                            if(document.getString("code_id").equals(notIncludedChurchName)) {} //remove if the same record
                            else if(document.getString("filter_mode").equals("PRIVATE")) {} //Remove if the data is private
                            else{
                                ChurchModel row = new ChurchModel();
                                row.setNotify_Empty(cl_no_data);
                                row.setSearchPickerListView(listviews);
                                row.setUrl_logo(document.getString("url_logo"));
                                row.setChurch_code(document.getString("code_id"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setChurch_name(document.getString("church_name"));
                                row.setTotal_members(document.getLong("total_members").toString());
                                row.setBarangay(document.getString("barangay"));
                                row.setMunicipality_city(document.getString("city"));
                                row.setProvince(document.getString("province"));
                                cdata.add(row);
                            }
                        }

                        if(cdata.size()>0) {

                            arrayAdapter = new searchPickerListView(getContext(), R.layout.list_view_search_picker, cdata, passData.getSearchPickType());
                            listviews.setAdapter(arrayAdapter);
                            listviews.setClickable(true);
                            listviews.setDivider(null);
                            cl_no_data.setVisibility(View.INVISIBLE);
                            listviews.setVisibility(View.VISIBLE);
                        }
                        else {
                            arrayAdapter = new searchPickerListView(getContext(), R.layout.list_view_search_picker, cdata, passData.getSearchPickType());
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

    private ArrayList<ChurchModel> loadChurchData(String keyword, String notIncludedChurchName){
        /*
        ArrayList<ChurchModel> cdata = new ArrayList<>();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("churches")
                .orderBy("church_name", Query.Direction.ASCENDING)
                .limit(20)
                .startAt(keyword)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot document: queryDocumentSnapshots)
                        {
                            if(document.getString("code_id").equals(notIncludedChurchName)) {} //Remove if there is the same record if existing in update
                            else if (!document.getString("filter_mode").equals("PUBLIC")) {} //Remove All Search Code and Private to be searchable
                            else{
                                ChurchModel row = new ChurchModel();
                                row.setNotify_Empty(cl_no_data);
                                row.setSearchPickerListView(listviews);
                                row.setUrl_logo(document.getString("url_logo"));
                                row.setChurch_code(document.getString("code_id"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setChurch_name(document.getString("church_name"));
                                row.setTotal_members(document.getLong("total_members").toString());
                                row.setBarangay(document.getString("barangay"));
                                row.setMunicipality_city(document.getString("city"));
                                row.setProvince(document.getString("province"));
                                cdata.add(row);
                            }
                        }

                        if(cdata.size()>0) {

                            arrayAdapter = new searchPickerListView(getContext(), R.layout.list_view_search_picker, cdata, passData.getSearchPickType());
                            listviews.setAdapter(arrayAdapter);
                            listviews.setClickable(true);
                            listviews.setDivider(null);
                            cl_no_data.setVisibility(View.INVISIBLE);
                            listviews.setVisibility(View.VISIBLE);
                        }
                        else {
                            arrayAdapter = new searchPickerListView(getContext(), R.layout.list_view_search_picker, cdata, passData.getSearchPickType());
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
