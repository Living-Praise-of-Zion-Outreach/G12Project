package com.lpzoutreach.g12lpz.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.RecyclerView.holyBibleBookSelectorRecyclerView;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.databinding.FragmentEbookBinding;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleBookSelectorBinding;

import java.util.ArrayList;

public class HolyBibleBookSelectorFragment extends Fragment {
    FragmentHolyBibleBookSelectorBinding binding;
    holyBibleBookSelectorRecyclerView adapter;
    String orderType;
    private elUtil eventListenerUtil;

    public HolyBibleBookSelectorFragment(elUtil eventListenerUtil, String orderType) {
        this.eventListenerUtil = eventListenerUtil;
        this.orderType=orderType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHolyBibleBookSelectorBinding.inflate(inflater,container,false);
        initialize();
        return binding.getRoot();
    }

    private void initialize(){
        loadBook("");
        clickEvent();
    }

    private void clickEvent(){
        eventListenerUtil.addEventListener("holy-bible-book-select", new elHandler() {
            @Override
            public void callback(elEvent event) {
                int book_no = Integer.parseInt(event.getData().toString());
                sharedData.set_bible_book_no(requireContext(),book_no);
                sharedData.set_bible_chapter(requireContext(),1);
                sharedData.set_bible_verses(requireContext(),1);
                binding.svSearchHolyBibleBookSelector.setQuery("-",true);
            }
        });
        binding.cvSearchHolyBibleBookSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.svSearchHolyBibleBookSelector.onActionViewExpanded();
            }
        });

        binding.svSearchHolyBibleBookSelector.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadBook("");
                binding.svSearchHolyBibleBookSelector.clearFocus();
                eventListenerUtil.myCallBack("slide-right-tab-control",1);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                loadBook(newText);
                return false;
            }
        });

    }
    private void loadBook(String searchValue){
        openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(), holyBibleSelector.get_bible_version(requireContext()));
        ArrayList<HolyBibleModel> data = helper.get_list_book_name(orderType,eventListenerUtil,"holy-bible-book-select",searchValue);

        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        binding.rvHolyBibleBookSelector.setLayoutManager(manager);
        adapter = new holyBibleBookSelectorRecyclerView(requireContext(), data);
        binding.rvHolyBibleBookSelector.setAdapter(adapter);

        if(data.size()>0){
            binding.rvHolyBibleBookSelector.setVisibility(View.VISIBLE);
            binding.clNoDataHolyBibleBookSelector.setVisibility(View.GONE);
        }else{
            binding.rvHolyBibleBookSelector.setVisibility(View.GONE);
            binding.clNoDataHolyBibleBookSelector.setVisibility(View.VISIBLE);
        }

        adapter.resetAllColor(sharedData.get_bible_current_row_index_book(requireContext()));
    }
}