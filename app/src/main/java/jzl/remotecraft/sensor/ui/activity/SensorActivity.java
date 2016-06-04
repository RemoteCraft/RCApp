package jzl.remotecraft.sensor.ui.activity;

import android.content.res.TypedArray;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import jzl.remotecraft.sensor.R;
import jzl.remotecraft.sensor.model.entity.NavDrawerItem;
import jzl.remotecraft.sensor.ui.adapter.NavDrawerListAdapter;
import jzl.remotecraft.sensor.ui.fragment.BaseFragment;
import jzl.remotecraft.sensor.ui.fragment.MainFragment;
import jzl.remotecraft.sensor.ui.fragment.MonitorFragment;
import jzl.remotecraft.sensor.ui.fragment.PersonalFragment;
import jzl.remotecraft.sensor.util.Common.Common;
import jzl.remotecraft.sensor.util.Common.LogUtil;

public class SensorActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ListView mDrawerMenu;
    private String[] mNavMenuTitles;
    private TypedArray mNavMenuIconsTypeArray;
    private TypedArray mNavMenuIconTintTypeArray;
    private ArrayList<NavDrawerItem> mNavDrawerItems;
    private NavDrawerListAdapter mAdapter;
    private int position;


    private DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Display the menu button on the left of the toolbar
        setupToolbar(getSupportActionBar());
        setUpDrawer();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setOverflowShowingAlways();

    }

    private void setupToolbar(final ActionBar ab) {
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }
    //Mandate to hide physical menu button to enable overflow all the time
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }
        mDrawerMenu = (ListView) findViewById(R.id.left_menu);
        mNavMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources
        mNavMenuIconsTypeArray = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        mNavMenuIconTintTypeArray = getResources()
                .obtainTypedArray(R.array.nav_drawer_tint);
        mNavDrawerItems = new ArrayList<NavDrawerItem>();
        for (int i = 0; i < mNavMenuTitles.length; i++) {
            mNavDrawerItems.add(new NavDrawerItem(mNavMenuTitles[i], mNavMenuIconsTypeArray
                    .getResourceId(i, -1), mNavMenuIconTintTypeArray.getResourceId(i, -1)));
        }
        mNavMenuIconsTypeArray.recycle();
        // setting the nav drawer list adapter
        mAdapter = new NavDrawerListAdapter(this,
                mNavDrawerItems);
        mDrawerMenu.setAdapter(mAdapter);
        mDrawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!Common.isEmpty(mNavDrawerItems, i)) {
                    NavDrawerItem navDrawerItem = mNavDrawerItems.get(i);
                    if (navDrawerItem != null) {
                        selectItem(i, navDrawerItem.getTitle());
                    }
                }
            }
        });


        selectItem(0, mNavDrawerItems.get(0).getTitle());
    }

    protected void openDrawer() {
        if (drawerLayout == null)
            return;
        drawerLayout.openDrawer(GravityCompat.START);
    }

    protected void closeDrawer() {
        if (drawerLayout == null)
            return;
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case android.R.id.home:
                openDrawer();
                return true;
            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                Bundle extras = new Bundle();
                extras.putString("key", "value");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //Mandate to show icon in the hidden menu bar
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sensor, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
    /**
     * 左侧drawer选择事件
     *
     * @param position
     * @param title
     */
    public void selectItem(int position, String title) {
        Fragment fragment = null;
        this.position = position;
        switch (position) {
            case 0:
                //首页
                fragment = new MainFragment();
                break;
            case 1:
                //个人
                fragment = new PersonalFragment();
                break;
            case 2:
                //传感器
                fragment = new MonitorFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment).commit();
            setTitle(title);
            closeDrawer();
        } else {
            LogUtil.e("HomeActivity", "Error in creating fragment");
        }
    }
    @Override
    public void onBackPressed() {

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
            return;
        }

        if (position != 0) {
            selectItem(0, mNavDrawerItems.get(0).getTitle());
        } else {
            super.onBackPressed();
        }

    }
    public void onFragmentInteraction(Uri uri){

    }
}
