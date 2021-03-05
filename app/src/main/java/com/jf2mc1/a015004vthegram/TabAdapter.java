package com.jf2mc1.a015004vthegram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {


    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);


    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {
        // returns the fragment we are dealing with
        switch (tabPosition) {
            case 0:
                return new ProfileTab();
            case 1:
                return new UsersTab();
            case 2:
                return new ShareImageTab();
            default:
                return new Fragment();
        }

    }

    @Override
    public int getCount() {
        // how many tabs indise tab layout
        return 3;
    }

    // set title of each tab
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        switch (position) {
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Image";
            default:
                return null;
        }

    }
}
