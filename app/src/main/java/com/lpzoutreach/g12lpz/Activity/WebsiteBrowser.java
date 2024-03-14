package com.lpzoutreach.g12lpz.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.databinding.ActivityWebsiteBrowserBinding;

public class WebsiteBrowser extends AppCompatActivity {
    ActivityWebsiteBrowserBinding binding;
    private String action;
    private SearchView searchView;
    private MenuItem sitem;
    private ProgressDialogBar progressDialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebsiteBrowserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    public void initialize(){
        setSupportActionBar(binding.toolbarWebsiteBrowser);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialogBar = new ProgressDialogBar(WebsiteBrowser.this);
        action = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        setTitle(title);

        binding.ivBackWebsiteBrowser.setOnClickListener(v -> {
            if(binding.webviewWebsiteBrowser.canGoBack())
            {binding.webviewWebsiteBrowser.goBack();
            }
        });
        binding.ivForwardWebsiteBrowser.setOnClickListener(v -> {
            if(binding.webviewWebsiteBrowser.canGoForward()){
                binding.webviewWebsiteBrowser.goForward(); }
        });
        binding.ivRefreshWebsiteBrowser.setOnClickListener(v -> binding.webviewWebsiteBrowser.reload());
        binding.ivShareWebsiteBrowser.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,binding.webviewWebsiteBrowser.getUrl());
            intent.setType("text/plain");
            startActivity(intent);
        });

        binding.linearLayoutWebsiteBrowser.setVisibility(View.GONE);

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUrl(action);
                binding.refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initial_web_component(){
        if(binding.webviewWebsiteBrowser.canGoBack())
            binding.ivBackWebsiteBrowser.setVisibility(View.VISIBLE);
        else
            binding.ivBackWebsiteBrowser.setVisibility(View.INVISIBLE);

        if(binding.webviewWebsiteBrowser.canGoForward())
            binding.ivForwardWebsiteBrowser.setVisibility(View.VISIBLE);
        else
            binding.ivForwardWebsiteBrowser.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(binding.webviewWebsiteBrowser.canGoBack()){
            binding.webviewWebsiteBrowser.goBack();
        }else{
            overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_activity_website_browser, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        };

        sitem = menu.findItem(R.id.menu_search_website_browser);

        sitem.setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) sitem.getActionView();
        //sitem.expandActionView();
        searchView.setQueryHint("Search Site");
        searchView.setQuery(action,false);
        searchView.clearFocus();
        searchView.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadUrl(searchView.getQuery().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        WebSettings webSettings = binding.webviewWebsiteBrowser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        binding.webviewWebsiteBrowser.setWebViewClient(new MyWebViewClient());
        binding.webviewWebsiteBrowser.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.progressBarWebsiteBrowser.setProgress(newProgress);
            }
        });
        progressDialogBar.show();
        binding.webviewWebsiteBrowser.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                binding.webviewWebsiteBrowser.setVisibility(View.GONE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.webviewWebsiteBrowser.setVisibility(View.VISIBLE);
                progressDialogBar.hide();
                super.onPageFinished(view, url);
            }
        });

        loadUrl(action);

        return true;
    }

   private void loadUrl(String url) {

        boolean matchUrl = Patterns.WEB_URL.matcher(url).matches();
        if(matchUrl) {
            binding.webviewWebsiteBrowser.loadUrl(url);
            binding.webviewWebsiteBrowser.setWebViewClient(new MyWebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    progressDialogBar.hide();
                    super.onPageFinished(view, url);
                    initial_web_component();
                    binding.linearLayoutWebsiteBrowser.setVisibility(View.VISIBLE);
                    sitem.setVisible(false);
                }
            });
        }else {
            progressDialogBar.hide();
            binding.webviewWebsiteBrowser.loadUrl("google.com/search?q=" + url);
            binding.webviewWebsiteBrowser.setWebViewClient(new MyWebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    initial_web_component();
                    binding.linearLayoutWebsiteBrowser.setVisibility(View.VISIBLE);
                    sitem.setVisible(false);
                }
            });
        }
    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            binding.progressBarWebsiteBrowser.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            binding.progressBarWebsiteBrowser.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}