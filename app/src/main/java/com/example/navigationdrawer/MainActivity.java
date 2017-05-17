package com.example.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.navigationdrawer.Fragments.AddPhotoFragment;
import com.example.navigationdrawer.Fragments.ExploreFragment;
import com.example.navigationdrawer.Fragments.HelpFragment;
import com.example.navigationdrawer.Fragments.SettingsFragment;
import com.example.navigationdrawer.Fragments.ShareFragment;
import com.example.navigationdrawer.Fragments.ViewLikesFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(mNavigationView);

        mFragmentManager = this.getSupportFragmentManager();

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content, new ExploreFragment()).commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.nav_explore:
                fragmentClass = ExploreFragment.class;
                break;
            case R.id.nav_add_photo:
                fragmentClass = AddPhotoFragment.class;
                break;
            case R.id.nav_likes:
                fragmentClass = ViewLikesFragment.class;
                break;
            case R.id.nav_share:
                fragmentClass = ShareFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.nav_help:
                fragmentClass = HelpFragment.class;
                break;
            default:
                fragmentClass = MainActivity.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mFragmentManager.beginTransaction().replace(R.id.content, fragment).commit();

        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }
}