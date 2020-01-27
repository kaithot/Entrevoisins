package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public ListNeighbourPagerAdapter(FragmentManager fm, int tabCount) { // ajout int tabcount en 2ème parametre
        super(fm);

        this.tabCount = tabCount;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) { // switch afin de basculer entre les 2 activités

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