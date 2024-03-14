package com.lpzoutreach.g12lpz.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Fragments.HolyBibleFragment;
import com.lpzoutreach.g12lpz.Models.NotifyAppUpdateModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.utilFile;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperProfileInformation;
import com.lpzoutreach.g12lpz.Utility.sql.local.utilDatabase;
import com.lpzoutreach.g12lpz.Utility.sql.php.dataTransfer;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    elUtil eventListener = elUtil.getInstance();
    private ProgressDialogBar progressDialogBar;
    FragmentManager mFragmentManager;

    ActivityHomeBinding binding;
    Menu menu;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //auth = FirebaseAuth.getInstance();


        //gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //gClient = GoogleSignIn.getClient(this, gOptions);

        progressDialogBar = new ProgressDialogBar(Home.this);

        setSupportActionBar(binding.toolbarHome);


        if(utilFile.isPermissionGranted(getApplicationContext())){
            utilDatabase.checkDatabase(getApplicationContext(),sharedData.DBNAME);
        }

        menu = binding.navigationViewHome.getMenu();
        binding.navigationViewHome.getHeaderView(0);
        binding.navigationViewHome.bringToFront();

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, binding.drawerLayoutHome, binding.toolbarHome, R.string.Open_Navigation,R.string.Close_Navigation);
        binding.drawerLayoutHome.addDrawerListener(toogle);
        toogle.syncState();
        binding.navigationViewHome.setNavigationItemSelectedListener(this);


        mFragmentManager = getSupportFragmentManager();


        eventListener.addEventListener("update-app", new elHandler() {
            @Override
            public void callback(elEvent event) {
                NotifyAppUpdateModel data = (NotifyAppUpdateModel) event.getData();


                Toast.makeText(getApplicationContext(),data.getAppPath(),Toast.LENGTH_LONG).show();

                String[] fileName  = data.getAppPath().split("/");

                Uri downloadURI = Uri.parse(accessUrl.BASE_URL + data.getAppPath());
                DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                try{
                    if(manager!=null){
                        DownloadManager.Request request = new DownloadManager.Request(downloadURI);
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                                .setTitle(fileName[fileName.length-1])
                                .setDescription("Download " + fileName[fileName.length-1])
                                .setAllowedOverMetered(true)
                                .setAllowedOverRoaming(true)
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName[fileName.length-1])
                                .setMimeType(getMimeType(downloadURI));
                        manager.enqueue(request);
                        Toast.makeText(getApplicationContext(),"Download Started", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_LONG).show();
                    Log.v("ERROR Downoload",e.toString());
                }



            }
        });



        setTitle("LPZ Bible");
        replaceFragment(new HolyBibleFragment(getSupportActionBar()));

    }

    private String getMimeType(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    private void getData(){
        if(!sharedData.is_Profile_Sync(getApplicationContext())){
            if(utilNetwork.isConnected(getApplicationContext())){
                dataTransfer.syncProfileInformation(getApplicationContext(),progressDialogBar);
            }
        }
    }

    public void resetCheck(){
        unCheckAllMenuItems(binding.navigationViewHome.getMenu());
    }

    private void unCheckAllMenuItems(@NonNull final Menu menu) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            final MenuItem item = menu.getItem(i);
            if(item.hasSubMenu()) {
                // Un check sub menu items
                unCheckAllMenuItems(item.getSubMenu());
            } else {
                item.setChecked(false);
            }
        }
    }

    private void hideAllMenuItems(@NonNull final Menu menu) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            final MenuItem item = menu.getItem(i);
            if(item.hasSubMenu()) {
                // Un check sub menu items
                hideAllMenuItems(item.getSubMenu());
            } else {
                item.setVisible(false);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        resetCheck();
        //hideAllMenuItems(toolbarMenu);
        item.setChecked(true);
        binding.drawerLayoutHome.closeDrawer(GravityCompat.START);

        switch (id)
        {
            case R.id.menu_holy_bible:
                binding.toolbarHome.setTitle("Holy Bible");
                replaceFragment(new HolyBibleFragment(getSupportActionBar()));
                break;
            case R.id.menu_privacy_policy:
                Intent intent = new Intent(Home.this, WebsiteBrowser.class);
                intent.putExtra("title",  "Privacy Policy");
                intent.putExtra("url",accessUrl.BASE_URL +  "privacy-policy");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
                    break;
            case R.id.menu_terms_condition:
                intent = new Intent(Home.this, WebsiteBrowser.class);
                intent.putExtra("title", "Terms and Conditions");
                intent.putExtra("url",accessUrl.BASE_URL +  "terms-and-conditions");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
                break;
            default:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.other_click_dialog),Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment){
        clearBackStack(mFragmentManager);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_home,fragment);
        fragmentTransaction.commit();
    }
    private void clearBackStack(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(0);
            mFragmentManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.MyDialogTheme);
        builder.setTitle(getResources().getString(R.string.exit_application_title_dialog))
                .setCancelable(false)
                .setMessage(getResources().getString(R.string.exit_application_content_dialog))
                .setPositiveButton(getResources().getString(R.string.yes_button_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no_button_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater =  getMenuInflater();
        //inflater.inflate(R.menu.toolbar_home,menu);
        //ToolBarMainMenu = menu;
        return true;
    }

    private void showToolbarMenuIcons(int index, boolean isVisible)
    {

    }

}