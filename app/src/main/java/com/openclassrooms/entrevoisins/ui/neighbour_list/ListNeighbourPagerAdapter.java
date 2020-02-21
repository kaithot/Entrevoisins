package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    // TODO 6 add int tabCount to the 2ème setting, add a switch in the getItem method and add variable (tabCount) into getCount method
    public ListNeighbourPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return NeighbourFragment.newInstance(0);

            case 1:
                return NeighbourFragment.newInstance(1);

                default:
                    return null;
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return tabCount;
    }
}