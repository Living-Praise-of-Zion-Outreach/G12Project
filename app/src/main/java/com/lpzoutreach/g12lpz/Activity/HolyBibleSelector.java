package com.lpzoutreach.g12lpz.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayoutMediator;
import com.lpzoutreach.g12lpz.Adapter.HolyBibleSelectorTabAdapter;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBible;
import com.lpzoutreach.g12lpz.databinding.ActivityHolyBibleSelectorBinding;
import com.lpzoutreach.g12lpz.databinding.ActivityImageViewerToolsBinding;

import java.util.Objects;

public class HolyBibleSelector extends AppCompatActivity {
    ActivityHolyBibleSelectorBinding binding;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private elUtil eventListenerUtil = elUtil.getInstance();
    private String orderType;
    private HolyBibleSelectorTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize(){
        binding = ActivityHolyBibleSelectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarHolyBibleSelector);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Bible Reference");
        orderType="";
        createTab(0);
        clickEvent();
        resultLauncher();
    }

    private void resultLauncher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==78){
                        Intent intent = result.getData();
                        if(intent!=null)
                        {
                            Intent returnIntent = new Intent();
                            setResult(78, returnIntent);
                            finish();
                        }
                    }
                });
    }

    private void clickEvent(){
        eventListenerUtil.addEventListener("slide-right-tab-control", event -> {
           int index = Integer.parseInt(event.getData().toString());
            createTab(index);
        });
        eventListenerUtil.addEventListener("holy-bible-done-selected", event -> {
                openHelperHolyBible_Default helper = new openHelperHolyBible_Default(getApplicationContext(), holyBibleSelector.get_bible_version(getApplicationContext()));
                openHelperBible helper_2 = new openHelperBible(getApplicationContext());
                HolyBibleModel data = new HolyBibleModel();
                data.setBook_number(sharedData.get_bible_book_no(getApplicationContext()));
                data.setLong_name(helper.get_book_name_by_string());
                data.setChapter(sharedData.get_bible_chapter(getApplicationContext()));
                data.setVerse(sharedData.get_bible_verses(getApplicationContext()));
                data.setBookVersion(sharedData.get_bible_version(getApplicationContext()));
                helper_2.insert_bible_history(data);
                Intent returnIntent = new Intent();
                setResult(78, returnIntent);
                finish();
        });
        binding.fabLeftChapterHolyBible.setOnClickListener(view -> eventListenerUtil.myCallBack("holy-bible-done-selected",""));
    }

    private void createTab(int index){
        adapter = new HolyBibleSelectorTabAdapter(HolyBibleSelector.this,  getSupportFragmentManager(),getLifecycle(),eventListenerUtil,orderType, index);
        binding.vpHolyBibleSelector.setAdapter(adapter);
        new TabLayoutMediator(binding.tlHolyBibleSelector,binding.vpHolyBibleSelector,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("BOOKS");
                    break;
                case 1:
                    tab.setText("CHAPTERS");
                    break;
                case 2:
                    tab.setText("VERSES");
                    break;
            }
        }).attach();
        Objects.requireNonNull(binding.tlHolyBibleSelector.getTabAt(index)).select();
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sort_order_holy_bible_selector:
                if(orderType.equals("")) {
                    orderType = "asc";
                    Toast.makeText(getApplicationContext(), "Ascending Mode", Toast.LENGTH_LONG).show();
                }else if (orderType.equals("asc")) {
                    orderType = "desc";
                    Toast.makeText(getApplicationContext(), "Descending Mode", Toast.LENGTH_LONG).show();
                }else{
                    orderType = "";
                    Toast.makeText(getApplicationContext(), "Default Mode", Toast.LENGTH_LONG).show();
                }
                createTab(0);
                break;
            case R.id.menu_history_holy_bible_selector:
                Intent intent = new Intent(getApplicationContext(), HolyBibleHistory.class);
                activityResultLauncher.launch(intent);
                overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_activity_holy_bible_selector, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }
}