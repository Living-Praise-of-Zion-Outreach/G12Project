package com.lpzoutreach.g12lpz.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleBinding;


public class HolyBibleFragment extends Fragment {
    FragmentHolyBibleBinding binding;
    private elUtil eventListenerUtil = elUtil.getInstance();
    private BottomSheetBehavior bsbColorPalete;
    androidx.appcompat.app.ActionBar parentActionBar;
    public HolyBibleFragment(androidx.appcompat.app.ActionBar ab) {
        parentActionBar = ab;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {setHasOptionsMenu(true);super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHolyBibleBinding.inflate(inflater,container,false);
        initialize();
        return binding.getRoot();
    }

    private void initialize(){
        bottomNav();
        clickEvent();
        binding.tvFooterHeadingHolyBible.setVisibility(View.GONE);
        bsbColorPalete = BottomSheetBehavior.from(binding.flBottomSheetColorPalette);
        bsbColorPalete.setPeekHeight(0);
        bsbColorPalete.setState(BottomSheetBehavior.STATE_COLLAPSED);
        binding.flBottomSheetColorPalette.setVisibility(View.VISIBLE);
        binding.bnvHolyBible.setVisibility(View.GONE);
    }

    private void clickEvent(){

        eventListenerUtil.addEventListener("show-navigation-bottom", new elHandler() {
            @Override
            public void callback(elEvent event) {
                if(binding.bnvHolyBible.getVisibility()==View.GONE) {
                    TranslateAnimation animate = new TranslateAnimation(0, 0, binding.bnvHolyBible.getHeight(), 0);
                    animate.setDuration(300);
                    animate.setFillAfter(true);
                    binding.bnvHolyBible.startAnimation(animate);
                    binding.bnvHolyBible.setVisibility(View.VISIBLE);
                    binding.tvFooterHeadingHolyBible.setVisibility(View.GONE);
                }
            }
        });
        eventListenerUtil.addEventListener("hide-navigation-bottom", new elHandler() {
            @Override
            public void callback(elEvent event) {
                if(binding.bnvHolyBible.getVisibility()==View.VISIBLE) {
                    TranslateAnimation animate = new TranslateAnimation(0, 0, 0, binding.bnvHolyBible.getHeight());
                    animate.setDuration(300);
                    animate.setFillAfter(true);
                    binding.bnvHolyBible.startAnimation(animate);
                    binding.bnvHolyBible.setVisibility(View.GONE);
                    binding.tvFooterHeadingHolyBible.setVisibility(View.VISIBLE);
                }
            }
        });
        eventListenerUtil.addEventListener("refresh-bible-title", new elHandler() {
            @Override
            public void callback(elEvent event) {
                String value = holyBibleSelector.get_title_action_bar(requireContext());
                parentActionBar.setTitle(value);
                binding.tvFooterHeadingHolyBible.setText(value);
            }
        });
        eventListenerUtil.addEventListener("open-color-palette", new elHandler() {
            @Override
            public void callback(elEvent event) {
                String data = event.getData().toString();
                showBottomSheetColorPalette(data);
            }
        });

         
    }

    @SuppressLint("SetTextI18n")
    private void showBottomSheetColorPalette(String selectedVerse){
        //Toast.makeText(requireContext(),selectedVerse,Toast.LENGTH_LONG).show();
        openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(), holyBibleSelector.get_bible_version(requireContext()));
        binding.tvSelectedVerseHolyBible.setText(helper.get_book_name_by_string() + " " + selectedVerse);

        if(!selectedVerse.equals("")){
            //bsbColorPalete.setHideable(false);
            //bsbColorPalete.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        }else{
            //bsbColorPalete.setHideable(true);
            //bsbColorPalete.setState(BottomSheetBehavior.STATE_HIDDEN);
        }

        binding.bnvHolyBible.setVisibility(View.GONE);

    }

    private void showNavigationBottom(){
        if(binding.bnvHolyBible.getVisibility()==View.GONE){
            TranslateAnimation animate = new TranslateAnimation(0,0, binding.bnvHolyBible.getHeight(),0);
            animate.setDuration(300);
            animate.setFillAfter(true);
            binding.bnvHolyBible.startAnimation(animate);
            binding.bnvHolyBible.setVisibility(View.GONE);
            binding.tvFooterHeadingHolyBible.setVisibility(View.GONE);
        }

        binding.bnvHolyBible.setVisibility(View.GONE);
    }
    @SuppressLint("NonConstantResourceId")
    private void bottomNav(){

        replaceFragment(new HolyBibleHomeFragment(eventListenerUtil, binding.fabLeftChapterHolyBible,binding.fabRightChapterHolyBible));
        String title = holyBibleSelector.get_title_action_bar(requireContext());
        parentActionBar.setTitle(title);
        binding.tvFooterHeadingHolyBible.setText(title);
        
        binding.bnvHolyBible.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home_bible:
                    binding.fabLeftChapterHolyBible.setVisibility(View.VISIBLE);
                    binding.fabRightChapterHolyBible.setVisibility(View.VISIBLE);
                    parentActionBar.setTitle(holyBibleSelector.get_title_action_bar(requireContext()));
                    replaceFragment(new HolyBibleHomeFragment(eventListenerUtil, binding.fabLeftChapterHolyBible,binding.fabRightChapterHolyBible));
                    break;

            }
            binding.bnvHolyBible.setVisibility(View.GONE);
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content_holy_bible,fragment);
        fragmentTransaction.commit();
    }
}