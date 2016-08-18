package moe.haruue.goodhabits.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;

import com.jude.utils.JUtils;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.BaseActivity;
import moe.haruue.goodhabits.util.BitmapUtils;

public class MainActivity extends BaseActivity {

    private Listener listener = new Listener();
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JUtils.Log("MainActivity#onCreate()");
        setContentView(R.layout.activity_main);
        initializeDrawerLayout();
        initializeToolbar();
        initializeTabLayout();
    }

    private void initializeDrawerLayout() {
        drawerLayout = $(R.id.drawer_layout);
    }

    private void initializeToolbar() {
        JUtils.Log("MainActivity#initializeToolbar()");
        toolbar = $(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(view -> {
            JUtils.Log("ToolbarNavigationIcon#OnClick, id = " + view.getId());
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initializeTabLayout() {
        JUtils.Log("MainActivity#initializeTabLayout()");
        tabLayout = $(R.id.tab_layout);
        viewPager = $(R.id.view_pager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager, true);
        appBarLayout = $(R.id.app_bar_layout);
        appBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(listener);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private class Listener implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

            }
        }

        @Override
        public void onGlobalLayout() {
            // 在这里设置 AppBarLayout 背景
            appBarLayout.setBackgroundDrawable(new BitmapDrawable(BitmapUtils.zoomImg(
                    BitmapFactory.decodeResource(getResources(), R.drawable.bg_toolbar),
                    appBarLayout.getWidth(),
                    appBarLayout.getHeight()
            )));

        }
    }

    public static void start(Context context) {
        JUtils.Log("MainActivity#start()");
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
}
