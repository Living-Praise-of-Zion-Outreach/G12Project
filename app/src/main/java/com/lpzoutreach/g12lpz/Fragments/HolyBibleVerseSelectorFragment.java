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
import com.lpzoutreach.g12lpz.RecyclerView.holyBibleVerseSelectorRecyclerView;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleChapterSelectorBinding;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleVerseSelectorBinding;

import java.util.ArrayList;

public class HolyBibleVerseSelectorFragment extends Fragment {
    private FragmentHolyBibleVerseSelectorBinding binding;
    private elUtil eventListenerUtil;
    public HolyBibleVerseSelectorFragment(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    @Override public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHolyBibleVerseSelectorBinding.inflate(inflater,container,false);
        initialize();
        return binding.getRoot();
    }

    private void initialize(){
        loadVerse();
        clickEvent();
    }

    private void clickEvent(){
        eventListenerUtil.addEventListener("holy-bible-verse-select", new elHandler() {
            @Override
            public void callback(elEvent event) {
                int verse_no = Integer.parseInt(event.getData().toString());
                sharedData.set_bible_verses(requireContext(),verse_no);
                loadVerse();
                eventListenerUtil.myCallBack("holy-bible-done-selected","");
            }
        });
    }

    private void loadVerse(){
        openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(), holyBibleSelector.get_bible_version(requireContext()));
        ArrayList<HolyBibleModel> data = helper.get_list_verse(eventListenerUtil,"holy-bible-verse-select");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(binding.rvHolyBibleVerseSelector.getContext(),4);
        binding.rvHolyBibleVerseSelector.setLayoutManager(gridLayoutManager);

        holyBibleVerseSelectorRecyclerView adapter = new holyBibleVerseSelectorRecyclerView(requireContext(), data);
        binding.rvHolyBibleVerseSelector.setAdapter(adapter);

    }
}