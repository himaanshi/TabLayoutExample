package com.paxcel.tablayoutexample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paxcel.tablayoutexample.fragment.FirstFragment;
import com.paxcel.tablayoutexample.fragment.SecondFragment;
import com.paxcel.tablayoutexample.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        setTabbedView();
    }

    private void setTabbedView() {

        final ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        final PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, android.R.color.transparent));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorBlue));

        View vCurrentCity = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_tab, null, false);
        ImageView ivIconCity = vCurrentCity.findViewById(R.id.ivIcon);
        ivIconCity.setImageResource(R.drawable.icon_current_city_active);
        TextView tvTextCity = vCurrentCity.findViewById(R.id.tvText);
        tvTextCity.setText("TAB ONE");
        tvTextCity.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorBlue));
        tabLayout.getTabAt(0).setCustomView(vCurrentCity);


        View vDestination = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_tab, null, false);
        ImageView ivIconDestination = vDestination.findViewById(R.id.ivIcon);
        ivIconDestination.setImageResource(R.drawable.icon_destination);
        TextView tvTextDestination = vDestination.findViewById(R.id.tvText);
        tvTextDestination.setText("TAB TWO");
        tabLayout.getTabAt(1).setCustomView(vDestination);

        View vDate = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_tab, null, false);
        ImageView ivIconDate = vDate.findViewById(R.id.ivIcon);
        ivIconDate.setImageResource(R.drawable.icon_calendar);
        TextView tvTextDate = vDate.findViewById(R.id.tvText);
        tvTextDate.setText("TAB THREE");
        tabLayout.getTabAt(2).setCustomView(vDate);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                ImageView ivIcon = tab.getCustomView().findViewById(R.id.ivIcon);
                TextView tvText = tab.getCustomView().findViewById(R.id.tvText);
                tvText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorBlue));
                switch (tab.getPosition()) {
                    case 0:
                        ivIcon.setImageResource(R.drawable.icon_current_city_active);
                        break;
                    case 1:
                        ivIcon.setImageResource(R.drawable.icon_destination_active);
                        break;
                    case 2:
                        ivIcon.setImageResource(R.drawable.icon_calendar_active);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView ivIcon = tab.getCustomView().findViewById(R.id.ivIcon);
                TextView tvText = tab.getCustomView().findViewById(R.id.tvText);
                tvText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorDarkGray));
                switch (tab.getPosition()) {
                    case 0:
                        ivIcon.setImageResource(R.drawable.icon_current_city);
                        break;
                    case 1:
                        ivIcon.setImageResource(R.drawable.icon_destination);
                        break;
                    case 2:
                        ivIcon.setImageResource(R.drawable.icon_calendar);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;

        public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    FirstFragment firstFragment = new FirstFragment();
                    return firstFragment;
                case 1:
                    SecondFragment secondFragment = new SecondFragment();
                    return secondFragment;
                case 2:
                    ThirdFragment thirdFragment = new ThirdFragment();
                    return thirdFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }

    }


}
