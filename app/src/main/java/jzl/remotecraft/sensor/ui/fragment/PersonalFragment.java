package jzl.remotecraft.sensor.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import jzl.remotecraft.sensor.R;
import jzl.remotecraft.sensor.ui.adapter.PersonalPagerAdapter;
import jzl.remotecraft.sensor.ui.fragment.personal.HistoryFragment;
import jzl.remotecraft.sensor.ui.fragment.personal.InfoFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class PersonalFragment extends BaseFragment {


    private LinearLayout personal_content = null;
    private TabLayout personal_pager_tabs = null;
    private ViewPager personal_pager = null;
    private FloatingActionButton personal_fab = null;

    public PersonalFragment() {
        // Required empty public constructor
    }
    @Override
    protected void init() {
        personal_pager.setOffscreenPageLimit(PersonalPagerAdapter.PAGE_COUNT);
        BaseFragment [] cfs = new BaseFragment[2];
        cfs[0] = new HistoryFragment();
        cfs[1] = new InfoFragment();
        final PersonalPagerAdapter adapter = new PersonalPagerAdapter(getSupportFragmentManager(),personal_fab,cfs);
        personal_pager.setAdapter(adapter);
        personal_pager_tabs.setupWithViewPager(personal_pager);
        personal_pager_tabs.setTabMode(TabLayout.MODE_FIXED);
        personal_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                personal_fab.show();
//                ExploreChildFragment exploreChildFragment =  adapter.getFB(position);
//                if(exploreChildFragment != null && exploreChildFragment.getExplore_recycleview() != null){
//                    explore_fab.attachToRecyclerView(exploreChildFragment.getExplore_recycleview());
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_personal, container, false);
        personal_content = (LinearLayout)view.findViewById(R.id.personal_content);
        personal_pager_tabs = (TabLayout)view.findViewById(R.id.personal_pager_tabs);
        personal_pager = (ViewPager)view.findViewById(R.id.personal_pager);
        personal_fab = (FloatingActionButton)view.findViewById(R.id.personal_fab);
        return view;
    }
    protected View getLoadingTargetView(){return personal_content;}
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    
}
