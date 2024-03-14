package com.lpzoutreach.g12lpz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.RecyclerView.holyBibleChapterSelectorRecyclerView;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleBookSelectorBinding;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleChapterSelectorBinding;

import java.util.ArrayList;

public class HolyBibleChapterSelectorFragment extends Fragment {
    FragmentHolyBibleChapterSelectorBinding binding;
    private elUtil eventListenerUtil;

    public HolyBibleChapterSelectorFragment(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }


    @Override public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHolyBibleChapterSelectorBinding.inflate(inflater,container,false);
        initialize();
        return binding.getRoot();  }

    private void initialize(){
        clickEvent();

    }


    private void clickEvent(){
        loadChapter();
        eventListenerUtil.addEventListener("holy-bible-chapter-select", new elHandler() {
            @Override
            public void callback(elEvent event) {
                int chapter = Integer.parseInt(event.getData().toString());
                if(chapter==0){
                    sharedData.set_bible_chapter(requireContext(),chapter);
                    sharedData.set_bible_verses(requireContext(),0);
                    eventListenerUtil.myCallBack("holy-bible-done-selected","");
                }else{
                    sharedData.set_bible_chapter(requireContext(),chapter);
                    sharedData.set_bible_verses(requireContext(),1);
                    loadChapter();
                    eventListenerUtil.myCallBack("slide-right-tab-control",2);
                }
            }
        });
    }

    private void loadChapter(){
        openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(), holyBibleSelector.get_bible_version(requireContext()));
        ArrayList<HolyBibleModel> data = helper.get_list_chapter(eventListenerUtil,"holy-bible-chapter-select");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(binding.rvHolyBibleChapterSelector.getContext(),4);
        binding.rvHolyBibleChapterSelector.setLayoutManager(gridLayoutManager);

        holyBibleChapterSelectorRecyclerView adapter = new holyBibleChapterSelectorRecyclerView(requireContext(), data);
        binding.rvHolyBibleChapterSelector.setAdapter(adapter);

    }
}