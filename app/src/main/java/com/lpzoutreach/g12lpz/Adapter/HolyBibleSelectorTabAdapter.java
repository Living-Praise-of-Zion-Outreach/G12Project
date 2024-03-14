package com.lpzoutreach.g12lpz.Adapter;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Fragments.HolyBibleBookSelectorFragment;
import com.lpzoutreach.g12lpz.Fragments.HolyBibleChapterSelectorFragment;
import com.lpzoutreach.g12lpz.Fragments.HolyBibleVerseSelectorFragment;
import java.util.ArrayList;

public class HolyBibleSelectorTabAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fragments;
    private Context context;
    private elUtil eventListenerUtil;
    private String orderType;

    private int index;

    public HolyBibleSelectorTabAdapter(Context context, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, elUtil eventListenerUtil, String orderType, int index) {
        super(fragmentManager, lifecycle);
        fragments = new ArrayList<>();
        this.context = context;
        this.eventListenerUtil = eventListenerUtil;
        this.orderType = orderType;
        fragments.add(new HolyBibleBookSelectorFragment(eventListenerUtil,orderType));
        fragments.add(new HolyBibleChapterSelectorFragment(eventListenerUtil));
        fragments.add(new HolyBibleVerseSelectorFragment(eventListenerUtil));
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
    @Override
    public int getItemCount() {
        return fragments.size();
    }


}
