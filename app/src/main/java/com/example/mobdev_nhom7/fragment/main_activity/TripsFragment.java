package com.example.mobdev_nhom7.fragment.main_activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.fragment.main_activity.trips.TripsActiveFragment;
import com.example.mobdev_nhom7.fragment.main_activity.trips.TripsCancelledFragment;
import com.example.mobdev_nhom7.fragment.main_activity.trips.TripsPastFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TripsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    public static int initialPosition = 0;
    public TripsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(requireActivity());
        viewPagerAdapter.addFragment(new TripsActiveFragment());
        viewPagerAdapter.addFragment(new TripsPastFragment());
        viewPagerAdapter.addFragment(new TripsCancelledFragment());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(initialPosition);
        initialPosition = 0;

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Active");
                            break;
                        case 1:
                            tab.setText("Past");
                            break;
                        case 2:
                            tab.setText("Cancelled");
                            break;
                    }
                }
        ).attach();

        return view;
    }
}